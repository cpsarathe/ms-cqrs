package com.cpbank.account.core.events;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class AccountClosedEvent {
    @TargetAggregateIdentifier
    private String id;
}
