package com.pubudu.microsecvices.inventry_service;

import org.springframework.boot.SpringApplication;

public class TestInventryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
