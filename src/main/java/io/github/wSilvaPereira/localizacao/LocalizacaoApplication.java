package io.github.wSilvaPereira.localizacao;

import io.github.wSilvaPereira.localizacao.domain.entity.Cidade;
import io.github.wSilvaPereira.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
//		salvarCidade();
//		listarCidades();
		listarCidadesPorHabitantes();
	}

	private void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}

	private void listarCidadesPorNome() {
		cidadeRepository.findByNome("Curitiba").forEach(System.out::println);
	}

	private void listarCidadesPorHabitantes() {
		cidadeRepository.findByHabitantes(12396372L).forEach(System.out::println);
	}

//	@Transactional
//	private void salvarCidade() {
//		var cidade = new Cidade(1L, "São Paulo", 12395372L);
//		cidadeRepository.save(cidade);
//
//		cidade = new Cidade(2L, "Curitiba", 1963726L);
//		cidadeRepository.save(cidade);
//	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
