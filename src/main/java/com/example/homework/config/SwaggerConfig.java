package com.example.homework.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(info())
                .paths(new Paths().addPathItem("/employees/**", new PathItem()))
                .paths(new Paths().addPathItem("/inserters/**", new PathItem()));

    }

    private Info info() {
        return new Info()
                .title("EmployeeSalary API")
                .description("gives information about employee salaries")
                .version("1.0.0")
                .contact(new Contact().name("Givi Khutsishvili")
                        .email("givi_khutsishvili@epam.com")
                        .url("www.epam.com")).license(new License()
                        .name("epam")
                        .url("www.epam.com"));


    }
}