package com.cpbank.user.core.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collation = "users")
public class Account {
    private String userName;
    private String password;
    private List<Role> roles;
}
