package io.github.wSilvaPereira.localizacao.domain.repository.specs;

import io.github.wSilvaPereira.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    public static Specification<Cidade> idEqual(Long id){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nomeEqual(String nome){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesGreaterThan(Long  value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("habitantes"), value);
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("habitantes"), min, max);
    }

    public static Specification<Cidade> nomeLike(String nome){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

}
