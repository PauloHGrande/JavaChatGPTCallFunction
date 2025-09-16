package com.example.chatgptoracle.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ChatGPT Oracle API")
                        .version("1.0")
                        .description("Consulta Oracle usando ChatGPT Function-Calling"));
    }
}
