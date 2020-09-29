package com.assessement.price_engine.Repositories;

import com.assessement.price_engine.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product getByProductId(long id);
}
