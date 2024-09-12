package com.inditex.prices_service.controller;

import com.inditex.prices_service.exception.MissingParameterException;
import com.inditex.prices_service.exception.PriceNotFoundException;
import com.inditex.prices_service.model.PricesEntity;
import com.inditex.prices_service.service.IPricesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class PricesControllerTest {

    @Mock
    private IPricesService pricesService;

    @InjectMocks
    private PricesController pricesController;

    public PricesControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPrice_Success() {

        PricesEntity pricesEntity = PricesEntity.builder().priceList(1).price(35.50).build();

        when(pricesService.getApplicablePrice(anyInt(), anyInt(), any())).thenReturn(Optional.of(pricesEntity));

        PricesEntity response = pricesController.getPrices(35455, 1, LocalDateTime.parse("2020-06-14T10:00:00"));

        assertEquals(1, response.getPriceList());
        assertEquals(35.50, response.getPrice());
    }

    @Test
    void testGetPrice_NotFound() {

        when(pricesService.getApplicablePrice(anyInt(), anyInt(), any())).thenReturn(Optional.empty());

        try {
            pricesController.getPrices(35455, 1, LocalDateTime.parse("2020-06-14T10:00:00"));
        } catch (PriceNotFoundException e) {
            assertEquals("No prices found by product", e.getMessage());
        }
    }

    @Test
    void testGetPrice_MissingParameters() {

        when(pricesService.getApplicablePrice(anyInt(), anyInt(), any())).thenReturn(Optional.empty());

        try {
            pricesController.getPrices(null, null, null);
        } catch (MissingParameterException e) {
            assertEquals("Missing required query parameters.", e.getMessage());
        }
    }

}
