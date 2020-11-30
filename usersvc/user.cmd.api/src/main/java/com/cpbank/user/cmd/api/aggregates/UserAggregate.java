package com.cpbank.user.cmd.api.aggregates;

import com.cpbank.user.cmd.api.commands.RegisterUserCommand;
import com.cpbank.user.cmd.api.commands.RemoveUserCommand;
import com.cpbank.user.cmd.api.commands.UpdateUserCommand;
import com.cpbank.user.cmd.api.security.PasswordEncoder;
import com.cpbank.user.cmd.api.security.PasswordEncoderImpl;
import com.cpbank.user.core.events.UserRegisterEvent;
import com.cpbank.user.core.events.UserRemoveEvent;
import com.cpbank.user.core.events.UserUpdateEvent;
import com.cpbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;
    private PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @Autowired
    @CommandHandler
    public UserAggregate(RegisterUserCommand registerUserCommand) {
        this();
        User user = registerUserCommand.getUser();
        user.setId(registerUserCommand.getId());
        String password = user.getAccount().getPassword();
        String hashedPassowrd = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassowrd);

        UserRegisterEvent event = UserRegisterEvent.builder()
                .id(registerUserCommand.getId())
                .user(user)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand) {
        User user = updateUserCommand.getUser();
        user.setId(updateUserCommand.getId());
        String password = user.getAccount().getPassword();
        String hashedPassowrd = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassowrd);

        UserUpdateEvent event = UserUpdateEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand removeUserCommand) {
        UserRemoveEvent userRemoveEvent = new UserRemoveEvent();
        userRemoveEvent.setId(removeUserCommand.getId());
        AggregateLifecycle.apply(userRemoveEvent);
    }

    @EventSourcingHandler
    public void on(UserRegisterEvent userRegisterEvent) {
        this.id = userRegisterEvent.getId();
        this.user = userRegisterEvent.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent userUpdateEvent) {
        this.user = userUpdateEvent.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemoveEvent userRemoveEvent) {
        AggregateLifecycle.markDeleted();
    }
}
