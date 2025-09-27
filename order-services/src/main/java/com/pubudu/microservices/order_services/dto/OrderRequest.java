package com.pubudu.microservices.order_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest{
         String orderNumber;
         String skuName;
         BigDecimal price;
         Integer quantity;
}
