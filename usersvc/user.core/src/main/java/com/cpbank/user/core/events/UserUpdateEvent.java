package com.cpbank.user.core.events;

import com.cpbank.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UserUpdateEvent {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
