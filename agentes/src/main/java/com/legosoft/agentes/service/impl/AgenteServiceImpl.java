package com.legosoft.agentes.service.impl;

import com.google.gson.Gson;
import com.legosoft.agentes.eventsourcing.command.agente.AsociarAgenteUsuarioCommand;
import com.legosoft.agentes.eventsourcing.command.agente.CreateAgenteCommand;
import com.legosoft.agentes.model.Agente;
import com.legosoft.agentes.repository.AgenteRepository;
import com.legosoft.agentes.service.AgenteService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service("agenteService")
public class AgenteServiceImpl implements AgenteService {

    private  CommandGateway commandGateway ;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AgenteRepository agenteRepository;

    public AgenteServiceImpl(CommandGateway commandGateway, AgenteRepository agenteRepository){
        this.commandGateway = commandGateway;
        this.agenteRepository = agenteRepository;
    }

    public CompletableFuture<String> createCommandAgente(Agente agente){
        Agente validaAgente = agenteRepository.findByNombreAgente(agente.getNombreAgente());
        CompletableFuture<String> future = null;
        if (validaAgente == null){
            agente.setIdAgente(null);
            agente = agenteRepository.save(agente);
            Long idEvent = agente.getIdAgente();
            CreateAgenteCommand agenteCommand = new CreateAgenteCommand(agente.getIdAgente(), agente);
//        rabbitTemplate.convertAndSend("agente_usuario","agente_usuario", new Gson().toJson(agente));
//        rabbitTemplate.convertAndSend("usuarioCQRS","usuarioCQRS", new Gson().toJson(agente));
            future = commandGateway.send(agenteCommand);
        }else{
            log.info("Error el agente ya existe");
        }
        return future;
    }

    public CompletableFuture<String> updateCommandRelacionUsarioAgente(Agente agente){
        Agente ag = agenteRepository.findByNombreAgente(agente.getNombreAgente());
//        Agente ag = agenteRepository.findByIdAgente(agente.getIdAgente());
        System.out.println("nombre del agente despues de la consulta:: " + ag.getNombreAgente());
        Agente nuvoAgente = agenteRepository.save(ag);
        AsociarAgenteUsuarioCommand asociarAgenteUsuarioCommand = new AsociarAgenteUsuarioCommand(1L, "uriel", new Date(), Boolean.TRUE, "USUARIO_RELACIOANDO_AGENTE");
        return commandGateway.sendAndWait(asociarAgenteUsuarioCommand);
    }

    public void EnviaColaGrupo(String nombreGrupo, String nombreUsuario, String nombreAgente){
        Map<String, Object> json = new HashMap<>();
        List<String> agenteList = new ArrayList<>();
        agenteList.add(nombreAgente);
        json.put("nombreUsuario", nombreUsuario);
        json.put("nombreGrupo", nombreGrupo);
        json.put("agenteList", agenteList);
        rabbitTemplate.convertAndSend("grupo_empresarial_events_test","grupo_empresarial_events_test", new Gson().toJson(json));
    }

    public Agente findAgenteById(Long idAgente){
        return agenteRepository.findByIdAgente(idAgente);
    }

    public List<Agente> findAllAgentes(){
        return agenteRepository.findAll();
    }

    public Agente saveOrUpdate(Agente agente){
        return agenteRepository.save(agente);
    }

    public void deleteAgente(Agente agente){
        agenteRepository.delete(agente);
    }

    public Agente findAgenteByNombre(String nombreAgente){
        return agenteRepository.findByNombreAgente(nombreAgente);
    }

}
