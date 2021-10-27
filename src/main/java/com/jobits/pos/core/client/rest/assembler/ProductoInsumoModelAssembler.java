/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.producto.ProductoInsumoListEndPoint;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class ProductoInsumoModelAssembler extends CrudModelAssembler<ProductoInsumo> {

    public ProductoInsumoModelAssembler() {
        super(ProductoInsumoListEndPoint.class);
    }

    @Override
    public Object getId(ProductoInsumo entity) {
        return entity.getProductoInsumoPK();
    }
    
}
