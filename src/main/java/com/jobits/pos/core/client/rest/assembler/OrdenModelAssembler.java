/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.venta.OrdenEndPoint;
import com.jobits.pos.core.domain.models.Orden;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class OrdenModelAssembler extends CrudModelAssembler<Orden> {

    public OrdenModelAssembler() {
        super(OrdenEndPoint.class);
    }

    @Override
    public Object getId(Orden entity) {
        return entity.getCodOrden();
    }

}
