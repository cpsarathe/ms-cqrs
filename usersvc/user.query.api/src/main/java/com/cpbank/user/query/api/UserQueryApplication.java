package com.cpbank.user.query.api;

import com.cpbank.user.core.configuration.CoreConfiguration;
import com.cpbank.user.core.configuration.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CoreConfiguration.class, MongoConfig.class})
public class UserQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class, args);
    }

}
