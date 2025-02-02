package dev.kinodesu;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "${spring.application.name}",
        version = "${spring.application.version}",
        description = "${spring.application.description}")
)
public class CalculadoraGenshinApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculadoraGenshinApplication.class, args);
    }

}