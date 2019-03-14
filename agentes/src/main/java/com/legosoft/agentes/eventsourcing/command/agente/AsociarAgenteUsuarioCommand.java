package com.legosoft.agentes.eventsourcing.command.agente;

import com.legosoft.agentes.model.Compania;
import lombok.*;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AsociarAgenteUsuarioCommand {

    @TargetAggregateIdentifier
    private Long idEvent;

    private Compania compania;

}
