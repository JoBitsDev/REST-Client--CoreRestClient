/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.venta.VentaDetailEndPoint;
import com.jobits.pos.core.domain.models.Venta;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class VentaModelAssembler extends CrudModelAssembler<Venta> {

    public VentaModelAssembler() {
        super(VentaDetailEndPoint.class);
    }

    @Override
    public Object getId(Venta entity) {
        return entity.getId();
    }

}
