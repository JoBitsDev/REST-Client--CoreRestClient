/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler.models;

import com.jobits.pos.core.client.rest.endpoint.configuracion.ConfiguracionEndPoint;
import com.jobits.pos.core.domain.models.Configuracion;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class ConfigurationObjectModelAssembler implements RepresentationModelAssembler<ConfigurationObjectWrapper, EntityModel<ConfigurationObjectWrapper>> {

    protected Class<? extends CrudRestServiceTemplate<Configuracion>> serviceClass = ConfiguracionEndPoint.class;

    public ConfigurationObjectModelAssembler() {
    }

    @Override
    public EntityModel<ConfigurationObjectWrapper> toModel(ConfigurationObjectWrapper entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }

}
