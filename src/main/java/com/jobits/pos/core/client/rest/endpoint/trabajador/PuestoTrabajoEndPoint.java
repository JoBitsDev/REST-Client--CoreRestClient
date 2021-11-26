/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.core.domain.models.PuestoTrabajo;
import com.jobits.pos.core.module.PosCoreModule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoUseCase;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/puesto-trabajo")
public class PuestoTrabajoEndPoint extends CrudRestEndPointTemplate<PuestoTrabajo, PuestoTrabajoUseCase>
        implements PuestoTrabajoUseCase {

    @Override
    public PuestoTrabajoUseCase getUc() {
        return PosCoreModule.getInstance().getImplementation(PuestoTrabajoUseCase.class);
    }

}
