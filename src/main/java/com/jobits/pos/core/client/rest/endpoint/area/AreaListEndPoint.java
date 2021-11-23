/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.area;

import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.ArrayList;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/area-venta")
public class AreaListEndPoint extends CrudRestEndPointTemplate<Area, AreaVentaService> implements AreaVentaService{

    public static final String LIST_NAMES_URL = "/list/names";
    public static final String LIST_MESAS_URL = "/{id}/list-mesas";
    public static final String LIST_MESAS_VACIAS_NAMES_URL = "/mostrar-vacias/names";

    @Override
    public AreaVentaService getUc() {
        return PosCoreModule.getInstance().getImplementation(AreaVentaService.class);
    }

    @GetMapping(LIST_MESAS_URL)
    ResponseEntity<List<Mesa>> findMesasOfArea(@PathVariable("id") String codArea) {
        return ResponseEntity.ok(getUc().findBy(codArea).getMesaList());
    }

    @GetMapping(LIST_NAMES_URL)
    ResponseEntity<List<String>> findAllToString() {
        List<String> ret = new ArrayList<>();
        getUc().findAll().forEach(a -> {
            ret.add(a.getCodArea());
        });
        return ResponseEntity.ok(ret);
    }

    @GetMapping(LIST_MESAS_VACIAS_NAMES_URL)
    ResponseEntity<List<String>> findEmptyTablesNames() {
        List<String> ret = new ArrayList<>();
        for (Area a : getUc().findAll()) {
            for (Mesa m : a.getMesaList()) {
                if (m.getEstado().equals("vacia")) {
                    ret.add(m.getCodMesa());
                }
            }
        }
        return ResponseEntity.ok(ret);
    }

}
