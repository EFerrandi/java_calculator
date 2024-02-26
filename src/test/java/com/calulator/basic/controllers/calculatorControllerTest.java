package com.calulator.basic.controllers;

import com.calulator.basic.BasicCalculatorApplication;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
//import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
//import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ContextConfiguration(classes = BasicCalculatorApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorControllerTest {

//    @Autowired
//    ApplicationContext context;

    @Autowired
    private WebTestClient webTestClient;

    private String buildUri(String path){
        return "calculator" + path;
    }

//    @BeforeEach
//    public void setup() {
//        this.webTestClient = WebTestClient
//                .bindToApplicationContext(this.context)
//                // add Spring Security test Support
//                .apply(springSecurity())
//                .configureClient()
//                .filter(basicAuthentication("user", "password"))
//                .build();
//    }

    @Test
    void getLastResult() {
        webTestClient.get().uri(buildUri("/result")).exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class).value(number -> {
                    assertEquals(0, number);
                });
    }

    @Test
    void addition() {
        List<Integer> integers= List.of(1,2,3);

        webTestClient
                //.mutateWith(csrf())
                .post().uri(buildUri("/addition"))
                .bodyValue(integers).exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class).value(number -> {
                    assertEquals(6, number);
                });
    }
}