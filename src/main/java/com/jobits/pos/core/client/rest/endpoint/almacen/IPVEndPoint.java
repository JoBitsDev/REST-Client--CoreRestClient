/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.IPVService;
import com.jobits.pos.core.client.rest.assembler.IpvModelAssembler;
import com.jobits.pos.core.domain.models.Ipv;
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
@RequestMapping(path = "/ipv")
public class IPVEndPoint extends CrudRestServiceTemplate<Ipv> {

    IpvModelAssembler ipvAssembler = new IpvModelAssembler();

    @Override
    public CRUDUseCase<Ipv> getUc() {
        return PosCoreModule.getInstance().getImplementation(IPVService.class);
    }

    @Override
    public CrudModelAssembler<Ipv> getAssembler() {
        return ipvAssembler;
    }

}
