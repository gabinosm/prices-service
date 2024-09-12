package com.inditex.prices_service.repository;

import com.inditex.prices_service.model.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

    List<PricesEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            int productId, int brandId, LocalDateTime applicationDate, LocalDateTime applicationDateEnd);

}
