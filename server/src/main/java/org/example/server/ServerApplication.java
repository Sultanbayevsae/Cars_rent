package org.example.server;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
        info = @Info(
                title = "MORENT Swagger UI",
                description = "All necessary API endpoints are available",
                version = "v1",
                contact = @Contact(
                        name = "Steve Jobs",
                        email = "jobs@apple.com",
                        url = "https://morent.com/contact"
                ),
                license = @License(
                        name = "MIT 3.0",
                        url = "https://morent.com/license"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "How-To-Use Documentation",
                url = "https://morent.com/documentation"
        ),
        security = {
                @SecurityRequirement(
                        name = "Bearer "
                )
        }
)

@SecurityScheme(
        name = "Bearer ",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
@EntityScan("org.example.server.entity")
@EnableJpaRepositories("org.example.server.repository")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }

}
