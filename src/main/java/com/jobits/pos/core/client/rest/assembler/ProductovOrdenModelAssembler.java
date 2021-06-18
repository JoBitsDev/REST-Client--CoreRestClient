/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.venta.OrdenEndPoint;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.ProductovOrden;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class ProductovOrdenModelAssembler implements RepresentationModelAssembler<ProductovOrden, EntityModel<ProductovOrden>> {

    protected Class<? extends CrudRestServiceTemplate<Orden>> serviceClass = OrdenEndPoint.class;

    public ProductovOrdenModelAssembler() {
    }

    @Override
    public EntityModel<ProductovOrden> toModel(ProductovOrden entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }

}
