package com.cpbank.user.core.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UserRemoveEvent {
    @TargetAggregateIdentifier
    private String id;
}
