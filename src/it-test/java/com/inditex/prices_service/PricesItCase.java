package com.inditex.prices_service;

import com.inditex.prices_service.model.ApiError;
import com.inditex.prices_service.model.PricesEntity;
import com.inditex.prices_service.repository.PricesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesItCase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void testGetPrice() {
        ResponseEntity<PricesEntity> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/api/prices/search?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00",
                PricesEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getPriceList());
        assertEquals(35.50, response.getBody().getPrice());
        assertEquals("EUR", response.getBody().getCurr());
    }

    @Test
    void testPriceNotFound() {
        ResponseEntity<ApiError> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/api/prices/search?productId=35455&brandId=1&applicationDate=2020-06-10T00:00:00",
                ApiError.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No prices found by product", response.getBody().getMessage());
    }

    @Test
    void testMissingParameters() {
        ResponseEntity<ApiError> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/api/prices/search?",
                ApiError.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Missing required query parameters.", response.getBody().getMessage());
    }
}
