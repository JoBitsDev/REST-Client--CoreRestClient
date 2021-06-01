/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.AlmacenListService;
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.core.client.rest.assembler.AlmacenModelAssembler;
import com.jobits.pos.core.domain.models.Almacen;
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
@RequestMapping(path = "/almacen_list")
public class AlmacenListEndPoint extends CrudRestServiceTemplate<Almacen> {

    AlmacenModelAssembler almacenAssembler = new AlmacenModelAssembler();

    @Override
    public CRUDUseCase<Almacen> getUc() {
        return PosCoreModule.getInstance().getImplementation(AlmacenListService.class);
    }

    @Override
    public CrudModelAssembler<Almacen> getAssembler() {
        return almacenAssembler;
    }

}
