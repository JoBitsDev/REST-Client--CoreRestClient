/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.trabajador.NominasEndPoint;
import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Home
 */
public class AsistenciaPersonalEstadisticasModelAssembler implements RepresentationModelAssembler<AsistenciaPersonalEstadisticas, EntityModel<AsistenciaPersonalEstadisticas>> {

    protected Class<? extends CrudRestServiceTemplate<AsistenciaPersonal>> serviceClass = NominasEndPoint.class;

    public AsistenciaPersonalEstadisticasModelAssembler() {
    }

    @Override
    public EntityModel<AsistenciaPersonalEstadisticas> toModel(AsistenciaPersonalEstadisticas entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(serviceClass).findBy(entity)).withSelfRel());
    }
}
