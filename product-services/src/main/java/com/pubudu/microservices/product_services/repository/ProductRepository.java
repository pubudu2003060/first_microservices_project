package com.pubudu.microservices.product_services.repository;

import com.pubudu.microservices.product_services.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,Integer> {
}
