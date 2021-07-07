/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.almacen.PedidoIPVVentasEndPoint;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class PedidoIpvVentaModelAssembler extends CrudModelAssembler<IpvVentaRegistro> {

    public PedidoIpvVentaModelAssembler() {
        super(PedidoIPVVentasEndPoint.class);
    }

    @Override
    public Object getId(IpvVentaRegistro entity) {
        return entity.getIpvVentaRegistroPK();
    }
}
