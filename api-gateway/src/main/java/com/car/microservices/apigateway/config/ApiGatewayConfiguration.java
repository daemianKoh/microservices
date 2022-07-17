package com.car.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
				
		return builder.routes()
				.route(p -> p.path("/car-seller/**")
				.uri("lb://car-seller-service"))
				.route(p -> p.path("/car-buyer/**")
						.uri("lb://car-buyer-service"))
				.route(p -> p.path("/car-inventory/**")
						.uri("lb://car-inventory")).build();
	}
}
