package com.tx9.asparagus.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tx9.asparagus.model.Meal;
import com.tx9.asparagus.repository.MealRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(MealRepository repository) {
		return args -> {
			log.info("PRELOADING " + repository.save(new Meal("Bacon Wrapped Asparagus")));
			log.info("PRELOADING " + repository.save(new Meal("Chiecken Wings")));
			log.info("PRELOADING " + repository.save(new Meal("Shrimp Cream Pasta")));
			
			repository.findAll().forEach(meal -> log.info("PRELOADED " + meal));
		};
		
	}
}
