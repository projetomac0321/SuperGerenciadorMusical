package com.mac0321.SuperGerenciadorMusical;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuperGerenciadorMusicalApplication {
	
	@Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 0;
    }
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SuperGerenciadorMusicalApplication.class, args);
		
	}

}
