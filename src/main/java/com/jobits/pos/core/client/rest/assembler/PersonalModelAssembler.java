/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.trabajador.PersonalDetailEndPoint;
import com.jobits.pos.core.domain.models.Personal;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class PersonalModelAssembler extends CrudModelAssembler<Personal> {

    public PersonalModelAssembler() {
        super(PersonalDetailEndPoint.class);
    }

    @Override
    public Object getId(Personal entity) {
        return entity.getUsuario();
    }

}
