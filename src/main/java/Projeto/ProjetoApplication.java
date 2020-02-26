package Projeto;

import Projeto.domain.*;
import Projeto.domain.enums.EstadoPagamento;
import Projeto.domain.enums.TipoCliente;
import Projeto.repositories.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {



	}
}
