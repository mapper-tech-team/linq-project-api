package com.example.linqprojectapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "LinQProject API", version = "1",
                contact = @Contact(name = "Mapper Tech",
                        email = "mappertech@gmail.com", url = "https://www.mappertech.com.br"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
                termsOfService = "", description = ""),
        servers = @Server(url = "http://localhost:8080", description = "Production"))
@SecurityScheme(name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
public class OpenAPISecurityConfiguration {
}
