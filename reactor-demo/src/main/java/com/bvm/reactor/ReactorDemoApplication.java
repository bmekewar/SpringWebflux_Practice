package com.bvm.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactorDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactorDemoApplication.class, args);
	}

	public void test() {
		Flux.just("Spring", "Spring Boot", "Reactive Spring Boot").map(s -> s.concat("flux"))
				.subscribe(System.out::println);

		Mono.just("Spring").map(s -> s.concat("flux")).subscribe(System.out::println);
	}
}
