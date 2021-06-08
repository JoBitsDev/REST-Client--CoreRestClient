/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.TransaccionListService;
import com.jobits.pos.core.client.rest.assembler.TransaccionModelAssembler;
import static com.jobits.pos.core.client.rest.endpoint.almacen.IPVEndPoint.GET_IPV_REGISTRO_VENTA_LIST_PATH;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.List;
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
@RequestMapping(path = "/transaccion_list")
public class TransaccionListEndPoint extends CrudRestServiceTemplate<Transaccion> {

    public static final String FIND_ALL_BY_ALMACEN_PATH = "/find_all_by_almacen";
    public static final RequestMethod FIND_ALL_BY_ALMACEN_METHOD = RequestMethod.GET;

    TransaccionModelAssembler transaccionAssembler = new TransaccionModelAssembler();

    @Override
    public TransaccionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(TransaccionListService.class);
    }

    @Override
    public CrudModelAssembler<Transaccion> getAssembler() {
        return transaccionAssembler;
    }

    @GetMapping(FIND_ALL_BY_ALMACEN_PATH)
    public CollectionModel<EntityModel<Transaccion>> findAllByAlmacen(Almacen almacen) {
        CollectionModel<EntityModel<Transaccion>> entityModel
                = transaccionAssembler.toCollectionModel(getUc().findAllByAlmacen(almacen));
        entityModel.add(linkTo(methodOn(TransaccionListEndPoint.class).findAllByAlmacen(almacen)).withRel("find_all_by_almacen"));
        return entityModel;
    }

}
