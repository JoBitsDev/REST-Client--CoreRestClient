/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 *
 * @author Home
 */
public class TipoGastoModelAssembler implements RepresentationModelAssembler<String, EntityModel<String>> {

    public TipoGastoModelAssembler() {
    }

    @Override
    public EntityModel<String> toModel(String entity) {
        return EntityModel.of(entity, linkTo(entity).withSelfRel());
    }

}
