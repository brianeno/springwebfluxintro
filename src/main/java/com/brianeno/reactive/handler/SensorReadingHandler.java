package com.brianeno.reactive.handler;

import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SensorReadingHandler {

    private final SensorService service;

    public SensorReadingHandler(final SensorService service) {
        this.service = service;
    }

    public Mono<ServerResponse> helloSpringWebFluxMono(ServerRequest request) {
        return this.service.findById(Integer.valueOf(request.pathVariable("id")))
                .flatMap(post -> ServerResponse.ok().body(Mono.just(post), SensorReading.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> helloSpringWebFluxFlux(ServerRequest request) {
        return ServerResponse.ok().body(this.service.findAll(), SensorReading.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}