package com.system.pagos;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SyspagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyspagosApplication.class, args);
	}

	@Bean
	public ModelMapper loadMapper(){
		return new ModelMapper();
	}
}
