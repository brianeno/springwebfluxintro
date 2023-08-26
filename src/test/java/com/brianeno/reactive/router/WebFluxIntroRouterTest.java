package com.brianeno.reactive.router;

import com.brianeno.reactive.ReactiveApplication;
import com.brianeno.reactive.handler.WebFluxIntroHandler;
import com.brianeno.reactive.model.SensorReading;
import com.brianeno.reactive.model.dto.SensorReadingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ReactiveApplication.class)
class WebFluxIntroRouterTest {

    @Autowired
    private WebFluxIntroRouter config;

    @Autowired
    private WebFluxIntroHandler handler;

    @Test
    void givenReadingId_whenGetReadingById_thenCorrectReading() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(config.getAllSensorRoutes(handler))
                .build();

        client.get()
                .uri("/v1/readings/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(SensorReadingDto.class);
    }

    @Test
    void givenReadings_whenGetAllReading_thenCorrectReadings() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(config.getAllSensorRoutes(handler))
                .build();

        client.get()
                .uri("/v1/readings")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(List.class);
    }
}