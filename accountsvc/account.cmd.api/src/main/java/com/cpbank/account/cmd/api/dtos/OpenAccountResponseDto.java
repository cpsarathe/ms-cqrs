package com.cpbank.account.cmd.api.dtos;

import com.cpbank.account.core.dto.BaseResponse;

public class OpenAccountResponseDto extends BaseResponse {
    private String id;

    public OpenAccountResponseDto(String id, String message) {
        super(message);
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
