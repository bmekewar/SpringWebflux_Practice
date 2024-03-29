package com.bvm.reactor.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FluxAndMonoController {

	@GetMapping("/flux")
	public Flux<Integer> returnFlux() {
		return Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1)).log();
	}

	@GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> returnFluxStream() {
		return Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1)).log();
	}

	@GetMapping(value = "/fluxstream2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> returnFluxStream2() {
		return Flux.interval(Duration.ofSeconds(1)).log();
	}
	
	@GetMapping("/mono")
	public Mono<Integer> returnMono(){
		return Mono.just(1)
				.log();
	}

}
