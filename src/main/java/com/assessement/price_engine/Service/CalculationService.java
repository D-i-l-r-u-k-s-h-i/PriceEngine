package com.assessement.price_engine.Service;

import com.assessement.price_engine.DTOs.ProductDTO;
import com.assessement.price_engine.DTOs.ProductPricesDTO;
import com.assessement.price_engine.DTOs.ProductQtyDTO;
import com.assessement.price_engine.Entities.Product;
import com.assessement.price_engine.Repositories.ProductRepository;
import com.assessement.price_engine.Util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class CalculationService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> getProductsWithPrices(){
        List<Product> products=productRepository.findAll();

        List<ProductDTO> allProducts=Utils.mapAll(products,ProductDTO.class);

        for (ProductDTO dto:allProducts) {
            ProductQtyDTO productQtyDTO=new ProductQtyDTO();
            productQtyDTO.setProductId(dto.getProductId());
            productQtyDTO.setQuantityType("UNITS");

            HashMap<Integer,Double> unitPrices=new HashMap<>();
//            IntStream.range(1, 51).parallel.forEach(i -> {
//                productQtyDTO.setQuantity(i);
//                unitPrices.put(i,calculatePricePerProductQty(productQtyDTO));
//            });
            for(int i = 1; i <= 50; i++) {
                productQtyDTO.setQuantity(i);
                unitPrices.put(i,calculatePricePerProductQty(productQtyDTO));
            }
            dto.setUnitPrices(unitPrices);
        }

        return allProducts;
    }

    public double calculatePricePerProductQty(ProductQtyDTO productQtyDTO){
        Product product=productRepository.getByProductId(productQtyDTO.getProductId());

        Map<String, Integer> seperatedQtyTypes= checkQuantityUnits(productQtyDTO,product);

        double totPriceForQtyOfProduct;
        double priceForCartons;
        double priceForUnits=0.0;

        if(seperatedQtyTypes.get("no_of_cartons")>=3){
            //apply discount
            priceForCartons=seperatedQtyTypes.get("no_of_cartons")*product.getPricePerCarton()-(product.getPricePerCarton()*0.1);
        }
        else{
            priceForCartons=seperatedQtyTypes.get("no_of_cartons")*product.getPricePerCarton();
        }
        //(price per unit + percentage increase) * total no. of single units
        if(seperatedQtyTypes.get("no_of_units")!=0){
            priceForUnits=((product.getPricePerCarton()/product.getNoOfUnitsPerCarton()*seperatedQtyTypes.get("no_of_units"))+product.getPricePerCarton()*0.3);
        }

        totPriceForQtyOfProduct=priceForCartons+priceForUnits;
        return totPriceForQtyOfProduct;
    }

    public Map<String, Integer> checkQuantityUnits(ProductQtyDTO productQtyDTO,Product product){
        Map<String, Integer> qtys=new HashMap<>();

        int no_of_units=0;
        int no_of_cartons=0;

        if(productQtyDTO.getQuantityType().equals("UNITS")){
            //div
            no_of_cartons=productQtyDTO.getQuantity()/product.getNoOfUnitsPerCarton();
            //mod
            no_of_units=productQtyDTO.getQuantity()%product.getNoOfUnitsPerCarton();
        }
        else{
            //quantity type is cartons
            no_of_cartons=productQtyDTO.getQuantity();
        }

        qtys.put("no_of_units",no_of_units);
        qtys.put("no_of_cartons",no_of_cartons);

        return qtys;
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products=productRepository.findAll();

        return Utils.mapAll(products,ProductDTO.class);
    }

    public ProductPricesDTO getAmountForProductQuantity(ProductQtyDTO productQtyDTO){
        double amount=calculatePricePerProductQty(productQtyDTO);
        return new ProductPricesDTO(productQtyDTO.getProductId(),amount);
    }

}
