/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.area;

import com.jobits.pos.controller.areaventa.AreaDetailService;
import com.jobits.pos.core.client.rest.assembler.AreaModelAssembler;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/area_detail")
public class AreaDetailEndPoint extends CrudRestServiceTemplate<Area> {

    public static final String GET_CARTA_LIST_PATH = "/get_carta_list";
    public static final RequestMethod GET_CARTA_LIST_METHOD = RequestMethod.GET;

    AreaModelAssembler areaAssembler = new AreaModelAssembler();

    @Override
    public AreaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(AreaDetailService.class);
    }

    @Override
    public CrudModelAssembler<Area> getAssembler() {
        return areaAssembler;
    }

    @GetMapping(GET_CARTA_LIST_PATH)
    public CollectionModel<EntityModel<Carta>> getCartaList() {
        throw new UnsupportedOperationException();
    }
}
