package dev.kinodesu.infra.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2Database {

    private final String url;
    private final String user;
    private final String password;
}
