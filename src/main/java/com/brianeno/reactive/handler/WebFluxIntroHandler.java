package com.brianeno.reactive.handler;

import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.model.dto.SensorReadingDto;
import com.brianeno.reactive.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class WebFluxIntroHandler {

    private final SensorService service;

    public WebFluxIntroHandler(final SensorService service) {
        this.service = service;
    }

    public Mono<ServerResponse> helloSpringWebFluxMono(ServerRequest request) {
        SensorReading sensorReading = this.service.findById(Integer.valueOf(request.pathVariable("id")));
        SensorReadingDto sensorReadingDto = SensorReadingDto.convert(sensorReading);
        if (sensorReading != null) {
            return ServerResponse.ok().body(Mono.just(sensorReadingDto), SensorReadingDto.class);
        } else {
            return ServerResponse.notFound().build();
        }
    }

    public Mono<ServerResponse> helloSpringWebFluxFlux(ServerRequest request) {

        List<SensorReading> readings = this.service.findAll();
        List<SensorReadingDto> dtos = SensorReadingDto.convert(readings);
        return ServerResponse.ok().body(Flux.just(dtos), List.class);
    }
}