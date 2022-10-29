package com.gateway.discovery;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/get")
                        .filters(p-> p
                                .addRequestHeader("token", "hghghhhgjghghas")
                                .addRequestParameter("cpf", "89009844390"))
                        .uri("http://httpbin.org:80"))
                .route(p-> p
                        .path("/boletos/**")
                        .uri("lb://ms-boleto"))
                .route(p-> p
                        .path("/contas/**")
                        .uri("lb://ms-conta"))
                .route(p -> p
                        .path("/transferencias/**")
                        .uri("lb:/ms-transferencia/"))
                .build();
    }
}
