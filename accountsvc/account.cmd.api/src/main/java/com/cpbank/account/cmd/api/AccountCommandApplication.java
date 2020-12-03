package com.cpbank.account.cmd.api;

import com.cpbank.account.core.configuration.CoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({CoreConfiguration.class})
public class AccountCommandApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountCommandApplication.class, args);
    }

}
