package com.springboot.blogapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Blog API",
                version = "1.0",
                description = "Blog Backend API",
                contact = @Contact(
                        name = "Hargun Bedi",
                        url = "http://hargunbedi.netlify.app"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Blog API Documentation",
                url = "https://www.github.com/Hargun22/blog-backend-api"
        )
)
public class BlogApiApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(BlogApiApplication.class, args);
    }

}
