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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/almacen_list")
public class AlmacenListEndPoint extends CrudRestServiceTemplate<Almacen> {

    public static final String RESET_ALMACEN_PATH = "/reset_almacen";
    public static final RequestMethod RESET_ALMACEN_METHOD = RequestMethod.PUT;

    AlmacenModelAssembler almacenAssembler = new AlmacenModelAssembler();

    @Override
    public AlmacenListService getUc() {
        return PosCoreModule.getInstance().getImplementation(AlmacenListService.class);
    }

    @Override
    public CrudModelAssembler<Almacen> getAssembler() {
        return almacenAssembler;
    }

    @PutMapping(RESET_ALMACEN_PATH)
    public boolean resetAlmacen(Almacen almacen) {
        getUc().resetAlmacen(almacen);
        return true;
    }
}
