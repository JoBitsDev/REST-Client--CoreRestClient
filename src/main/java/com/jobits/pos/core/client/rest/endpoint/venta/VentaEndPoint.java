/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import java.io.File;
import java.time.LocalDate;
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
@RequestMapping(path = "pos/venta")
public class VentaEndPoint extends CrudRestEndPointTemplate<Venta, VentaDetailService>
        implements VentaDetailService {

    @Override
    public VentaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaDetailService.class);
    }

    @Override
    public List<Venta> getVentasDeFecha(LocalDate date) {
        return getUc().getVentasDeFecha(date);
    }

    @Override
    public List<Venta> getVentasDeFecha(int idVenta) {
        return getUc().getVentasDeFecha(idVenta);
    }

    @Override
    public Venta cambiarTurno(int idTurnoATerminar) {
        return getUc().cambiarTurno(idTurnoATerminar);
    }

    @Override
    public boolean canOpenNuevoTurno(LocalDate fecha) {
        return getUc().canOpenNuevoTurno(fecha);
    }

    @Override
    public boolean canOpenNuevoTurno(int idVenta) {
        return getUc().canOpenNuevoTurno(idVenta);
    }

    @Override
    public boolean canReabrirVenta(int codVenta) {
        return getUc().canReabrirVenta(codVenta);
    }

    @Override
    public Orden createNewOrden(int codVenta, String codMesa) {
        return getUc().createNewOrden(codVenta, codMesa);
    }

    @Override
    public float getAutorizosTotalDelProducto(String codProductoVenta, int codVenta) {
        return getUc().getAutorizosTotalDelProducto(codProductoVenta, codVenta);
    }

    @Override
    public Float getGastoTotalDeInsumo(String codCocina, String codInsumo, int codVenta) {
        return getUc().getGastoTotalDeInsumo(codCocina, codInsumo, codVenta);
    }

    @Override
    public List<Orden> getOrdenesActivas(int codVenta) {
        return getUc().getOrdenesActivas(codVenta);
    }

    @Override
    public Float getPagoTrabajador(String codPersonal, int codVenta, int dividirEntre) {
        return getUc().getPagoTrabajador(codPersonal, codVenta, dividirEntre);
    }

    @Override
    public Float getPropinaTrabajador(String codPersonal, int codVenta) {
        return getUc().getPropinaTrabajador(codPersonal, codVenta);
    }

    @Override
    public void importarVenta(File file) {//TODO; implementar
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostMapping("/inicializar")
    public Venta inicializarVentas(@RequestBody LocalDate fecha) {
        return getUc().inicializarVentas(fecha);
    }

    @Override
    public void printGastosCasa(int codVenta) {
        getUc().printGastosCasa(codVenta);
    }

    @Override
    public void printPagoPorVentaPersonal(String codPersonal, int codVenta, boolean printValores) {
        getUc().printPagoPorVentaPersonal(codPersonal, codVenta, printValores);
    }

    @Override
    public void printComisionPorcentualResumen(String codMesa, int idVenta) {
        getUc().printComisionPorcentualResumen(codMesa, idVenta);
    }

    @Override
    public void printZ(int codVenta) {
        getUc().printZ(codVenta);
    }

    @Override
    public void reabrirVentas(int codVenta) {
        getUc().reabrirVentas(codVenta);
    }

    @Override
    public void terminarVentas(int codVenta) {
        getUc().terminarVentas(codVenta);
    }

    @Override
    public void terminarYExportar(File file, int codVenta) {//TODO: implementar
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
