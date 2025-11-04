package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
    @Bean
    @LoadBalanced // @LoadBalanced makes the RestTemplate client-side load-balanced. To make the logical service name work with Eureka and remove the host and port dependencies, if we change port of any service we don't need to worry about it. Integrates with service discovery (Eureka/Consul) and Spring Cloud LoadBalancer to choose an instance and distribute requests. Without it, RestTemplate treats the URL as a literal host:port and no client-side load balancing occurs.
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
