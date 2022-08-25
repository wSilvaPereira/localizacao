package io.github.wSilvaPereira.localizacao;

import io.github.wSilvaPereira.localizacao.domain.entity.Cidade;
import io.github.wSilvaPereira.localizacao.domain.repository.CidadeRepository;
import io.github.wSilvaPereira.localizacao.service.CidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadesService cidadesService;

	@Override
	public void run(String... args) throws Exception {
		Cidade cidade = new Cidade(null, "Curitiba", null);
		cidadesService.filtroDinamico(cidade).forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
