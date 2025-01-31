package fr.efrei.pokemon_tcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class PokemonTcgApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonTcgApplication.class, args);
	}

	@Bean
	Server h2Server() {
		Server server = new Server();

		try {
			server.runTool("-tcp");
			server.runTool("-tcpAllowOthers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return server;

	}
}