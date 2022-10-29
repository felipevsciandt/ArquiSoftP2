package com.myenglish.namingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingserverApplication.class, args);
	}

}
