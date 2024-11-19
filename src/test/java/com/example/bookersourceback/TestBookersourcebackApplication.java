package com.example.bookersourceback;

import org.springframework.boot.SpringApplication;

public class TestBookersourcebackApplication {

	public static void main(String[] args) {
		SpringApplication.from(BookersourcebackApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
