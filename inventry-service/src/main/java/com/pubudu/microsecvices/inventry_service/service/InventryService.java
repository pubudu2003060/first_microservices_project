package com.pubudu.microsecvices.inventry_service.service;

import com.pubudu.microsecvices.inventry_service.repository.InventryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventryService {

    private final InventryRepository inventryRepository;

    public InventryService(InventryRepository inventryRepository) {
        this.inventryRepository = inventryRepository;
    }

    public boolean isInStock(String skuCode,Integer quantity) {
        return inventryRepository.existsInventryBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
