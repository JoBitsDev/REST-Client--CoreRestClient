/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.puntoelaboracion;

import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.module.PosCoreModule;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/punto-elaboracion")
public class PuntoElaboracionListEndPoint extends CrudRestEndPointTemplate<Cocina, PuntoElaboracionService>
        implements PuntoElaboracionService {

    public static final String DESTROY_IN_CASCADE_PATH = "/destroy-cascade/{idCocina}";
    public static final RequestMethod DESTROY_IN_CASCADE_METHOD = RequestMethod.DELETE;

    public static final String EDIT_COCINA_PATH = "/{id}/edit-name/{newName}";
    public static final RequestMethod EDIT_COCINA_METHOD = RequestMethod.PUT;

    @Override
    public PuntoElaboracionService getUc() {
        return PosCoreModule.getInstance().getImplementation(PuntoElaboracionService.class);
    }

    @DeleteMapping(DESTROY_IN_CASCADE_PATH)
    @Override
    public Cocina destroyInCascade(@PathVariable("idCocina") String nombreCocina) {
        return getUc().destroyInCascade(nombreCocina);
    }

    @PutMapping(EDIT_COCINA_PATH)
    @Override
    public Cocina edit(@PathVariable("id") String codCocina, @PathVariable("newName") String newName) {
        return getUc().edit(codCocina, newName);
    }
}
