/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.insumo.InsumoDetailEndPoint;
import com.jobits.pos.core.domain.models.Insumo;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class InsumoModelAssembler extends CrudModelAssembler<Insumo> {

    public InsumoModelAssembler() {
        super(InsumoDetailEndPoint.class);
    }

    @Override
    public Object getId(Insumo entity) {
        return entity.getCodInsumo();
    }

}
