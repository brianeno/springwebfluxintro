package com.brianeno.reactive.controller;

import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.model.dto.SensorReadingDto;
import com.brianeno.reactive.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/readings")
public class SensorReadingController {

    private final SensorService service;

    @GetMapping
    public Flux<SensorReadingDto> getAllSensors() {
        List<SensorReading> readings = this.service.findAll();
        List<SensorReadingDto> dtos = SensorReadingDto.convert(readings);
        return Flux.fromIterable(dtos);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<SensorReadingDto>> getSensorById(@PathVariable Integer id) {
        SensorReading reading = service.findById(id);
        SensorReadingDto dto = SensorReadingDto.convert(reading);
        if (reading != null)
            return Mono.just(ResponseEntity.ok(dto));
        else
            return Mono.just(ResponseEntity.notFound().build());
    }
}


