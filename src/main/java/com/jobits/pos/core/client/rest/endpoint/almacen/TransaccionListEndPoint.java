/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.TransaccionService;
import com.jobits.pos.core.client.rest.assembler.TransaccionModelAssembler;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.usecase.TransaccionListService;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/transaccion_list")
public class TransaccionListEndPoint extends CrudRestServiceTemplate<Transaccion> {

    public static final String FIND_ALL_BY_ALMACEN_PATH = "/find_all_by_almacen";
    public static final RequestMethod FIND_ALL_BY_ALMACEN_METHOD = RequestMethod.GET;

    TransaccionModelAssembler transaccionAssembler = new TransaccionModelAssembler();

    @Override
    public TransaccionService getUc() {
        return PosCoreModule.getInstance().getImplementation(TransaccionService.class);
    }

    @Override
    public CrudModelAssembler<Transaccion> getAssembler() {
        return transaccionAssembler;
    }

    @GetMapping(FIND_ALL_BY_ALMACEN_PATH)
    public CollectionModel<EntityModel<Transaccion>> findAllByAlmacen(@RequestBody Almacen almacen) {
        CollectionModel<EntityModel<Transaccion>> entityModel
                = transaccionAssembler.toCollectionModel(getUc().findAllByAlmacen(almacen.getCodAlmacen()));
        entityModel.add(linkTo(methodOn(TransaccionListEndPoint.class).findAllByAlmacen(almacen)).withRel("find_all_by_almacen"));
        return entityModel;
    }

}
