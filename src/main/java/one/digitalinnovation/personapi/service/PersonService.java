package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // indica ao Spring que gerenciará uma classe responsável por colocar todas as regras de negócio da aplicação
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList()); // retorna a lista
    //34: ∀ dos dados da lista de allPeople será chamado o método personMapper p/ converter as linhas de allPeople p/ DTO
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    } // Optional<Person> - evita verificações como nula

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    // os comandos desse método estão sendo chamados + de 1x, por isso criou-se este método
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        //orElseThrow - se ñ achar a pessoa, já é lançada a exceção dentro, assim não precisa do Optional
    } // a exceção foi jogada na assinatura do método ao invés de usar um bloco try-catch. Tbm foi colocado em Controller

    // os comandos desse método estão sendo chamados + de 1x, por isso criou-se este método
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}

/*      toda a regra de criação está delegada para esta classe Service, com isso no teste unitário não é preciso se
preocupar com a camada REST. Assim é desacoplado e definida responsabilidades únicas para cada classe.
        Boa prática: todos os métodos publics primeiro e depois os privates (public > default > protegidos > private).
        No teste unitário é feito todos os casos de teste possíveis.
    */
