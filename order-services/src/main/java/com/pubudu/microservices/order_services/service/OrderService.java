package com.pubudu.microservices.order_services.service;

import com.pubudu.microservices.order_services.client.InventoryClient;
import com.pubudu.microservices.order_services.dto.OrderRequest;
import com.pubudu.microservices.order_services.model.Order;
import com.pubudu.microservices.order_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public void placeOrder(OrderRequest orderRequest){
        boolean isInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if(isInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with skucode "+orderRequest.skuCode()+" Not in stock");
        }
    }

}
