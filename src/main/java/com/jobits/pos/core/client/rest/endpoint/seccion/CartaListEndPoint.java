/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.seccion;

import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/carta")
public class CartaListEndPoint extends CrudRestEndPointTemplate<Carta, CartaListService>
        implements CartaListService {


    @Override
    public CartaListService getUc() {
        return PosCoreModule.getInstance().getImplementation(CartaListService.class);
    }

}
