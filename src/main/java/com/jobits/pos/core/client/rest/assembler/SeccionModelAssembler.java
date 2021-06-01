/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.seccion.SeccionListEndPoint;
import com.jobits.pos.core.domain.models.Seccion;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class SeccionModelAssembler extends CrudModelAssembler<Seccion> {

    public SeccionModelAssembler() {
        super(SeccionListEndPoint.class);
    }

    @Override
    public Object getId(Seccion entity) {
        return entity.getNombreSeccion();
    }
    
}
