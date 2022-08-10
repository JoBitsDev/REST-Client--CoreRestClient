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
import javax.websocket.server.PathParam;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/list-of-date/{dateISO}")
    public List<Venta> getVentasDeFecha(@PathVariable("dateISO") LocalDate date) {
        return getUc().getVentasDeFecha(date);
    }

    @Override
    @GetMapping("/{id}/list-all")
    public List<Venta> getVentasDeFecha(@PathVariable("id") int idVenta) {
        return getUc().getVentasDeFecha(idVenta);
    }

    @Override
    @PostMapping("/{id}/cambiar-turno")
    public Venta cambiarTurno(@PathVariable("id") int idTurnoATerminar) {
        return getUc().cambiarTurno(idTurnoATerminar);
    }

    @Override
    @GetMapping("/can-open-nuevo-turno/{dateISO}")
    public boolean canOpenNuevoTurno(@PathVariable("dateISO") LocalDate fecha) {
        return getUc().canOpenNuevoTurno(fecha);
    }

    @Override
    @GetMapping("/{id}/can-open-nuevo-turno")
    public boolean canOpenNuevoTurno(@PathVariable("id") int idVenta) {
        return getUc().canOpenNuevoTurno(idVenta);
    }

    @Override
    @GetMapping("/{id}/can-reabrir")
    public boolean canReabrirVenta(@PathVariable("id") int codVenta) {
        return getUc().canReabrirVenta(codVenta);
    }

    @Override
    @PostMapping("/{id}/create-new-orden/{idMesa}")
    public Orden createNewOrden(@PathVariable("id") int codVenta, @PathVariable("idMesa") String codMesa) {
        return getUc().createNewOrden(codVenta, codMesa);
    }

    @Override
    @GetMapping("/{id}/ordenes-activas")
    public List<Orden> getOrdenesActivas(@PathVariable("id") int codVenta) {
        return getUc().getOrdenesActivas(codVenta);
    }

    @Override
    @GetMapping("/{id}/get-pago-trabajador/{dividirEntre}/{codPersonal}")
    public Float getPagoTrabajador(@PathVariable("codPersonal") String codPersonal, int codVenta, int dividirEntre) {
        throw new UnsupportedOperationException("Operacion no permitida"); //   return getUc().getAutorizosTotalDelProducto(codProductoVenta, codVenta);
    }

    @Override
    public Float getPropinaTrabajador(String codPersonal, int codVenta) {
        throw new UnsupportedOperationException("Operacion no permitida"); //   return getUc().getAutorizosTotalDelProducto(codProductoVenta, codVenta);
    }

    @Override
    @PutMapping("/importar")
    public Venta importarVenta(String file) {//TODO; implementar
        return getUc().importarVenta(file);
    }

    @Override
    @PostMapping("/inicializar/{dateISO}")
    public Integer inicializarVentas(@PathVariable("dateISO") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return getUc().inicializarVentas(fecha);
    }

    @Override
    @PostMapping("/{id}/print/autorizos")
    public void printGastosCasa(@PathVariable("{id}") int codVenta) {
        getUc().printGastosCasa(codVenta);
    }

    @Override
    @PostMapping("/{id}/print/pago-por-venta/{usuario}")
    public void printPagoPorVentaPersonal(@PathVariable("usuario") String codPersonal,
            @PathVariable("id") int codVenta, @PathParam("printValues") boolean printValores) {
        getUc().printPagoPorVentaPersonal(codPersonal, codVenta, printValores);
    }

    @Override
    @PostMapping("/{id}/print/comision-pocentual/{codMesa}")
    public void printComisionPorcentualResumen(@PathVariable("codMesa") String codMesa, @PathVariable("id") int idVenta) {
        getUc().printComisionPorcentualResumen(codMesa, idVenta);
    }

    @Override
    @PostMapping("/{id}/print/z")
    public void printZ(@PathVariable("id") int codVenta) {
        getUc().printZ(codVenta);
    }

    @Override
    @PostMapping("/{id}/reabrir-ventas")
    public boolean reabrirVentas(@PathVariable("id") int codVenta) {
        return getUc().reabrirVentas(codVenta);
    }

    @Override
    @PostMapping("/{id}/terminar-ventas")
    public boolean terminarVentas(@PathParam("id") int codVenta) {
        return getUc().terminarVentas(codVenta);
    }

    @Override
    @PostMapping("/{id}/terminar-y-exportar")
    public String terminarYExportar(@PathVariable("id") int codVenta) {//TODO: implementar
        return getUc().terminarYExportar(codVenta);
    }

    @GetMapping("/listar-ventas-id/fecha/{dateISO}")
    @Override
    public List<Integer> getVentasIds(@PathVariable(value = "dateISO")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return getUc().getVentasIds(date);
    }

    @GetMapping("/listar-ventas-id/{id}")
    @Override
    public List<Integer> getVentasIds(@PathVariable("id") int idVenta) {
        return getUc().getVentasIds(idVenta);
    }

}
