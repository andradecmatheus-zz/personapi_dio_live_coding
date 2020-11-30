package one.digitalinnovation.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty //nunca pode ser vazio
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty
    @CPF //A biblio Validation já tem uma validação automática para CPF de formato BR
    private String cpf;

    private String birthDate;

    @Valid // qnd for passar os dados do telefone, cada um dos outros dados da lista precisam ser validados
    @NotEmpty
    private List<PhoneDTO> phones;
}

/*
    Objeto responsável por receber todos os dados de entrada, mapeá-los aqui ao invés de no Entity.
    Aqui é possível fazer a validação no momento da requisição HTTP, na própria camada de Controller. Tamanho dos chars...
 */