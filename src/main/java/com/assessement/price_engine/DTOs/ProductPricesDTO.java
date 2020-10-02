package com.assessement.price_engine.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductPricesDTO {

    private long productId;

    private double amount;
}
