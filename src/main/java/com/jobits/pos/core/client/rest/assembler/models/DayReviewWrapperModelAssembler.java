/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler.models;

import com.jobits.pos.core.client.rest.endpoint.configuracion.ConfiguracionEndPoint;
import com.jobits.pos.core.domain.models.Configuracion;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class DayReviewWrapperModelAssembler implements RepresentationModelAssembler<DayReviewWrapper<Venta>, EntityModel<DayReviewWrapper<Venta>>> {

    protected Class<? extends CrudRestServiceTemplate<Configuracion>> serviceClass = ConfiguracionEndPoint.class;

    public DayReviewWrapperModelAssembler() {
    }

    @Override
    public EntityModel<DayReviewWrapper<Venta>> toModel(DayReviewWrapper<Venta> entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }

}
