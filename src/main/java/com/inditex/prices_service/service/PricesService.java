package com.inditex.prices_service.service;

import com.inditex.prices_service.model.PricesEntity;
import com.inditex.prices_service.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService implements IPricesService {

    @Autowired
    private PricesRepository pricesRepository;

    public Optional<PricesEntity> getApplicablePrice(int productId, int brandId, LocalDateTime applicationDate) {
        List<PricesEntity> prices =
                pricesRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productId, brandId, applicationDate, applicationDate);
        return prices.stream().findFirst();
    }
}
