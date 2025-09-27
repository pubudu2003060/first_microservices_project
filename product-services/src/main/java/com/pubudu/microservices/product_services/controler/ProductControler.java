package com.pubudu.microservices.product_services.controler;

import com.pubudu.microservices.product_services.dto.ProductRequest;
import com.pubudu.microservices.product_services.dto.ProductResponce;
import com.pubudu.microservices.product_services.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductControler {

    ProductService productService;

    public ProductControler(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponce createProduct(@RequestBody ProductRequest productDTO){
        return productService.createProduct(productDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponce> getAllProducts(){
        return  productService.getAllProducts();
    }
}
