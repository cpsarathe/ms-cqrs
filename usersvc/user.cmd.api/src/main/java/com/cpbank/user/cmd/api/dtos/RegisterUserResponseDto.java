package com.cpbank.user.cmd.api.dtos;

import lombok.Data;

@Data
public class RegisterUserResponseDto extends BaseResponseDto {
    private String id;

    public RegisterUserResponseDto(String id , String message) {
        super(message);
        this.id = id;
    }
}
