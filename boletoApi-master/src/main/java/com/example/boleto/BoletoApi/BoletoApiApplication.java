package com.example.boleto.BoletoApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BoletoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletoApiApplication.class, args);
	}

}
