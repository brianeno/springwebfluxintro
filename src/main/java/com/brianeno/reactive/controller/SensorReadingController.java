package com.brianeno.reactive.controller;

import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/readings")
public class SensorReadingController {

    private final SensorService service;

    @GetMapping
    public Flux<SensorReading> getAllUsers() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<SensorReading>> getUserById(@PathVariable Integer id) {
        Mono<SensorReading> readings = this.service.findById(id);
        return readings.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

