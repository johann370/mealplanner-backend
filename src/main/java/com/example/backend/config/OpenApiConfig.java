package com.example.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI defineOpenApi () {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Johann Mendoza");
        myContact.setEmail("johannmendoza370@gmail.com");

        Info information = new Info()
                .title("Meal Planner App")
                .version("1.0")
                .description("A simple meal planning app where users can create planners and add/remove recipes from them.")
                .contact(myContact);

        return new OpenAPI().info(information).servers(List.of(devServer));
    }
}
