package com.springboot.blogapi;

import com.springboot.blogapi.model.Role;
import com.springboot.blogapi.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class BlogApiApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(BlogApiApplication.class, args);
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

    }
}
