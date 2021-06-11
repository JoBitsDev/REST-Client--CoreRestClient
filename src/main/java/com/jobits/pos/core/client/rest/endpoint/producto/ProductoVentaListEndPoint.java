/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.producto;

import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.core.client.rest.assembler.ProductoVentaModelAssembler;
import static com.jobits.pos.core.client.rest.endpoint.producto.ProductoVentaDetailEndPoint.AGREGAR_INSUMO_A_PRODUCTO_PATH;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/producto_venta_list")
public class ProductoVentaListEndPoint extends CrudRestServiceTemplate<ProductoVenta> {

    public static final String BULK_IMPORT_PRODUCTO_VENTA_PATH = "/bulk_import_producto_venta";
    public static final RequestMethod BULK_IMPORT_PRODUCTO_VENTA_METHOD = RequestMethod.GET;

    public static final String BULK_IMPORT_PRODUCTO_INSUMO_PATH = "/bulk_import_producto_insumo";
    public static final RequestMethod BULK_IMPORT_PRODUCTO_INSUMO_METHOD = RequestMethod.GET;

    ProductoVentaModelAssembler productoVentaAssembler = new ProductoVentaModelAssembler();

    @Override
    public ProductoVentaListService getUc() {
        return PosCoreModule.getInstance().getImplementation(ProductoVentaListService.class);
    }

    @Override
    public CrudModelAssembler<ProductoVenta> getAssembler() {
        return productoVentaAssembler;
    }

    @GetMapping(BULK_IMPORT_PRODUCTO_VENTA_PATH)
    public boolean bulkImportProductoInsumo(@RequestBody List<ProductoInsumo> productoInsumoList) {
        return getUc().bulkImportProductoInsumo(productoInsumoList);
    }

    @GetMapping(BULK_IMPORT_PRODUCTO_INSUMO_PATH)
    public boolean bulkImport(@RequestBody List<ProductoVenta> importList) {
        return getUc().bulkImport(importList);
    }
}
