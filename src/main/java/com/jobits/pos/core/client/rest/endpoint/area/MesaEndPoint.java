/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.area;

import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/mesa")
public class MesaEndPoint extends CrudRestEndPointTemplate<Mesa,MesaService> implements MesaService{


    @Override
    public MesaService getUc() {
        return PosCoreModule.getInstance().getImplementation(MesaService.class);
    }

 

}
