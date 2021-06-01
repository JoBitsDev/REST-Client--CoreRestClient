/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.mesa.MesaEndPoint;
import com.jobits.pos.core.domain.models.Mesa;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class MesaModelAssembler extends CrudModelAssembler<Mesa> {

    public MesaModelAssembler() {
        super(MesaEndPoint.class);
    }

    @Override
    public Object getId(Mesa entity) {
        return entity.getCodMesa();
    }

    
}
