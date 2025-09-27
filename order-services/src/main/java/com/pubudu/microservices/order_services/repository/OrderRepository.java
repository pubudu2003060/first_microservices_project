package com.pubudu.microservices.order_services.repository;

import com.pubudu.microservices.order_services.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
