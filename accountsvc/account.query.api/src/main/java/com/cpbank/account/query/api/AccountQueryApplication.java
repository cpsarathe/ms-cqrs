package com.cpbank.account.query.api;

import com.cpbank.account.core.configuration.CoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({CoreConfiguration.class})
@EntityScan(basePackages = {"com.cpbank.account.core.models"})
public class AccountQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }

}
