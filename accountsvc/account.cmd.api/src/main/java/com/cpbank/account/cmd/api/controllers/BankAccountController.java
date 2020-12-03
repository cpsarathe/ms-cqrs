package com.cpbank.account.cmd.api.controllers;


import com.cpbank.account.cmd.api.commands.OpenBankAccountCommand;
import com.cpbank.account.cmd.api.dtos.OpenAccountResponseDto;
import lombok.extern.apachecommons.CommonsLog;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/bank-account")
@CommonsLog
public class BankAccountController {

    private final CommandGateway commandGateway;

    public BankAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<OpenAccountResponseDto> openBankAccount(@RequestBody OpenBankAccountCommand openBankAccountCommand) {
        try {
            openBankAccountCommand.setId(UUID.randomUUID().toString());
            commandGateway.sendAndWait(openBankAccountCommand);
            return new ResponseEntity<>(new OpenAccountResponseDto(openBankAccountCommand.getId(), "account opened successfully"), HttpStatus.CREATED);
        } catch (Exception ex) {
            String custMessage = "Error while opening user account for id : " + openBankAccountCommand.getId();
            log.error(ex);
            return new ResponseEntity<>(new OpenAccountResponseDto(openBankAccountCommand.getId(), custMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
