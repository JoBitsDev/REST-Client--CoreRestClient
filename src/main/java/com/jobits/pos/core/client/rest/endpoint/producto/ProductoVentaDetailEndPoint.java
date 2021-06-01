/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.producto;

import com.jobits.pos.controller.productos.ProductoVentaDetailService;
import com.jobits.pos.core.client.rest.assembler.ProductoVentaModelAssembler;
import com.jobits.pos.core.domain.models.ProductoVenta;
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
@RequestMapping(path = "/producto_venta_detail")
public class ProductoVentaDetailEndPoint extends CrudRestServiceTemplate<ProductoVenta> {

    ProductoVentaModelAssembler productoVentaAssembler = new ProductoVentaModelAssembler();

    @Override
    public CRUDUseCase<ProductoVenta> getUc() {
        return PosCoreModule.getInstance().getImplementation(ProductoVentaDetailService.class);
    }

    @Override
    public CrudModelAssembler<ProductoVenta> getAssembler() {
        return productoVentaAssembler;
    }

}
