/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.resumen.VentaResumenUseCase;
import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.VentaResumenWrapper;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.jobits.pos.client.rest.endpoint.DefaultEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/venta")
public class VentaResumenEndPoint extends DefaultEndpoint
        implements VentaResumenUseCase {

    public VentaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaDetailService.class);
    }

    @Override
    public List<ProductovOrden> getResumenPorMesa(int codVenta, Mesa mesa) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductovOrden> getResumenPorPersonal(int codVenta, Personal personal) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductovOrden> getResumenPorCocina(int codVenta, Cocina cocina) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductovOrden> getResumenPorArea(int codVenta, Area area) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenMesa(int codVenta, Mesa mesa) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenDependiente(int codVenta, Personal personal) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenCocina(int codVenta, Cocina cocina) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenArea(int codVenta, Area area) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaResumenWrapper getResumenVenta(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getVentaTotalDelProducto(ProductoVenta productoVenta, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printMesaResumen(Mesa mesa, int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAreaResumen(Area a, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPersonalResumenRow(Personal p, int codVenta, boolean printValores) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printCocinaResumen(String codCocina, int codVenta, boolean printValores) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
