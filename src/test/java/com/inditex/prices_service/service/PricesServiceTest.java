package com.inditex.prices_service.service;

import com.inditex.prices_service.model.PricesEntity;
import com.inditex.prices_service.repository.PricesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class PricesServiceTest {

    @Mock
    private PricesRepository pricesRepository;

    @InjectMocks
    private PricesService pricesService;

    public PricesServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPrice() {

        PricesEntity price1 = PricesEntity.builder().priceList(1).price(35.50).priority(0).build();
        PricesEntity price2 = PricesEntity.builder().priceList(2).price(25.45).priority(1).build();

        List<PricesEntity> pricesEntityList = Arrays.asList(price2, price1);

        when(pricesRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                anyInt(), anyInt(), any(), any())).thenReturn(pricesEntityList);

        Optional<PricesEntity> result = pricesService.getApplicablePrice(35455, 1,
                LocalDateTime.of(2020, 6, 14, 16, 0));

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
        assertEquals(25.45, result.get().getPrice());
    }

    @Test
    void testGetApplicablePrice_NotFound() {

        when(pricesRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                anyInt(), anyInt(), any(), any())).thenReturn(List.of());

        Optional<PricesEntity> pricesEntity = pricesService.getApplicablePrice(35455, 1,
                LocalDateTime.of(2020, 6, 14, 16, 0));

        assertTrue(pricesEntity.isEmpty());
    }
}
