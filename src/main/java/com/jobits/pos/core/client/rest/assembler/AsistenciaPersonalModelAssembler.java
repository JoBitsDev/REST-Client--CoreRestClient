/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.trabajador.AsistenciaPersonalEndPoint;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class AsistenciaPersonalModelAssembler extends CrudModelAssembler<AsistenciaPersonal> {

    public AsistenciaPersonalModelAssembler() {
        super(AsistenciaPersonalEndPoint.class);
    }

    @Override
    public Object getId(AsistenciaPersonal entity) {
        return entity.getAsistenciaPersonalPK();
    }
    
}
