package com.pubudu.microsecvices.inventry_service.controler;

import com.pubudu.microsecvices.inventry_service.service.InventryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventry")
public class InventryControler {

    private final InventryService inventryService;

    public InventryControler(InventryService inventryService) {
        this.inventryService = inventryService;
    }

    @GetMapping
    public boolean inInStock(@RequestParam("skucode") String skuCode,@RequestParam("quantity") Integer quantity) {
        return inventryService.isInStock(skuCode, quantity);
    }
}
