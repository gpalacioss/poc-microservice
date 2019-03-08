package com.legosoft.agentes.sagas;

import com.legosoft.agentes.eventsourcing.command.agente.AsociarAgenteUsuarioCommand;
import com.legosoft.agentes.eventsourcing.command.agente.CreateAgenteCommand;
import com.legosoft.agentes.eventsourcing.event.agente.AgenteCreatedEvent;
import com.legosoft.agentes.eventsourcing.event.agente.AssociatedAgenteUsuarioEvent;
import com.legosoft.agentes.model.Agente;
import com.legosoft.agentes.repository.AgenteRepository;
import com.legosoft.agentes.service.AgenteService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@Saga
public class CreateAgenteSaga {

    /**
     * Se anota como tiop transient por que no deben de persistir los recursos
     */
    @Autowired
    private transient CommandGateway commandGateway;

//    private final Repository<Agente> agenteRepository;

    @Autowired
    private AgenteService agenteService;

    private boolean creado = false;

//    public CreateAgenteSaga(Repository<Agente> agenteRepository){
//        this.agenteRepository = agenteRepository;
//    }

//    @StartSaga
//    @SagaEventHandler(associationProperty = "idAgente")
//    public void handle(AgenteCreatedEvent event){
////        SagaLifecycle.associateWith("");
//        Agente agente = agenteService.findAgenteByNombre(event.getNombreAgente());
//        log.info("Inicio la saga");
//        if (agente == null){
//            CreateAgenteCommand command = new CreateAgenteCommand(event.getIdAgente(), event.getNombreAgente(), event.getFechaCreacion(), event.isActivo(), event.getEstatus());
//            commandGateway.send(command);
//        }
//    }


//    @EndSaga
//    @SagaEventHandler(associationProperty = "idAgenteEvent")
//    public void on(AssociatedAgenteUsuarioEvent event){
//        log.info("Agente " + event.getNombreAgente() + " creado correctamente, siguiente paso relacionar a un usuario");
////        String id = UUID.randomUUID().toString();
////        log.info("Entro al end saga");
////        Agente ag = agenteService.findAgenteByNombre(event.getNombreAgente());
////        AsociarAgenteUsuarioCommand asociarAgenteUsuarioCommand = new AsociarAgenteUsuarioCommand(ag.getIdAgenteEvent(), event.getNombreAgente(), event.isActivo(), "USUARIO_RELACIONADO_CONAGETE");
////        commandGateway.send(asociarAgenteUsuarioCommand);
//    }
}