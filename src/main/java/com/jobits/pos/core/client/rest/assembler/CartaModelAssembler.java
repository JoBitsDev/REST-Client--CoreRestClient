/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.seccion.CartaListEndPoint;
import com.jobits.pos.core.domain.models.Carta;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class CartaModelAssembler extends CrudModelAssembler<Carta> {

    public CartaModelAssembler() {
        super(CartaListEndPoint.class);
    }

    @Override
    public Object getId(Carta entity) {
        return entity.getCodCarta();
    }
    
}
