/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.escandallo.InsumoRegistroEndPoint;
import com.jobits.pos.core.domain.models.escandallos.InsumoRegistro;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class InsumoRegistroModelAssembler extends CrudModelAssembler<InsumoRegistro> {

    public InsumoRegistroModelAssembler() {
        super(InsumoRegistroEndPoint.class);
    }

    @Override
    public Object getId(InsumoRegistro entity) {
        return entity.getId();
    }

}
