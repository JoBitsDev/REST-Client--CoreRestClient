/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.controller.trabajadores.PuestoTrabajoListService;
import com.jobits.pos.core.client.rest.assembler.PuestoTrabajoModelAssembler;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
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
@RequestMapping(path = "/puesto_trabajo_list")
public class PuestoTrabajoListEndPoint extends CrudRestServiceTemplate<PuestoTrabajo> {

    PuestoTrabajoModelAssembler puestoTrabajoAssembler = new PuestoTrabajoModelAssembler();

    @Override
    public CRUDUseCase<PuestoTrabajo> getUc() {
        return PosCoreModule.getInstance().getImplementation(PuestoTrabajoListService.class);
    }

    @Override
    public CrudModelAssembler<PuestoTrabajo> getAssembler() {
        return puestoTrabajoAssembler;
    }

}
