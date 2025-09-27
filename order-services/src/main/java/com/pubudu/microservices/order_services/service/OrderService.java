package com.pubudu.microservices.order_services.service;

import com.pubudu.microservices.order_services.dto.OrderRequest;
import com.pubudu.microservices.order_services.dto.OrderResponce;
import com.pubudu.microservices.order_services.model.Orders;
import com.pubudu.microservices.order_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public OrderResponce placeOrder(OrderRequest orderRequest){
        Orders order = modelMapper.map(orderRequest, Orders.class);
        orderRepository.save(order);
        return modelMapper.map(order, OrderResponce.class);
    }
}
