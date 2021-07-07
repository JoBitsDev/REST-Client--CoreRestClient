/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.gasto.GastoOperacionEndPoint;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.temporal.DefaultGasto;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class DefaultCostoModelAssembler implements RepresentationModelAssembler<DefaultGasto, EntityModel<DefaultGasto>> {

    protected Class<? extends CrudRestServiceTemplate<GastoVenta>> serviceClass = GastoOperacionEndPoint.class;

    public DefaultCostoModelAssembler() {
    }

    @Override
    public EntityModel<DefaultGasto> toModel(DefaultGasto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }

}
