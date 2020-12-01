package com.cpbank.user.cmd.api.controllers;

import com.cpbank.user.cmd.api.commands.RegisterUserCommand;
import com.cpbank.user.cmd.api.commands.UpdateUserCommand;
import com.cpbank.user.cmd.api.dtos.RegisterUserResponseDto;
import lombok.extern.apachecommons.CommonsLog;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/user")
@CommonsLog
public class UserController {

    private final CommandGateway commandGateway;

    public UserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserCommand registerUserCommand) {
        try {
            registerUserCommand.setId(UUID.randomUUID().toString());
            commandGateway.sendAndWait(registerUserCommand);
            return new ResponseEntity<>(new RegisterUserResponseDto(registerUserCommand.getId(), "user registered successfully"), HttpStatus.CREATED);
        } catch (Exception ex) {
            String custMessage = "Error while registering user for id : " + registerUserCommand.getId();
            log.error(ex);
            return new ResponseEntity<>(new RegisterUserResponseDto(registerUserCommand.getId(), custMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    @PutMapping("/{id}")
    public ResponseEntity<RegisterUserResponseDto> updateUser(@PathVariable(value = "id") String id , @RequestBody UpdateUserCommand updateUserCommand) {
        try {
            updateUserCommand.setId(id);
            commandGateway.sendAndWait(updateUserCommand);
            return new ResponseEntity<>(new RegisterUserResponseDto(updateUserCommand.getId(), "user updated successfully"), HttpStatus.CREATED);
        } catch (Exception ex) {
            String custMessage = "Error while updating user for id : " + updateUserCommand.getId();
            log.error(ex);
            return new ResponseEntity<>(new RegisterUserResponseDto(updateUserCommand.getId(), custMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
