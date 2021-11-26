/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.module.PosCoreModule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/personal")
public class PersonalEndPoint extends CrudRestEndPointTemplate<Personal, PersonalUseCase>
        implements
        PersonalUseCase {

    @Override
    public PersonalUseCase getUc() {
        return PosCoreModule.getInstance().getImplementation(PersonalUseCase.class);
    }

    @Override
    @PutMapping("/{usuario}/pagar")
    public Personal pagarTrabajador(@PathVariable("usuario") String usuario) {
        return getUc().pagarTrabajador(usuario);
    }

}
