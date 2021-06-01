/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.producto.ProductoVentaDetailEndPoint;
import com.jobits.pos.core.domain.models.ProductoVenta;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class ProductoVentaModelAssembler extends CrudModelAssembler<ProductoVenta> {

    public ProductoVentaModelAssembler() {
        super(ProductoVentaDetailEndPoint.class);
    }

    @Override
    public Object getId(ProductoVenta entity) {
        return entity.getCodigoProducto();
    }

}
