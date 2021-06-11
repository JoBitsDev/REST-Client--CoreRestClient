/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.producto;

import com.jobits.pos.controller.productos.ProductoVentaDetailService;
import com.jobits.pos.core.client.rest.assembler.ProductoVentaModelAssembler;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/producto_venta_detail")
public class ProductoVentaDetailEndPoint extends CrudRestServiceTemplate<ProductoVenta> {

    public static final String AGREGAR_INSUMO_A_PRODUCTO_PATH = "/agregar_insumo_a_producto";
    public static final RequestMethod AGREGAR_INSUMO_A_PRODUCTO_METHOD = RequestMethod.POST;

    public static final String ELIMINAR_INSUMO_PRODUCTO_PATH = "/eliminar_insumo_producto";
    public static final RequestMethod ELIMINAR_INSUMO_PRODUCTO_METHOD = RequestMethod.DELETE;

    ProductoVentaModelAssembler productoVentaAssembler = new ProductoVentaModelAssembler();

    @Override
    public ProductoVentaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(ProductoVentaDetailService.class);
    }

    @Override
    public CrudModelAssembler<ProductoVenta> getAssembler() {
        return productoVentaAssembler;
    }

    @PostMapping(AGREGAR_INSUMO_A_PRODUCTO_PATH)
    public boolean agregarInsumoaProducto(@RequestBody ProductoVenta producto, @RequestBody Insumo insumo_disponible_sel, @RequestParam float cantidad) {
        getUc().agregarInsumoaProducto(producto, insumo_disponible_sel, cantidad);
        return true;
    }

    @DeleteMapping(ELIMINAR_INSUMO_PRODUCTO_PATH)
    public boolean eliminarInsumoProducto(@RequestBody ProductoVenta producto, @RequestBody ProductoInsumo insumo_contenido_seleccionado) {
        getUc().eliminarInsumoProducto(producto, insumo_contenido_seleccionado);
        return true;
    }
}
