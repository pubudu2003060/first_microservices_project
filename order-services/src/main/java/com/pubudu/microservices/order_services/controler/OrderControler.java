package com.pubudu.microservices.order_services.controler;

import com.pubudu.microservices.order_services.dto.OrderRequest;
import com.pubudu.microservices.order_services.dto.OrderResponce;
import com.pubudu.microservices.order_services.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderControler {

    private final OrderService orderService;

    public OrderControler(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody  OrderRequest orderRequest) {
         orderService.placeOrder(orderRequest);
         return "Order Placed";
    }
}
