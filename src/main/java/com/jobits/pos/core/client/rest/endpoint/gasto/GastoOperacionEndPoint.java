/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.gasto;

import com.jobits.pos.controller.gasto.GastoOperacionService;
import com.jobits.pos.core.client.rest.assembler.GastoOperacionModelAssembler;
import com.jobits.pos.core.domain.models.GastoVenta;
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
@RequestMapping(path = "/gasto_operacion")
public class GastoOperacionEndPoint extends CrudRestServiceTemplate<GastoVenta> {

    GastoOperacionModelAssembler gastoOperacionAssembler = new GastoOperacionModelAssembler();

    @Override
    public CRUDUseCase<GastoVenta> getUc() {
        return PosCoreModule.getInstance().getImplementation(GastoOperacionService.class);
    }

    @Override
    public CrudModelAssembler<GastoVenta> getAssembler() {
        return gastoOperacionAssembler;
    }

}
