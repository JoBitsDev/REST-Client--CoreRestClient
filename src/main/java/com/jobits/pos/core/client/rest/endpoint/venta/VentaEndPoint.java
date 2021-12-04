/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.VentaResourcesWrapper;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/venta")
public class VentaEndPoint extends CrudRestEndPointTemplate<Venta, VentaDetailService>
        implements VentaDetailService {

    @Override
    public VentaDetailService getUc() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> getVentasDeFecha(LocalDate date) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> getVentasDeFecha(int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta cambiarTurno(int idTurnoATerminar) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canOpenNuevoTurno(LocalDate fecha) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canOpenNuevoTurno(int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canReabrirVenta(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden createNewOrden(int codVenta, String codMesa) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getAutorizosTotalDelProducto(String codProductoVenta, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float getGastoTotalDeInsumo(String codCocina, String codInsumo, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Orden> getOrdenesActivas(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float getPagoTrabajador(String codPersonal, int codVenta, int dividirEntre) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float getPropinaTrabajador(String codPersonal, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importarVenta(File file) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta inicializarVentas(LocalDate fecha, boolean nuevaVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printGastosCasa(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPagoPorVentaPersonal(String codPersonal, int codVenta, boolean printValores) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printComisionPorcentualResumen(String codMesa, int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printZ(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reabrirVentas(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void terminarVentas(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void terminarYExportar(File file, int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }


}
