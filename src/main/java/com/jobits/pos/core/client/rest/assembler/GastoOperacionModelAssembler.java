/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.gasto.GastoOperacionEndPoint;
import com.jobits.pos.core.domain.models.GastoVenta;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class GastoOperacionModelAssembler extends CrudModelAssembler<GastoVenta> {

    public GastoOperacionModelAssembler() {
        super(GastoOperacionEndPoint.class);
    }

    @Override
    public Object getId(GastoVenta entity) {
        return entity.getGastoVentaPK();
    }

    
}
