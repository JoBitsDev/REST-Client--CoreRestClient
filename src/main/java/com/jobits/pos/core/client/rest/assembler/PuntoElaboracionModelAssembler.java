/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.puntoelaboracion.PuntoElaboracionListEndPoint;
import com.jobits.pos.core.domain.models.Cocina;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class PuntoElaboracionModelAssembler extends CrudModelAssembler<Cocina> {

    public PuntoElaboracionModelAssembler() {
        super(PuntoElaboracionListEndPoint.class);
    }

    @Override
    public Object getId(Cocina entity) {
        return entity.getCodCocina();
    }
    
}
