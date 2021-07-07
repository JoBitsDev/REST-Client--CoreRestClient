/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.seccion;

import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.core.client.rest.assembler.SeccionModelAssembler;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.module.PosCoreModule;
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
@RequestMapping(path = "/seccion_list")
public class SeccionListEndPoint extends CrudRestServiceTemplate<Seccion> {

    public static final String FIND_SECCION_BY_MESA_PATH = "/find_secciones_by_mesa";
    public static final RequestMethod FIND_SECCIONES_BY_MESA_METHOD = RequestMethod.GET;
    SeccionModelAssembler seccionAssembler = new SeccionModelAssembler();

    @Override
    public SeccionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(SeccionListService.class);
    }

    @Override
    public CrudModelAssembler<Seccion> getAssembler() {
        return seccionAssembler;
    }

    @GetMapping(FIND_SECCION_BY_MESA_PATH)
    public CollectionModel<EntityModel<Seccion>> findSeccionesByMesa(@RequestBody Mesa mesa) {
        CollectionModel<EntityModel<Seccion>> entityModel
                = seccionAssembler.toCollectionModel(getUc().findSeccionesByMesa(mesa));
        entityModel.add(linkTo(methodOn(SeccionListEndPoint.class).findSeccionesByMesa(mesa)).withRel("find_secciones_by_mesa"));
        return entityModel;
    }

}
