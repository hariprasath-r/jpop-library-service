package in.hp.java.libraryservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration annotation is not needed if we are using annotation config
 * Class to configure swagger 3.
 * We can use annotations or bean config
 * Both are demonstrated here below
 */
@OpenAPIDefinition(
        info = @Info(
                title = "${swagger-custom.name}",
                version = "${swagger-custom.version}",
                description = "${swagger-custom.description}",
                contact = @Contact(
                        name = "${swagger-custom.contact.name}",
                        url = "${swagger-custom.contact.url}"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://springdoc.org"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Value("${swagger-custom.contact.name}")
    private String contactName;

    @Value("${swagger-custom.contact.url}")
    private String contactUrl;

    // @Bean
    public OpenAPI customApiConfig(
            @Value("${swagger-custom.name}") String title,
            @Value("${swagger-custom.version}") String version,
            @Value("${swagger-custom.description}") String description
    ) {
        var info = new io.swagger.v3.oas.models.info.Info();
        info.title(title);
        info.version(version);
        info.contact(getContact());
        info.description(description);
        info.license(
                new io.swagger.v3.oas.models.info.License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")
        );

        return new OpenAPI().info(info);
    }

    private io.swagger.v3.oas.models.info.Contact getContact() {
        return new io.swagger.v3.oas.models.info.Contact()
                .name(contactName)
                .url(contactUrl);
    }
}
