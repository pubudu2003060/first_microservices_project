package com.pubudu.microsecvices.inventry_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI inventoryServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Inventry Service API")
                        .description("This is the REST API for Inventry Service")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Inventry Service Wiki Documentation")
                        .url("https://Inventry-service-dummy-url.com/docs"));
    }

}
