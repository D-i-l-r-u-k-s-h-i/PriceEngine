package com.assessement.price_engine.Controller;

import com.assessement.price_engine.Service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/calculate")
@Controller
public class CalculationController {

    @Autowired
    CalculationService calculationService;

    @RequestMapping(value = "/products",method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProducts(){

        return ResponseEntity.ok(calculationService.getProductsWithPrices());
    }

//    @RequestMapping(value = "/perqty/{id}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> pricePerQty(@PathVariable(name = "id") long id) throws Exception {
//
//        return ResponseEntity.ok("");
//    }
}
