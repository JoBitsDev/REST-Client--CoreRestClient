/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.configuracion;

import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.core.client.rest.assembler.ConfiguracionModelAssembler;
import com.jobits.pos.core.domain.models.Configuracion;
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
@RequestMapping(path = "/configuracion")
public class ConfiguracionEndPoint extends CrudRestServiceTemplate<Configuracion> {

    ConfiguracionModelAssembler configuracionAssembler = new ConfiguracionModelAssembler();

    @Override
    public CRUDUseCase<Configuracion> getUc() {
        return PosCoreModule.getInstance().getImplementation(ConfiguracionService.class);
    }

    @Override
    public CrudModelAssembler<Configuracion> getAssembler() {
        return configuracionAssembler;
    }

}
