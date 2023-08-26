package com.brianeno.reactive.repository;

import com.brianeno.reactive.model.SensorReading;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SensorRepository {

    private static final List<SensorReading> DATA = new ArrayList<>();

    static {
        DATA.add(SensorReading.builder().id(1).timeStamp(LocalDateTime.now()).value("value 1")
                .dateCreated(LocalDateTime.now(ZoneOffset.UTC)).build());
        DATA.add(SensorReading.builder().id(2).timeStamp(LocalDateTime.now()).value("value 2")
                .dateCreated(LocalDateTime.now(ZoneOffset.UTC)).build());
        DATA.add(SensorReading.builder().id(3).timeStamp(LocalDateTime.now()).value("value 3")
                .dateCreated(LocalDateTime.now(ZoneOffset.UTC)).build());
        DATA.add(SensorReading.builder().id(4).timeStamp(LocalDateTime.now()).value("value 4")
                .dateCreated(LocalDateTime.now(ZoneOffset.UTC)).build());
    }

    public List<SensorReading> findAll() {
        return DATA;
    }

    public SensorReading findById(Integer id) {
        Optional<SensorReading> vals = findAll().stream().filter(p -> p.getId().equals(id)).findAny();
        if (vals.isPresent()) {
            return vals.get();
        } else {
            return null;
        }
    }
}