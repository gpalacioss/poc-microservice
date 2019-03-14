package com.legosoft.agentes.eventsourcing.event.agente;

import com.legosoft.agentes.model.Compania;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssociatedAgenteUsuarioEvent {

    private Long idEvent;

    private Compania compania;

}
