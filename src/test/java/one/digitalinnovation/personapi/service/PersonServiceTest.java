package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock // indica que é um mock, que será criado um objeto dublê/fake dele
    private PersonRepository personRepository;

    @InjectMocks //Injetar o mock da classe acima em PersonService
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() { // ao criar um DTO retornará um msgm de sucesso aqui
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, succesMessage);
        // assertEquals: a msgm esperada tem q ser = a que estou retornando no método
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}

/*
*   linha 20: @ExtendWith(MockitoExtension.class) - injetar que rode com o mockito, que é uma lib p/ criar objetos mocks
* p/ mockar as dependencias da classe. Significa que será testado somente o código dessa PersonService. Não vai haver
* interação com PersonRepository visando performance. Gerando um mock, classe fake deste PersonRepository para fazer um
* teste unitário
* */