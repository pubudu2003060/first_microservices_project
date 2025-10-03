package com.pubudu.microservices.order_services.config;

import com.pubudu.microservices.order_services.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventryUrl ;

    @Bean
    public InventoryClient inventoryClient() {
        RestClient restclient = RestClient.builder()
                .baseUrl(inventryUrl)
                .requestFactory(clientHttpRequestFactory())
                .build();
        var restClientAdapter =  RestClientAdapter.create(restclient);
        var httpServiceProxyFactory =  HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) Duration.ofSeconds(3).toMillis());
        factory.setReadTimeout((int) Duration.ofSeconds(3).toMillis());
        return factory;
    }
}
