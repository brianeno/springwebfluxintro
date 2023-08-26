package com.brianeno.reactive.model.dto;

import com.brianeno.reactive.model.SensorReading;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorReadingDto {
    private Integer id;
    private LocalDateTime timeStamp;
    private String value;

    @JsonIgnore
    public static List<SensorReadingDto> convert(List<SensorReading> readings) {
        if (readings == null) {
            return null;
        }
        List<SensorReadingDto> dtos = new ArrayList<>();
        for (SensorReading sr : readings) {
            SensorReadingDto dto = new SensorReadingDto();
            BeanUtils.copyProperties(sr, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    @JsonIgnore
    public static SensorReadingDto convert(SensorReading reading) {
        if (reading == null) {
            return null;
        }
        SensorReadingDto dto = new SensorReadingDto();
        BeanUtils.copyProperties(reading, dto);
        return dto;
    }
}
