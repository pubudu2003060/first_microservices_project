package com.pubudu.microservices.order_services.service;

import com.pubudu.microservices.order_services.client.InventoryClient;
import com.pubudu.microservices.order_services.dto.OrderRequest;
import com.pubudu.microservices.order_services.event.OrderPlaceEvent;
import com.pubudu.microservices.order_services.model.Order;
import com.pubudu.microservices.order_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String,OrderPlaceEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.kafkaTemplate = kafkaTemplate;
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

            //send message to kafka topic
            OrderPlaceEvent orderPlaceEvent = new OrderPlaceEvent(order.getOrderNumber(),orderRequest.userDetails().email());
            log.info("Start- Sending OrderPlacedEvent {} to Kafka Topic", orderPlaceEvent);
            kafkaTemplate.send("order-placed", orderPlaceEvent);
            log.info("End- Sending OrderPlacedEvent {} to Kafka Topic", orderPlaceEvent);

        }else{
            throw new RuntimeException("Product with skucode "+orderRequest.skuCode()+" Not in stock");
        }
    }

}
