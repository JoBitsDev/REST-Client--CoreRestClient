/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.area;

import com.jobits.pos.controller.areaventa.AreaDetailService;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.core.client.rest.assembler.AreaModelAssembler;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
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
public class AreaListEndPoint extends CrudRestServiceTemplate<Area> {

    AreaModelAssembler areaAssembler = new AreaModelAssembler();

    public static final String LIST_NAMES_URL = "/list/names";
    public static final String LIST_MESAS_URL = "/{id}/list-mesas";
    public static final String LIST_MESAS_VACIAS_NAMES_URL = "/mostrar-vacias/names";

    @Override
    synchronized public AreaVentaService getUc() {
        return PosCoreModule.getInstance().getImplementation(AreaVentaService.class);
    }

    @Override
    synchronized public CrudModelAssembler<Area> getAssembler() {
        return areaAssembler;
    }

    @GetMapping(LIST_MESAS_URL)
    synchronized ResponseEntity<List<Mesa>> findMesasOfArea(@PathVariable("id") String codArea) {
       // getUc().findAll();
        return ResponseEntity.ok(getUc().findBy(codArea).getMesaList());
    }

    @GetMapping(LIST_NAMES_URL)
    synchronized ResponseEntity<List<String>> findAllToString() {
        List<String> ret = new ArrayList<>();
        getUc().findAll().forEach(a -> {
            ret.add(a.getCodArea());
        });
        return ResponseEntity.ok(ret);
    }

    @GetMapping(LIST_MESAS_VACIAS_NAMES_URL)
    synchronized ResponseEntity<List<String>> findEmptyTablesNames() {
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
