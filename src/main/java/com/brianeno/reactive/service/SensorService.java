package com.brianeno.reactive.service;

import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    private final SensorRepository repository;

    public SensorService(final SensorRepository repository) {
        this.repository = repository;
    }

    public List<SensorReading> findAll() {
        return this.repository.findAll();
    }

    public SensorReading findById(Integer id) {
        return this.repository.findById(id);
    }
}
