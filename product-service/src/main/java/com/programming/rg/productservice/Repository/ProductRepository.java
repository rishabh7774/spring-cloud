package com.programming.rg.productservice.Repository;

import com.programming.rg.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
