package com.assessement.price_engine.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductQtyDTO implements Serializable {
    private long productId;

    private String quantityType;

    private int quantity;
}
