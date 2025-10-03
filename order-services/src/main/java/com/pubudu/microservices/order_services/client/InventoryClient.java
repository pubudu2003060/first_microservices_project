package com.pubudu.microservices.order_services.client;


import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@Slf4j
public interface InventoryClient {

    @GetExchange("api/inventry")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skucode,@RequestParam Integer quantity);

    default boolean fallbackMethod( String skucode, Integer quantity,Throwable throwable) {
        return false;
    }
}
