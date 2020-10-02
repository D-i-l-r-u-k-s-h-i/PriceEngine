package com.assessement.price_engine.DTOs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode
public class ProductDTO implements Serializable {

    private long productId;

    private String productName;

    private int noOfUnitsPerCarton;

    private double pricePerCarton;

    private HashMap<Integer,Double> unitPrices;
}
