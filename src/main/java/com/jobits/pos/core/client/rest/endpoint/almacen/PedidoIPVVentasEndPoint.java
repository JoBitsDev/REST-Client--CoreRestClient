/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.PedidoIpvVentasService;
import com.jobits.pos.core.client.rest.assembler.PedidoIpvVentaModelAssembler;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
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
@RequestMapping(path = "/pedido_ipv")
public class PedidoIPVVentasEndPoint extends CrudRestServiceTemplate<IpvVentaRegistro> {

    PedidoIpvVentaModelAssembler pedidoIpvAssembler = new PedidoIpvVentaModelAssembler();

    @Override
    public CRUDUseCase<IpvVentaRegistro> getUc() {
        return PosCoreModule.getInstance().getImplementation(PedidoIpvVentasService.class);
    }

    @Override
    public CrudModelAssembler<IpvVentaRegistro> getAssembler() {
        return pedidoIpvAssembler;
    }

}
