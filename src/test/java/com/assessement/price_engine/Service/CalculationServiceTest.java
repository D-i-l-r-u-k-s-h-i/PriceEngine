package com.assessement.price_engine.Service;

import com.assessement.price_engine.DTOs.ProductQtyDTO;
import com.assessement.price_engine.Entities.Product;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    @Test
    void calculatePricePerProductQty() {
        ProductQtyDTO productQtyDTO=new ProductQtyDTO();
        productQtyDTO.setProductId(1);
        productQtyDTO.setQuantity(25);
        productQtyDTO.setQuantityType("UNITS");

        CalculationService calcService=new CalculationService();
        double actual=calcService.calculatePricePerProductQty(productQtyDTO);
        double expected=271.25;

        assertEquals(expected,actual);

    }

    @Test
    void checkQuantityUnits() {
        ProductQtyDTO productQtyDTO=new ProductQtyDTO();
        productQtyDTO.setProductId(1);
        productQtyDTO.setQuantity(25);
        productQtyDTO.setQuantityType("UNITS");

        Product product=new Product();
        product.setProductId(1);
        product.setProductName("Penguin-ears");
        product.setNoOfUnitsPerCarton(20);
        product.setPricePerCarton(175.0);

        CalculationService calcService=new CalculationService();
        Map<String, Integer> actual=calcService.checkQuantityUnits(productQtyDTO,product);

        Map<String, Integer> expected= new HashMap<>();
        expected.put("no_of_units",5);
        expected.put("no_of_cartons",1);

        assertEquals(expected,actual);
    }
}