/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.puntoelaboracion;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.core.client.rest.assembler.PuntoElaboracionModelAssembler;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/punto_elaboracion_list")
public class PuntoElaboracionListEndPoint extends CrudRestServiceTemplate<Cocina> {

    public static final String DESTROY_IN_CASCADE_PATH = "/destroy_in_cascade";
    public static final RequestMethod DESTROY_IN_CASCADE_METHOD = RequestMethod.DELETE;

    public static final String EDIT_COCINA_PATH = "/edit_cocina";
    public static final RequestMethod EDIT_COCINA_METHOD = RequestMethod.PUT;

    PuntoElaboracionModelAssembler puntoElaboracionAssembler = new PuntoElaboracionModelAssembler();

    @Override
    public PuntoElaboracionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(PuntoElaboracionListService.class);
    }

    @Override
    public CrudModelAssembler<Cocina> getAssembler() {
        return puntoElaboracionAssembler;
    }

    @DeleteMapping(DESTROY_IN_CASCADE_PATH)
    public EntityModel<Cocina> destroyInCascade(@RequestBody Cocina cocina) {
        EntityModel<Cocina> entityModel = puntoElaboracionAssembler.toModel(getUc().destroyInCascade(cocina));
        entityModel.add(linkTo(methodOn(PuntoElaboracionListEndPoint.class).destroyInCascade(cocina)).withRel("destroy_in_cascade"));
        return entityModel;
    }

    @PutMapping(EDIT_COCINA_PATH)
    public EntityModel<Cocina> edit(@RequestBody Cocina t, @RequestParam String name) {
        EntityModel<Cocina> entityModel = puntoElaboracionAssembler.toModel(getUc().edit(t, name));
        entityModel.add(linkTo(methodOn(PuntoElaboracionListEndPoint.class).edit(t, name)).withRel("edit_cocina"));
        return entityModel;
    }
}
