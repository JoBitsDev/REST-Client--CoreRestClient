/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.AlmacenEndPoint;
import com.jobits.pos.core.domain.models.Almacen;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class AlmacenModelAssembler extends CrudModelAssembler<Almacen> {

    public AlmacenModelAssembler() {
        super(AlmacenEndPoint.class);
    }

    @Override
    public Object getId(Almacen entity) {
        return entity.getCodAlmacen();
    }

}
