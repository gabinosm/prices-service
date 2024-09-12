package com.inditex.prices_service.controller;

import com.inditex.prices_service.exception.MissingParameterException;
import com.inditex.prices_service.exception.PriceNotFoundException;
import com.inditex.prices_service.model.PricesEntity;
import com.inditex.prices_service.service.IPricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PricesController {

    @Autowired
    private IPricesService pricesService;

    @Operation(summary = "Search price by product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price by product" ),
            @ApiResponse(responseCode = "404", description = "Price not found" ),
            @ApiResponse(responseCode = "400", description = "Wrong request parameters" ),
            @ApiResponse(responseCode = "500", description = "Internal error" )
    })
    @GetMapping("/search")
    public PricesEntity getPrices(
            @RequestParam(value = "productId",required = false) Integer productId,
            @RequestParam(value = "brandId", required = false) Integer brandId,
            @RequestParam(value = "applicationDate",required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        if (productId == null || brandId == null || applicationDate == null) {
            throw new MissingParameterException("Missing required query parameters.");
        }

        Optional<PricesEntity> prices = pricesService.getApplicablePrice(productId, brandId, applicationDate);

        return prices.orElseThrow(() -> new PriceNotFoundException("No prices found by product"));
    }
}
