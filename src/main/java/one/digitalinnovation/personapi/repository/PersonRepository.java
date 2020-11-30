package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// é passado através de genérics a entidade q queremos gerenciar (Person) e  o tipo de dados do ID (Long)
/* com o spring não precisa abrir nem fechar conexão com o BD; implementar operação de criação, leitura e exclusão
    somente com esta interface já são fornecidas todas as operações básicas de BD*/
public interface PersonRepository extends JpaRepository<Person, Long> {
}
