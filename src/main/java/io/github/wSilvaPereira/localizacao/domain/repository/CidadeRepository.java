package io.github.wSilvaPereira.localizacao.domain.repository;

import io.github.wSilvaPereira.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

//    Query Methods com o campo Nome(String)
    List<Cidade> findByNome(String nome);

    List<Cidade> findByNomeLike(String nome);

    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLikeInsensitive(String nome);

    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);

//    Query Methods com o campo Habitantes(Numerico)
    List<Cidade> findByHabitantes(Long habitantes);
    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThanEqual(Long habitantes);

    List<Cidade> findByHabitantesAndNomeLike(Long habitantes, String nome);

}
