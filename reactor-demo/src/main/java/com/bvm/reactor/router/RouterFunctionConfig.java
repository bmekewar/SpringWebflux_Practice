package com.bvm.reactor.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bvm.reactor.handler.SampleHandlerFunction;

@Configuration
// Responsible to Map request to appropriate Handler
public class RouterFunctionConfig {

	@Bean
	public RouterFunction<ServerResponse> route(SampleHandlerFunction handlerFunction) {

		return RouterFunctions
				.route(RequestPredicates.GET("/functional/flux")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handlerFunction::flux)
				.andRoute(RequestPredicates.GET("/functional/mono"), handlerFunction::mono);

	}

}
