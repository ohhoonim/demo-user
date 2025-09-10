package dev.ohhoonim;

import org.springframework.boot.SpringApplication;

public class TestDemoUserApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoUserApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
