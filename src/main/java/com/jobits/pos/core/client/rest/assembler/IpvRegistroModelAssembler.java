/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.almacen.IPVEndPoint;
import com.jobits.pos.inventario.core.almacen.domain.Ipv;
import com.jobits.pos.inventario.core.almacen.domain.IpvRegistro;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class IpvRegistroModelAssembler implements RepresentationModelAssembler<IpvRegistro, EntityModel<IpvRegistro>> {

    protected Class<? extends CrudRestServiceTemplate<Ipv>> serviceClass = IPVEndPoint.class;

    public IpvRegistroModelAssembler() {
    }

    @Override
    public EntityModel<IpvRegistro> toModel(IpvRegistro entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }

}
