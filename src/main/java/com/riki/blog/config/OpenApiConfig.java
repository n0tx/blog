package com.riki.blog.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
    @Value("${app.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI blogOpenAPI() {

        Server devServer = new Server();
        devServer.setUrl((devUrl));
        devServer.setDescription("Server URL Development Environment");

        Contact contact = new Contact();
        contact.setName("n0tx");
        contact.setUrl("https://github/n0tx/blog");

        License license = new License()
                .name("GNU General Public License").url("https://www.gnu.org/licenses/gpl-3.0.html");

        Info info = new Info()
                .title("Blog REST APIs")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage blog")
                .license(license);

        return new OpenAPI().info(info).servers(List.of(devServer));

    }
}
