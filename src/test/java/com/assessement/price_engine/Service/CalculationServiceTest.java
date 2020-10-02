package com.assessement.price_engine.Service;

import com.assessement.price_engine.DTOs.ProductDTO;
import com.assessement.price_engine.DTOs.ProductPricesDTO;
import com.assessement.price_engine.DTOs.ProductQtyDTO;
import com.assessement.price_engine.Entities.Product;
import com.assessement.price_engine.Repositories.ProductRepository;
import com.assessement.price_engine.Util.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CalculationServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @InjectMocks
    private CalculationService calculationService;

    @Test
    void calculatePricePerProductQty() {
        ProductQtyDTO productQtyDTO=new ProductQtyDTO();
        productQtyDTO.setProductId(1);
        productQtyDTO.setQuantity(25);
        productQtyDTO.setQuantityType("UNITS");

        Product product=new Product();
        product.setProductId(1);
        product.setProductName("Penguin-ears");
        product.setNoOfUnitsPerCarton(20);
        product.setPricePerCarton(175.0);

        Mockito.when(productRepository.getByProductId(productQtyDTO.getProductId())).thenReturn(product);

        double actual=calculationService.calculatePricePerProductQty(productQtyDTO);
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

        Map<String, Integer> actual=calculationService.checkQuantityUnits(productQtyDTO,product);

        Map<String, Integer> expected= new HashMap<>();
        expected.put("no_of_units",5);
        expected.put("no_of_cartons",1);

        assertEquals(expected,actual);
    }

    @Test
    void getProductsWithPrices(){
        List<Product> expectedProductList=new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        String json1="{\n" +
                "            \"1\": 61.25,\n" +
                "            \"2\": 70.0,\n" +
                "            \"3\": 78.75,\n" +
                "            \"4\": 87.5,\n" +
                "            \"5\": 96.25,\n" +
                "            \"6\": 105.0,\n" +
                "            \"7\": 113.75,\n" +
                "            \"8\": 122.5,\n" +
                "            \"9\": 131.25,\n" +
                "            \"10\": 140.0,\n" +
                "            \"11\": 148.75,\n" +
                "            \"12\": 157.5,\n" +
                "            \"13\": 166.25,\n" +
                "            \"14\": 175.0,\n" +
                "            \"15\": 183.75,\n" +
                "            \"16\": 192.5,\n" +
                "            \"17\": 201.25,\n" +
                "            \"18\": 210.0,\n" +
                "            \"19\": 218.75,\n" +
                "            \"20\": 227.5,\n" +
                "            \"21\": 236.25,\n" +
                "            \"22\": 245.0,\n" +
                "            \"23\": 253.75,\n" +
                "            \"24\": 262.5,\n" +
                "            \"25\": 271.25,\n" +
                "            \"26\": 280.0,\n" +
                "            \"27\": 288.75,\n" +
                "            \"28\": 297.5,\n" +
                "            \"29\": 306.25,\n" +
                "            \"30\": 315.0,\n" +
                "            \"31\": 323.75,\n" +
                "            \"32\": 332.5,\n" +
                "            \"33\": 341.25,\n" +
                "            \"34\": 350.0,\n" +
                "            \"35\": 358.75,\n" +
                "            \"36\": 367.5,\n" +
                "            \"37\": 376.25,\n" +
                "            \"38\": 385.0,\n" +
                "            \"39\": 393.75,\n" +
                "            \"40\": 402.5,\n" +
                "            \"41\": 411.25,\n" +
                "            \"42\": 420.0,\n" +
                "            \"43\": 428.75,\n" +
                "            \"44\": 437.5,\n" +
                "            \"45\": 446.25,\n" +
                "            \"46\": 455.0,\n" +
                "            \"47\": 463.75,\n" +
                "            \"48\": 472.5,\n" +
                "            \"49\": 481.25,\n" +
                "            \"50\": 490.0\n" +
                "        }";
        HashMap<Integer,Double> unitPrices_p1=new HashMap<>();
        try {
            unitPrices_p1=mapper.readValue(json1,new TypeReference<HashMap<Integer,Double>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product p1=new Product();
        p1.setProductId(1);
        p1.setProductName("Penguin-ears");
        p1.setNoOfUnitsPerCarton(20);
        p1.setPricePerCarton(175.0);

        String json2="{\n" +
                "            \"1\": 412.5,\n" +
                "            \"2\": 577.5,\n" +
                "            \"3\": 742.5,\n" +
                "            \"4\": 907.5,\n" +
                "            \"5\": 1072.5,\n" +
                "            \"6\": 1237.5,\n" +
                "            \"7\": 1402.5,\n" +
                "            \"8\": 1567.5,\n" +
                "            \"9\": 1732.5,\n" +
                "            \"10\": 1897.5,\n" +
                "            \"11\": 2062.5,\n" +
                "            \"12\": 2227.5,\n" +
                "            \"13\": 2392.5,\n" +
                "            \"14\": 2557.5,\n" +
                "            \"15\": 2640.0,\n" +
                "            \"16\": 2805.0,\n" +
                "            \"17\": 2970.0,\n" +
                "            \"18\": 3135.0,\n" +
                "            \"19\": 3300.0,\n" +
                "            \"20\": 3465.0,\n" +
                "            \"21\": 3630.0,\n" +
                "            \"22\": 3795.0,\n" +
                "            \"23\": 3960.0,\n" +
                "            \"24\": 4125.0,\n" +
                "            \"25\": 4290.0,\n" +
                "            \"26\": 4455.0,\n" +
                "            \"27\": 4620.0,\n" +
                "            \"28\": 4785.0,\n" +
                "            \"29\": 4950.0,\n" +
                "            \"30\": 5115.0,\n" +
                "            \"31\": 5280.0,\n" +
                "            \"32\": 5445.0,\n" +
                "            \"33\": 5610.0,\n" +
                "            \"34\": 5775.0,\n" +
                "            \"35\": 5940.0,\n" +
                "            \"36\": 6105.0,\n" +
                "            \"37\": 6270.0,\n" +
                "            \"38\": 6435.0,\n" +
                "            \"39\": 6600.0,\n" +
                "            \"40\": 6765.0,\n" +
                "            \"41\": 6930.0,\n" +
                "            \"42\": 7095.0,\n" +
                "            \"43\": 7260.0,\n" +
                "            \"44\": 7425.0,\n" +
                "            \"45\": 7590.0,\n" +
                "            \"46\": 7755.0,\n" +
                "            \"47\": 7920.0,\n" +
                "            \"48\": 8085.0,\n" +
                "            \"49\": 8250.0,\n" +
                "            \"50\": 8415.0\n" +
                "        }";

        HashMap<Integer,Double> unitPrices_p2=new HashMap<>();
        try {
            unitPrices_p2=mapper.readValue(json2,new TypeReference<HashMap<Integer,Double>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product p2=new Product();
        p2.setProductId(2);
        p2.setProductName("Horseshoe");
        p2.setNoOfUnitsPerCarton(5);
        p2.setPricePerCarton(825.0);

        expectedProductList.add(p1);
        expectedProductList.add(p2);

        Mockito.when(productRepository.getByProductId(1)).thenReturn(p1);
        Mockito.when(productRepository.getByProductId(2)).thenReturn(p2);

        Mockito.when(productRepository.findAll()).thenReturn(expectedProductList);

        List<ProductDTO> expectedProductDTOList= Utils.mapAll(expectedProductList,ProductDTO.class);
        for (ProductDTO pdto:expectedProductDTOList) {
            if(pdto.getProductId()==1){
                pdto.setUnitPrices(unitPrices_p1);
            }
            else if(pdto.getProductId()==2){
                pdto.setUnitPrices(unitPrices_p2);
            }
        }

        List<ProductDTO> actualProductsist=calculationService.getProductsWithPrices();

        //failing due to different hashcodes given to objects inside the hash map, the solution is the same when debugged
        assertIterableEquals(expectedProductDTOList,actualProductsist);
    }

    @Test
    void getAllProducts() {
        List<Product> expectedProductList=new ArrayList<>();
        Product p1=new Product();
        p1.setProductId(1);
        p1.setProductName("Penguin-ears");
        p1.setNoOfUnitsPerCarton(20);
        p1.setPricePerCarton(175.0);

        Product p2=new Product();
        p2.setProductId(2);
        p2.setProductName("Horseshoe");
        p2.setNoOfUnitsPerCarton(5);
        p2.setPricePerCarton(825.0);

        expectedProductList.add(p1);
        expectedProductList.add(p2);

        Mockito.when(productRepository.findAll()).thenReturn(expectedProductList);

        List<ProductDTO> expectedProductDTOList= Utils.mapAll(expectedProductList,ProductDTO.class);
        List<ProductDTO> actualProductsist=calculationService.getAllProducts();

        assertArrayEquals(expectedProductDTOList.toArray(),actualProductsist.toArray());
    }

    @Test
    void getAmountForProductQuantity() {
        ProductQtyDTO productQtyDTO=new ProductQtyDTO();
        productQtyDTO.setProductId(1);
        productQtyDTO.setQuantity(25);
        productQtyDTO.setQuantityType("UNITS");

        Product product=new Product();
        product.setProductId(1);
        product.setProductName("Penguin-ears");
        product.setNoOfUnitsPerCarton(20);
        product.setPricePerCarton(175.0);

        Mockito.when(productRepository.getByProductId(productQtyDTO.getProductId())).thenReturn(product);

        ProductPricesDTO expected = new ProductPricesDTO(1,271.25);
        ProductPricesDTO actual = calculationService.getAmountForProductQuantity(productQtyDTO);

        assertEquals(expected,actual);
    }
}