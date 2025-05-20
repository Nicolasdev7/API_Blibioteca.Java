package com.biblioteca.biblioteca.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("spring-biblioteca")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI bibliotecaOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Biblioteca")
                .description("Documentação da API do sistema de biblioteca")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Seu Nome")
                    .email("seu@email.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
