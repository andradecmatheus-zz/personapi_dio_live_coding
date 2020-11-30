package one.digitalinnovation.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // está reconhecida como uma entidade do BD
@Data //automaticamente insere os getters e setters
@Builder // o padrão de projetos para a construção dos objetos
@AllArgsConstructor //insere os construtores
@NoArgsConstructor //insere os construtores
public class Phone {

    @Id //por ser entidade, é preciso uma PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity = autoincremental
    private Long id;

    @Enumerated(EnumType.STRING) // o tipo
    @Column(nullable = false) //criada uma regra uma constraint no BD; nullable = não pode ser nulo
    private PhoneType type;

    @Column(nullable = false)
    private String number;


}
