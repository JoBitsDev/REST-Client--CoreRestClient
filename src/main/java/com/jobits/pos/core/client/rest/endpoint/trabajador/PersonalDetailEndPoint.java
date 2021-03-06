/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.controller.trabajadores.PersonalDetailService;
import com.jobits.pos.core.client.rest.assembler.PersonalModelAssembler;
import com.jobits.pos.core.domain.models.Personal;
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
@RequestMapping(path = "/personal_detail")
public class PersonalDetailEndPoint extends CrudRestServiceTemplate<Personal> {

    PersonalModelAssembler personalAssembler = new PersonalModelAssembler();

    @Override
    public CRUDUseCase<Personal> getUc() {
        return PosCoreModule.getInstance().getImplementation(PersonalDetailService.class);
    }

    @Override
    public CrudModelAssembler<Personal> getAssembler() {
        return personalAssembler;
    }

}
