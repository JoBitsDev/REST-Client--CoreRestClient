/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.seccion;

import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.core.client.rest.assembler.SeccionModelAssembler;
import com.jobits.pos.core.domain.models.Seccion;
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
@RequestMapping(path = "/seccion_list")
public class SeccionListEndPoint extends CrudRestServiceTemplate<Seccion> {

    SeccionModelAssembler seccionAssembler = new SeccionModelAssembler();

    @Override
    public CRUDUseCase<Seccion> getUc() {
        return PosCoreModule.getInstance().getImplementation(SeccionListService.class);
    }

    @Override
    public CrudModelAssembler<Seccion> getAssembler() {
        return seccionAssembler;
    }

}
