package com.merikan.cardboard.api.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.merikan.cardboard.services.Item;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardboardControllerTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @Nested
    class match {

        @Test
        void shouldMatch() {
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(6, 7));
                    add(new Item(2, 4));
                    add(new Item(4, 1));
                }
            };

            given()
                .contentType(ContentType.JSON)
                .body(items)
            .when()
                .post("/api/cardboard/match")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(containsString("2"));
        }
        @Test
        void shouldNotMatch() {
            List<Item> items = new ArrayList<>() {
                {
                    add(new Item(12, 7));
                    add(new Item(100, 1));
                }
            };

            given()
                .contentType(ContentType.JSON)
                .body(items)
            .when()
                .post("/api/cardboard/match")
            .then()
                .statusCode(HttpStatus.EXPECTATION_FAILED.value())
                .body(containsString("Upphämtning krävs"));
        }

    }

}
