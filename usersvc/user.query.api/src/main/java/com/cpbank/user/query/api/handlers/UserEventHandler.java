package com.cpbank.user.query.api.handlers;

import com.cpbank.user.core.events.UserRegisterEvent;
import com.cpbank.user.core.events.UserRemoveEvent;
import com.cpbank.user.core.events.UserUpdateEvent;

public interface UserEventHandler {

    void on(UserRegisterEvent userRegisterEvent);

    void on(UserUpdateEvent userUpdateEvent);

    void on(UserRemoveEvent userRemoveEvent);
}
