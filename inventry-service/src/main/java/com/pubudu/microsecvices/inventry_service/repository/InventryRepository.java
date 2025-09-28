package com.pubudu.microsecvices.inventry_service.repository;

import com.pubudu.microsecvices.inventry_service.model.Inventry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventryRepository extends JpaRepository<Inventry,Long> {
    boolean existsInventryBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantityIsGreaterThan);
}
