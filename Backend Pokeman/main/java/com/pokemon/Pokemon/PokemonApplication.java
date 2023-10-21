package com.pokemon.Pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokemonApplication {

	public static void main(String[] args) {

		System.out.println("main");
		SpringApplication.run(PokemonApplication.class, args);
	}

}
