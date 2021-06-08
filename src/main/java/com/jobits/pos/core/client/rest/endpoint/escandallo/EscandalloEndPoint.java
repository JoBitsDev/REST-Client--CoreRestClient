/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.escandallo;

import com.jobits.pos.controller.escandallo.EscandalloService;
import com.jobits.pos.core.client.rest.assembler.EscandalloModelAssembler;
import com.jobits.pos.core.domain.models.escandallos.RegistroEscandallo;
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
@RequestMapping(path = "/escandallo")
public class EscandalloEndPoint extends CrudRestServiceTemplate<RegistroEscandallo> {

    EscandalloModelAssembler escandalloAssembler = new EscandalloModelAssembler();

    @Override
    public EscandalloService getUc() {
        return PosCoreModule.getInstance().getImplementation(EscandalloService.class);
    }

    @Override
    public CrudModelAssembler<RegistroEscandallo> getAssembler() {
        return escandalloAssembler;
    }

}
