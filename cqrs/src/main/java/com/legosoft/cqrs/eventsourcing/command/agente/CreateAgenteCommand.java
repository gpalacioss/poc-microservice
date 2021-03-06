package com.legosoft.cqrs.eventsourcing.command.agente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgenteCommand {

    @TargetAggregateIdentifier
    private Long idAgente;

    private String nombreAgente;

    private Date fechaCracion;

    private boolean activo;
}
