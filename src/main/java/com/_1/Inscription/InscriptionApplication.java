package com._1.Inscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com._1.Inscription.proxy")
public class InscriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InscriptionApplication.class, args);
	}

}
