package com.brianeno.reactive.router;


import com.brianeno.reactive.handler.WebFluxIntroHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebFluxIntroRouter {

    @Bean
    public RouterFunction<ServerResponse> routeHelloWebFlux(WebFluxIntroHandler webFluxIntroHandler) {

        return RouterFunctions.route(RequestPredicates.GET("/readings/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), webFluxIntroHandler::helloSpringWebFluxMono).
                andRoute(RequestPredicates.GET("/readings")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), webFluxIntroHandler::helloSpringWebFluxFlux);
    }
}
