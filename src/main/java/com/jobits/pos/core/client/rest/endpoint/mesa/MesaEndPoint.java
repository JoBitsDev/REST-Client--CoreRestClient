/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.mesa;

import com.jobits.pos.controller.mesa.MesaService;
import com.jobits.pos.core.client.rest.assembler.MesaModelAssembler;
import com.jobits.pos.core.domain.models.Mesa;
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
@RequestMapping(path = "/mesa")
public class MesaEndPoint extends CrudRestServiceTemplate<Mesa> {

    MesaModelAssembler mesaAssembler = new MesaModelAssembler();

    @Override
    public CRUDUseCase<Mesa> getUc() {
        return PosCoreModule.getInstance().getImplementation(MesaService.class);
    }

    @Override
    public CrudModelAssembler<Mesa> getAssembler() {
        return mesaAssembler;
    }

}
