/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.escandallo;

import com.jobits.pos.controller.escandallo.InsumoRegistroService;
import com.jobits.pos.core.client.rest.assembler.InsumoRegistroModelAssembler;
import com.jobits.pos.core.domain.models.escandallos.InsumoRegistro;
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
@RequestMapping(path = "/insumo_registro")
public class InsumoRegistroEndPoint extends CrudRestServiceTemplate<InsumoRegistro> {

    InsumoRegistroModelAssembler insumoRegistroAssembler = new InsumoRegistroModelAssembler();

    @Override
    public CRUDUseCase<InsumoRegistro> getUc() {
        return PosCoreModule.getInstance().getImplementation(InsumoRegistroService.class);
    }

    @Override
    public CrudModelAssembler<InsumoRegistro> getAssembler() {
        return insumoRegistroAssembler;
    }

}
