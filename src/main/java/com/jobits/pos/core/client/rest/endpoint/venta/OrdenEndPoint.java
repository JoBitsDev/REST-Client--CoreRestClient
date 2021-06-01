/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.client.rest.assembler.OrdenModelAssembler;
import com.jobits.pos.core.domain.models.Orden;
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
@RequestMapping(path = "/orden")
public class OrdenEndPoint extends CrudRestServiceTemplate<Orden> {

    OrdenModelAssembler ordenAssembler = new OrdenModelAssembler();

    @Override
    public CRUDUseCase<Orden> getUc() {
        return PosCoreModule.getInstance().getImplementation(OrdenService.class);
    }

    @Override
    public CrudModelAssembler<Orden> getAssembler() {
        return ordenAssembler;
    }

}
