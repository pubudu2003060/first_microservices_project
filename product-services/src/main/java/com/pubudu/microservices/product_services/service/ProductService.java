package com.pubudu.microservices.product_services.service;

import com.pubudu.microservices.product_services.dto.ProductRequest;
import com.pubudu.microservices.product_services.dto.ProductResponce;
import com.pubudu.microservices.product_services.model.Product;
import com.pubudu.microservices.product_services.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponce createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        String message = String.format("Product %s created", product.getName());
        log.info(message);
        return new ProductResponce(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    public List<ProductResponce> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(product -> new ProductResponce(product.getId(),product.getName(),product.getDescription(),product.getPrice()))
                .toList();
    }


}
