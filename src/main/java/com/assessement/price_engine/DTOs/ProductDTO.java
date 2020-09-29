package com.assessement.price_engine.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
public class ProductDTO implements Serializable {

    private long productId;

    private String productName;

    private int noOfUnitsPerCarton;

    private double pricePerCarton;

    private HashMap<Integer,Double> unitPrices;
}
