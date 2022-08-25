package io.github.wSilvaPereira.localizacao.service;

import io.github.wSilvaPereira.localizacao.domain.entity.Cidade;
import io.github.wSilvaPereira.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadesService {

    private CidadeRepository repository;

    public CidadesService(CidadeRepository repository) {
        this.repository = repository;
    }

    public void listarCidades() {
        repository.findAll().forEach(System.out::println);
    }

    public void listarCidadesPorNome() {
//        Com ordenação
//        repository.findByNomeLike("%a%", Sort.by(Sort.Direction.DESC, "habitantes")).forEach(System.out::println);

//        Com paginação
//        Pageable pageable = PageRequest.of(0, 2);
//        repository.findByNomeLike("%a%", pageable).forEach(System.out::println);

//        Com ordenação e com paginação
        Pageable pageable = PageRequest.of(0, 2, Sort.by("habitantes"));
        repository.findByNomeLike("%a%", pageable).forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes() {
        repository.findByHabitantes(12396372L).forEach(System.out::println);
    }

    @Transactional
    void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396272L);
        repository.save(cidade);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);

        return repository.findAll(example);
    }
}
