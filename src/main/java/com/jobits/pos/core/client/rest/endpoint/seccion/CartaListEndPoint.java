/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.seccion;

import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.core.client.rest.assembler.CartaModelAssembler;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/carta_list")
public class CartaListEndPoint extends CrudRestServiceTemplate<Carta> {

    CartaModelAssembler cartaAssembler = new CartaModelAssembler();

    @Override
    public CRUDUseCase<Carta> getUc() {
        return PosCoreModule.getInstance().getImplementation(CartaListService.class);
    }

    @Override
    public CrudModelAssembler<Carta> getAssembler() {
        return cartaAssembler;
    }

}
