package com.inditex.prices_service.repository;

import com.inditex.prices_service.model.PricesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PricesRepositoryTest {
    
    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void testGetPrice() {

        List<PricesEntity> pricesEntityList = pricesRepository.
                findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455, 1, LocalDateTime.of(2020, 6, 14, 10, 0),
                        LocalDateTime.of(2020, 6, 14, 10, 0));

        assertEquals(1, pricesEntityList.size());
        assertEquals(35.50, pricesEntityList.get(0).getPrice());
        assertEquals(1, pricesEntityList.get(0).getPriceList());
    }

    @Test
    void testGetPriceByPriority() {

        List<PricesEntity> pricesEntityList = pricesRepository.
                findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455, 1, LocalDateTime.of(2020, 6, 14, 16, 0),
                        LocalDateTime.of(2020, 6, 14, 16, 0));

        assertEquals(2, pricesEntityList.get(0).getPriceList());
        assertEquals(25.45, pricesEntityList.get(0).getPrice());
    }
}
