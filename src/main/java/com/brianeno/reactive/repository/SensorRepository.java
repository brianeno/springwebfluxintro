package com.brianeno.reactive.repository;

import com.brianeno.reactive.model.SensorReading;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SensorRepository {

    private static final List<SensorReading> DATA = new ArrayList<>();

    static {
        DATA.add(SensorReading.builder().id(1).timeStamp(LocalDateTime.now()).value("value 1").build());
        DATA.add(SensorReading.builder().id(2).timeStamp(LocalDateTime.now()).value("value 2").build());
        DATA.add(SensorReading.builder().id(3).timeStamp(LocalDateTime.now()).value("value 3").build());
    }

    public Flux<SensorReading> findAll() {
        return Flux.fromIterable(DATA);
    }

    public Mono<SensorReading> findById(Integer id) {
        return findAll().filter(p -> p.getId().equals(id)).last();
    }
}