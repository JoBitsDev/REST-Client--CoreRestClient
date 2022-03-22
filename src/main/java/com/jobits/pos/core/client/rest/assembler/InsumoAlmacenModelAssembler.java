/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.almacen.AlmacenManageEndPoint;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class InsumoAlmacenModelAssembler implements RepresentationModelAssembler<InsumoAlmacen, EntityModel<InsumoAlmacen>> {

    protected Class<? extends CrudRestServiceTemplate<Almacen>> serviceClass = AlmacenManageEndPoint.class;

    public InsumoAlmacenModelAssembler() {
    }

    @Override
    public EntityModel<InsumoAlmacen> toModel(InsumoAlmacen entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }
}
