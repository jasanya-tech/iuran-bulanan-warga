package com.iuran_bulanan_warga.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Iuran Bulanan Warga API")
                        .description("Aplikasi bootcamp Tabs")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Di buat oleh Laila Alfi Syah dan Syahrul Saefulah")
                        .url("https://github.com/jasanya-tech/iuran-bulanan-warga"));
    }
}
