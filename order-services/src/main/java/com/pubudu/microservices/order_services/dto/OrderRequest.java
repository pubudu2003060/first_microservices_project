package com.pubudu.microservices.order_services.dto;

import java.math.BigDecimal;


public record OrderRequest(
         String skuCode,
         BigDecimal price,
         Integer quantity
){}
