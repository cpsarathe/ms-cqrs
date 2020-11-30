package com.cpbank.user.query.api.handlers;

import com.cpbank.user.core.events.UserRegisterEvent;
import com.cpbank.user.core.events.UserRemoveEvent;
import com.cpbank.user.core.events.UserUpdateEvent;
import com.cpbank.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @EventHandler
    public void on(UserRegisterEvent userRegisterEvent) {
        userRepository.save(userRegisterEvent.getUser());
    }

    @Override
    @EventHandler
    public void on(UserUpdateEvent userUpdateEvent) {
        userRepository.save(userUpdateEvent.getUser());
    }

    @Override
    @EventHandler
    public void on(UserRemoveEvent userRemoveEvent) {
        userRepository.deleteById(userRemoveEvent.getId());
    }
}
