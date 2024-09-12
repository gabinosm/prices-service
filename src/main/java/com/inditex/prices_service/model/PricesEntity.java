package com.inditex.prices_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Getter
@Setter
@ToString
@Entity
@Table(name = "prices")
public class PricesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price_list")
    private int priceList;

    @Column(name = "product_id")
    private int productId;

    private int priority;

    private double price;

    private String curr;
}
