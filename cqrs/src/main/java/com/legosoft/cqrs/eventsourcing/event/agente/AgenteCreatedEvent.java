package com.legosoft.cqrs.eventsourcing.event.agente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenteCreatedEvent {

    private Long idAgente;

    private String nombreAgente;

    private Date fechaCracion;

    private boolean activo;
}
