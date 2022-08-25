package io.github.wSilvaPereira.localizacao;

import io.github.wSilvaPereira.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		listarCidadesPorNome();
	}

	private void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}

	private void listarCidadesPorNome() {
		cidadeRepository.findByNomeLikeInsensitive("%s%").forEach(System.out::println);
	}

	private void listarCidadesPorHabitantes() {
		cidadeRepository.findByHabitantes(12396372L).forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
