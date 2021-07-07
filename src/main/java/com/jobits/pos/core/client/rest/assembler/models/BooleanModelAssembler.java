/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler.models;

import com.jobits.pos.core.client.rest.endpoint.venta.VentaDetailEndPoint;
import com.jobits.pos.core.domain.models.Venta;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class BooleanModelAssembler implements RepresentationModelAssembler<Boolean, EntityModel<Boolean>> {

    protected Class<? extends CrudRestServiceTemplate<Venta>> serviceClass = VentaDetailEndPoint.class;

    public BooleanModelAssembler() {
    }

    @Override
    public EntityModel<Boolean> toModel(Boolean entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }

}
