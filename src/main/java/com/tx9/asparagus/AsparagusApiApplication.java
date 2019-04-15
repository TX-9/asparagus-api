package com.tx9.asparagus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class AsparagusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsparagusApiApplication.class, args);
	}

}
