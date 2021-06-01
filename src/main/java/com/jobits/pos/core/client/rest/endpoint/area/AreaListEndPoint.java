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
@RequestMapping(path = "/area_venta")
public class AreaListEndPoint extends CrudRestServiceTemplate<Area> {

    AreaModelAssembler areaAssembler = new AreaModelAssembler();

    @Override
    public CRUDUseCase<Area> getUc() {
        return PosCoreModule.getInstance().getImplementation(AreaVentaService.class);
    }

    @Override
    public CrudModelAssembler<Area> getAssembler() {
        return areaAssembler;
    }

}
