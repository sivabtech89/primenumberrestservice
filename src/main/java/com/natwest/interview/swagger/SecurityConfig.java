package com.natwest.interview.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig implements WebMvcConfigurer {

  @Bean
  public OpenAPI customOpenAPI() {
    final var securitySchemaName = "HTTP_AUTH_TOKEN";

    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemaName))
        .components(new Components().addSecuritySchemes(securitySchemaName,
            new SecurityScheme().name(securitySchemaName).type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)));
  }
}
