/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.trabajador.PuestoTrabajoDetailEndPoint;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class PuestoTrabajoModelAssembler extends CrudModelAssembler<PuestoTrabajo> {

    public PuestoTrabajoModelAssembler() {
        super(PuestoTrabajoDetailEndPoint.class);
    }

    @Override
    public Object getId(PuestoTrabajo entity) {
        return entity.getIdPuesto();
    }
    
}
