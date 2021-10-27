/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.controller.trabajadores.NominasService;
import com.jobits.pos.core.client.rest.assembler.AsistenciaPersonalEstadisticasModelAssembler;
import com.jobits.pos.core.client.rest.assembler.AsistenciaPersonalModelAssembler;
import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/nominas")
public class NominasEndPoint extends CrudRestServiceTemplate<AsistenciaPersonal> {

    public static final String GET_PERSONAL_ACTIVO_PATH = "/get_personal_activo";
    public static final RequestMethod GET_PERSONAL_ACTIVO_METHOD = RequestMethod.GET;

    public static final String IMPRIMIR_ESTADISTICAS_PATH = "/imprimir_estadisticas";
    public static final RequestMethod IMPRIMIR_ESTADISTICAS_METHOD = RequestMethod.POST;

    public static final String PAGAR_PATH = "/pagar";
    public static final RequestMethod PAGAR_METHOD = RequestMethod.PUT;

    AsistenciaPersonalModelAssembler asistenciaPersonalAssembler
            = new AsistenciaPersonalModelAssembler();
    AsistenciaPersonalEstadisticasModelAssembler asistenciaPersonalEstadisticasAssembler
            = new AsistenciaPersonalEstadisticasModelAssembler();

    @Override
    public NominasService getUc() {
        return PosCoreModule.getInstance().getImplementation(NominasService.class);
    }

    @Override
    public CrudModelAssembler<AsistenciaPersonal> getAssembler() {
        return asistenciaPersonalAssembler;
    }

    @GetMapping(GET_PERSONAL_ACTIVO_PATH)
    public CollectionModel<EntityModel<AsistenciaPersonalEstadisticas>> getPersonalActivo(@RequestBody Date fecha_desde, @RequestBody Date fecha_hasta) {
        CollectionModel<EntityModel<AsistenciaPersonalEstadisticas>> entityModel
                = asistenciaPersonalEstadisticasAssembler.toCollectionModel(getUc().getPersonalActivo(fecha_desde, fecha_hasta));
        entityModel.add(linkTo(methodOn(NominasEndPoint.class).getPersonalActivo(fecha_desde, fecha_hasta)).withRel("insumo_almacen_list"));
        return entityModel;
    }

    @PostMapping(IMPRIMIR_ESTADISTICAS_PATH)
    public boolean imprimirEstadisticas(@RequestBody List<AsistenciaPersonalEstadisticas> lista_personal) {
        getUc().imprimirEstadisticas(lista_personal);
        return true;
    }

    @PutMapping(PAGAR_PATH)
    public boolean pagar(@RequestBody List<AsistenciaPersonalEstadisticas> list, @RequestParam boolean flag) {
        getUc().pagar(list, flag);
        return true;
    }
}
