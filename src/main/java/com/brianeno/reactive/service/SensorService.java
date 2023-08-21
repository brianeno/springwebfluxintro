package com.brianeno.reactive.service;

import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.repository.SensorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SensorService {
    private final SensorRepository repository;

    public SensorService(final SensorRepository repository) {
        this.repository = repository;
    }

    public Flux<SensorReading> findAll() {
        return this.repository.findAll();
    }

    public Mono<SensorReading> findById(Integer id) {
        return this.repository.findById(id);
    }
}
