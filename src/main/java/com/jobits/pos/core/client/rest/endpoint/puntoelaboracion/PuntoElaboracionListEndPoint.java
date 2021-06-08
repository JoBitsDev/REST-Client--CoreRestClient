/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.puntoelaboracion;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.core.client.rest.assembler.PuntoElaboracionModelAssembler;
import com.jobits.pos.core.client.rest.endpoint.almacen.AlmacenManageEndPoint;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/punto_elaboracion_list")
public class PuntoElaboracionListEndPoint extends CrudRestServiceTemplate<Cocina> {

    PuntoElaboracionModelAssembler puntoElaboracionAssembler = new PuntoElaboracionModelAssembler();

    @Override
    public PuntoElaboracionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(PuntoElaboracionListService.class);
    }

    @Override
    public CrudModelAssembler<Cocina> getAssembler() {
        return puntoElaboracionAssembler;
    }

}
