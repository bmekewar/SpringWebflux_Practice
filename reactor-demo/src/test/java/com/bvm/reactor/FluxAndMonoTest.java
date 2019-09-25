package com.bvm.reactor;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

	@Test
	public void fluxTest() {

		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring Boot")
				// .concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.concatWith(Flux.just("After error nothing will be sent")) // wont be executed after exception
				.log();

		stringFlux.subscribe(System.out::println, (e) -> System.err.println("Exception is:" + e),
				() -> System.out.println("Completed"));

		// Flux.just("Spring", "Spring Boot", "Reactive Spring Boot").map(s ->
		// s.concat("flux")).subscribe(System.out::println);
	}

	@Test
	public void FlexWithoutError() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring Boot").log();

		StepVerifier.create(stringFlux).expectNext("Spring").expectNext("Spring Boot")
				.expectNext("Reactive Spring Boot");// .verifyComplete();
	}

	@Test
	public void FlexWithError() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring Boot")
				.concatWith(Flux.error(new RuntimeException("Exception occured"))).log();

		StepVerifier.create(stringFlux).expectNext("Spring").expectNext("Spring Boot")
				.expectNext("Reactive Spring Boot")
				// .expectError(RuntimeException.class)
				.expectErrorMessage("Exception occured").verify();
		// .verifyComplete();
	}

	@Test
	public void FlexElementsCount() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring Boot")
				.concatWith(Flux.error(new RuntimeException("Exception occured"))).log();

		StepVerifier.create(stringFlux)
				// .expectNext("Spring", "Spring Boot", "Reactive Spring Boot")
				.expectNextCount(3)
				// .expectError(RuntimeException.class)
				.expectErrorMessage("Exception occured").verify();
		// .verifyComplete();
	}

	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("Mono").log();

		StepVerifier.create(stringMono.log()).expectNext("Mono").verifyComplete();
		// .map(s -> s.concat("flux")).subscribe(System.out::println);
	}

	@Test
	public void monoTest_Error() {
		StepVerifier.create(Mono.error(new RuntimeException("Exception Occured")).log())
				.expectError(RuntimeException.class).verify();
		// .map(s -> s.concat("flux")).subscribe(System.out::println);
	}

}
