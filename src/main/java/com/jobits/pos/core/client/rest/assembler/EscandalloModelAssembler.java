/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.escandallo.EscandalloEndPoint;
import com.jobits.pos.core.domain.models.escandallos.RegistroEscandallo;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class EscandalloModelAssembler extends CrudModelAssembler<RegistroEscandallo> {

    public EscandalloModelAssembler() {
        super(EscandalloEndPoint.class);
    }

    @Override
    public Object getId(RegistroEscandallo entity) {
        return entity.getId();
    }

    
}
