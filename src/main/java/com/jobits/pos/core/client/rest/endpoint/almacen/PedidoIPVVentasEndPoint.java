/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.core.client.rest.assembler.PedidoIpvVentaModelAssembler;
import com.jobits.pos.core.domain.InsumoPedidoModel;
import com.jobits.pos.core.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.IpvVentaRegistro;
import com.jobits.pos.inventario.core.almacen.domain.wrapper.PedidoIpvWrapper;
import com.jobits.pos.inventario.core.almacen.usecase.PedidoIpvVentasService;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/ipv/productos")
public class PedidoIPVVentasEndPoint extends CrudRestServiceTemplate<IpvVentaRegistro> {

    public static final String REALIZAR_PEDIDO_IPV_PATH = "/realizar-pedido/{idVenta}/{codCocinaDestino}/{codAlmacenOrigen}";

    PedidoIpvVentaModelAssembler pedidoIpvAssembler = new PedidoIpvVentaModelAssembler();

    @Override
    public PedidoIpvVentasService getUc() {
        return PosCoreModule.getInstance().getImplementation(PedidoIpvVentasService.class);
    }

    @Override
    public CrudModelAssembler<IpvVentaRegistro> getAssembler() {
        return pedidoIpvAssembler;
    }

    @PutMapping(REALIZAR_PEDIDO_IPV_PATH)
    public boolean realizarPedidoDeIpv(@RequestBody PedidoIpvWrapper wrapper,
            @PathVariable("codCocinaDestino") String puntoDestino,
            @PathVariable("codAlmacenOrigen") String almacenOrigen,
            @PathVariable("idVenta") int venta) {
        getUc().realizarPedidoDeIpv(wrapper, puntoDestino, almacenOrigen, venta);
        return true;
    }

}
