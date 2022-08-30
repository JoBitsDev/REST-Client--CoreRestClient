/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.producto;

import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/products")
public class ProductoVentaEndPoint extends CrudRestEndPointTemplate<ProductoVenta, ProductoVentaService>
        implements ProductoVentaService {

    @Override
    public ProductoVentaService getUc() {
        return PosCoreModule.getInstance().getImplementation(ProductoVentaService.class);
    }

    @Override
    @PostMapping("/bulk-import")
    public List<ProductoVenta> bulkImport(@RequestBody List<ProductoVenta> importList) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }
}
