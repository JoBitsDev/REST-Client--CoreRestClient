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
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.module.PosCoreModule;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import org.jobits.pos.client.rest.endpoint.DefaultEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/venta/resumen")
public class VentaResumenEndPoint extends DefaultEndpoint
        implements VentaResumenUseCase {

    public VentaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaDetailService.class);
    }

    @Override
    public List<ProductovOrden> getResumenPorMesa(int codVenta, String codMesa) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductovOrden> getResumenPorPersonal(int codVenta, String codPersonal) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductovOrden> getResumenPorCocina(int codVenta, String codCocina) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductovOrden> getResumenPorArea(int codVenta, String codArea) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenMesa(int codVenta, String mesa) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenDependiente(int codVenta, String personal) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenCocina(int codVenta, String cocina) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTotalResumenArea(int codVenta, String area) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaResumenWrapper getResumenVenta(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaResourcesWrapper getVentaResources(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printMesaResumen(String codMesa, int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAreaResumen(String codArea, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPersonalResumenRow(String codPersonal, int codVenta, boolean printValores) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printCocinaResumen(String codCocina, int codVenta, boolean printValores) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    //  @GetMapping("{id}/get-autorizos-producto")
    public float getAutorizosTotalDelProducto(String codProductoVenta, int codVenta) {
        throw new UnsupportedOperationException("Operacion no permitida"); //   return getUc().getAutorizosTotalDelProducto(codProductoVenta, codVenta);
    }

    @Override
    public Map<String, Float> getGastoTotalDeInsumo(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getVentaTotalDelProducto(String codProductoVenta, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
