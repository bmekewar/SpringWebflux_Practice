package com.bvm.reactor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void returnFluxTest() {
		Flux<Integer> result= webTestClient.get()
			.uri("/flux")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus()
			.isOk()
			.returnResult(Integer.class)
			.getResponseBody();
		
		StepVerifier.create(result)
			.expectSubscription()
			.expectNextCount(3)
			.verifyComplete();
	}

	@Test
	public void returnFluxTest2() {
		webTestClient.get()
			.uri("/flux")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBodyList(Integer.class)
			.hasSize(3);
	}

	@Test
	public void returnFluxTest3() {
		List<Integer> expected = Arrays.asList(1,2,3);
		
		EntityExchangeResult<List<Integer>> result = webTestClient.get()
			.uri("/flux")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBodyList(Integer.class)
			.returnResult();
		
		assertEquals(expected, result.getResponseBody());
	}
	
	@Test
	public void returnFluxTest4() {
		List<Integer> expected = Arrays.asList(1,2,3);
		
		webTestClient.get()
			.uri("/flux")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBodyList(Integer.class)
			.consumeWith(response -> {
				assertEquals(expected, response.getResponseBody());
			});
	}
	
	@Test
	public void returnFluxStream() {
		
		Flux<Long> result= webTestClient.get()
				.uri("/fluxstream2")
				//.accept(MediaType.APPLICATION_STREAM_JSON_VALUE)
				.exchange()
				.expectStatus()
				.isOk()
				.returnResult(Long.class)
				.getResponseBody();

		StepVerifier.create(result)
		.expectSubscription()
		.expectNextCount(3)
		.thenCancel()
		.verify();
	}
	

	@Test
	public void returnMonoTest() {
		
		Integer expected = new Integer(1);
		
		webTestClient.get().uri("/mono")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(Integer.class)
			.consumeWith(response -> {
				assertEquals(expected, response.getResponseBody());
			});
	}
}
