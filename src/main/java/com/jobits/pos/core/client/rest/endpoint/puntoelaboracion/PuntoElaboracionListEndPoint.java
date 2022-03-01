/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.puntoelaboracion;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.ArrayList;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/punto_elaboracion_list")
public class PuntoElaboracionListEndPoint extends CrudRestEndPointTemplate<Cocina, PuntoElaboracionListService> {

    public static final String DESTROY_IN_CASCADE_PATH = "/destroy_in_cascade";
    public static final RequestMethod DESTROY_IN_CASCADE_METHOD = RequestMethod.DELETE;

    public static final String EDIT_COCINA_PATH = "/edit_cocina";
    public static final RequestMethod EDIT_COCINA_METHOD = RequestMethod.PUT;

    public static final String LIST_NAMES_URL = "/list/names";

    @Override
    public PuntoElaboracionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(PuntoElaboracionListService.class);
    }

    @GetMapping(LIST_NAMES_URL)
    ResponseEntity<List<String>> findAllToString() {
        List<String> ret = new ArrayList<>();
        getUc().findAll().forEach(a -> {
            ret.add(a.getCodCocina());
        });
        return ResponseEntity.ok(ret);
    }

}
