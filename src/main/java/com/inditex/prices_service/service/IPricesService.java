package com.inditex.prices_service.service;

import com.inditex.prices_service.model.PricesEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IPricesService {

    public Optional<PricesEntity> getApplicablePrice(int productId, int brandId, LocalDateTime applicationDate);

}
