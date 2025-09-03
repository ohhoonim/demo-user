package dev.ohhoomim;

import org.springframework.boot.SpringApplication;

import dev.ohhoonim.DemoUserApplication;

public class TestDemoUserApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoUserApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
