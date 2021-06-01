/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.TransaccionListService;
import com.jobits.pos.core.client.rest.assembler.TransaccionModelAssembler;
import com.jobits.pos.core.domain.models.Transaccion;
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
@RequestMapping(path = "/transaccion_list")
public class TransaccionListEndPoint extends CrudRestServiceTemplate<Transaccion> {

    TransaccionModelAssembler transaccionAssembler = new TransaccionModelAssembler();

    @Override
    public CRUDUseCase<Transaccion> getUc() {
        return PosCoreModule.getInstance().getImplementation(TransaccionListService.class);
    }

    @Override
    public CrudModelAssembler<Transaccion> getAssembler() {
        return transaccionAssembler;
    }

}
