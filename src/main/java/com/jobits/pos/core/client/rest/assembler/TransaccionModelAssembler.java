/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.almacen.TransaccionListEndPoint;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class TransaccionModelAssembler extends CrudModelAssembler<Transaccion> {

    public TransaccionModelAssembler() {
        super(TransaccionListEndPoint.class);
    }

    @Override
    public Object getId(Transaccion entity) {
        return entity.getNoTransaccion();
    }

}
