/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.puntoelaboracion;

import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import javax.websocket.server.PathParam;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    public static final String LIST_NAMES_URL = "/list/names";

    public static final String LIST = "/list";

    @Override
    public PuntoElaboracionService getUc() {
        return PosCoreModule.getInstance().getImplementation(PuntoElaboracionService.class);
    }

    @Override
    public Cocina destroyInCascade(String string) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PutMapping(EDIT_COCINA_PATH)
    public Cocina edit(@PathVariable("id") String id, @PathVariable("newName") String newName) {
        return getUc().edit(id, newName);
    }

    @GetMapping(LIST_NAMES_URL)
    synchronized ResponseEntity<List<String>> findAllToString() {
        List<String> ret = new ArrayList<>();
        getUc().findAll().forEach(a -> {
            ret.add(a.getCodCocina());
        });
        return ResponseEntity.ok(ret);
    }

}
