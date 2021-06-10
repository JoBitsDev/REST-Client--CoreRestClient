/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.insumo;

import com.jobits.pos.controller.insumo.InsumoListService;
import com.jobits.pos.core.client.rest.assembler.InsumoModelAssembler;
import com.jobits.pos.core.domain.models.Insumo;
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
@RequestMapping(path = "/insumo_list")
public class InsumoListEndPoint extends CrudRestServiceTemplate<Insumo> {

    InsumoModelAssembler insumoAssembler = new InsumoModelAssembler();

    @Override
    public InsumoListService getUc() {
        return PosCoreModule.getInstance().getImplementation(InsumoListService.class);
    }

    @Override
    public CrudModelAssembler<Insumo> getAssembler() {
        return insumoAssembler;
    }

}
