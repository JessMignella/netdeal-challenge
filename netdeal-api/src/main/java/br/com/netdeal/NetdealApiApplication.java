package br.com.netdeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.netdeal"})
@EnableJpaRepositories(basePackages = {"br.com.netdeal.infrastructure.persistence"})
public class NetdealApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetdealApiApplication.class, args);
	}

}
