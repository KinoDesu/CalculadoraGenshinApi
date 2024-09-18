package dev.kinodesu.infra.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class InfraConfig {

    @Value("${spring.data.h2db.uri}")
    private String url;

    @Value("${spring.data.h2db.user}")
    private String user;

    @Value("${spring.data.h2db.password}")
    private String password;

    @Bean
    public H2Database h2Database() {
        return new H2Database(url, user, password);
    }

    @Bean
    public Connection connection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
