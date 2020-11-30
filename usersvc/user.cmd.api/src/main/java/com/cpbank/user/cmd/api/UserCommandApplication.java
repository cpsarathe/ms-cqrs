package com.cpbank.user.cmd.api;

import com.cpbank.user.core.configuration.CoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CoreConfiguration.class})
public class UserCommandApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }

}
