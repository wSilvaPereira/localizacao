package io.github.wSilvaPereira.localizacao.service;

import io.github.wSilvaPereira.localizacao.domain.entity.Cidade;
import io.github.wSilvaPereira.localizacao.domain.repository.CidadeRepository;

import static io.github.wSilvaPereira.localizacao.domain.repository.specs.CidadeSpecs.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    void salvarCidade() {
        var cidade = new Cidade(1L, "São Paulo", 12396272L);
        repository.save(cidade);
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);

        return repository.findAll(example);
    }

    public void listarCidadesByNomeSpec() {
        Specification<Cidade> spec = nomeEqual("São Paulo").or(habitantesGreaterThan(1000L));
        repository.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQL() {
        repository
                .findByNomeSqlNativo("Curitiba")
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

    void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
        Specification<Cidade> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (filtro.getId() != null) {
            specs = specs.and(idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if (filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }

}
