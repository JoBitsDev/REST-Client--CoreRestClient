/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.almacen.IPVEndPoint;
import com.jobits.pos.core.domain.models.Ipv;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class IpvVentaRegistroModelAssembler implements RepresentationModelAssembler<IpvVentaRegistro, EntityModel<IpvVentaRegistro>> {

    protected Class<? extends CrudRestServiceTemplate<Ipv>> serviceClass = IPVEndPoint.class;

    public IpvVentaRegistroModelAssembler() {
    }
    
    @Override
    public EntityModel<IpvVentaRegistro> toModel(IpvVentaRegistro entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }
}
