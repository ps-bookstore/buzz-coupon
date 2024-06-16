package com.nhnacademy.couponapi.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("buzz-book-store-openapi")
				.version("1.0")
				.description("buzz-book-store-openapi-ui 화면입니다."));
	}

	@Bean
	public GroupedOpenApi groupedOpenApi() {
		String[] paths = {"/api/coupons/**"};
		String[] packagesToScan = {"com.nhnacademy.couponapi.controller"};
		return GroupedOpenApi.builder()
			.group("buzz-book-store-openapi")
			.pathsToMatch(paths)
			.packagesToScan(packagesToScan)
			.build();
	}
}
