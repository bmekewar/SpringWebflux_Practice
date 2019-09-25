package com.bvm.reactor.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebFluxTest
@AutoConfigureWebTestClient
public class SampleHandlerFunctionTest {

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void handlerFlux() {
		Flux<Integer> response = webTestClient.get().uri("/functional/flux").accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange().expectStatus().isOk().returnResult(Integer.class).getResponseBody();

		StepVerifier.create(response.log()).expectNext(1).expectNext(2).expectNext(3).verifyComplete();
	}

	@Test
	public void handlerMono() {
		Integer expected = new Integer(1);
		webTestClient.get().uri("/functional/mono").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus()
				.isOk().expectBody(Integer.class).consumeWith(response -> {
					assertEquals(expected, response.getResponseBody());
				});
	}

}
