package com.hanhwa_tae.firstserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info()
                .title("걸한 API 테스트")
                .description("걸한 Swagger 테스트")
                .version("1.0.0");
    }

}