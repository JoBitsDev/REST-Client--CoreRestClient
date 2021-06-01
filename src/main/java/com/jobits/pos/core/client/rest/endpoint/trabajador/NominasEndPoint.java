/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.controller.trabajadores.NominasService;
import com.jobits.pos.core.client.rest.assembler.AsistenciaPersonalModelAssembler;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
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
@RequestMapping(path = "/nominas")
public class NominasEndPoint extends CrudRestServiceTemplate<AsistenciaPersonal> {

    AsistenciaPersonalModelAssembler asistenciaPersonalAssembler = new AsistenciaPersonalModelAssembler();

    @Override
    public CRUDUseCase<AsistenciaPersonal> getUc() {
        return PosCoreModule.getInstance().getImplementation(NominasService.class);
    }

    @Override
    public CrudModelAssembler<AsistenciaPersonal> getAssembler() {
        return asistenciaPersonalAssembler;
    }

}
