package com.cpbank.user.query.api.dtos;

import com.cpbank.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

@Data
@AllArgsConstructor
public class UserLookupResponseDto {
    private List<User> users;
}
