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
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/venta")
public class VentaEndPoint extends CrudRestEndPointTemplate<Venta, VentaDetailService> implements VentaDetailService, VentaResumenUseCase {


    @Override
    public VentaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaDetailService.class);
    }

    public VentaResumenUseCase getUcR() {
        return PosCoreModule.getInstance().getImplementation(VentaResumenUseCase.class);
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
    @GetMapping(value = "/can-open-nuevo-turno/{dateISO}")
    public int canOpenNuevoTurno(@PathVariable("dateISO")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return getUc().canOpenNuevoTurno(fecha);
    }

    @Override
    @GetMapping("/{id}/can-open-nuevo-turno")
    public int canOpenNuevoTurno(@PathVariable("id") int idVenta) {
        return getUc().canOpenNuevoTurno(idVenta);
    }

    @Override
    @GetMapping("/{id}/can-reabrir")
    public int canReabrirVenta(@PathVariable("id") int codVenta) {
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
    public Float getPagoTrabajador(@PathVariable("codPersonal") String codPersonal, @PathVariable("id") int codVenta, @PathVariable("dividirEntre") int dividirEntre) {
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
    public void printGastosCasa(@PathVariable("id") int codVenta) {
        getUc().printGastosCasa(codVenta);
    }

    @Override
    @PostMapping("/{id}/print/pago-por-venta/{usuario}")
    public void printPagoPorVentaPersonal(@PathVariable("usuario") String codPersonal, @PathVariable("id") int codVenta, @PathParam("printValues") boolean printValores) {
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
    public Venta reabrirVentas(@PathVariable("id") int codVenta) {
        return getUc().reabrirVentas(codVenta);
    }

    @Override
    @PostMapping("/{id}/terminar-ventas")
    public Venta terminarVentas(@PathVariable("id") int codVenta) {
        return getUc().terminarVentas(codVenta);
    }

    @Override
    @PostMapping("/{id}/terminar-y-exportar")
    public String terminarYExportar(@PathVariable("id") int codVenta) {//TODO: implementar
        return getUc().terminarYExportar(codVenta);
    }

    @Override
    @PutMapping("/{id}/set-propina/{cantidad}")
    public Float setPropina(@PathVariable("id") Integer integer, @PathVariable("cantidad") Float aFloat) {
        return getUc().setPropina(integer, aFloat);
    }

    @GetMapping("/listar-ventas-id/fecha/{dateISO}")
    @Override
    public List<Integer> getVentasIds(@PathVariable(value = "dateISO") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return getUc().getVentasIds(date);
    }

    @GetMapping("/find-integer/{id}")
    public Venta findByInteger(@PathVariable("id") int o) throws RuntimeException {
        return getUc().findBy(o);
    }

    @GetMapping("/listar-ventas-id/{id}")
    @Override
    public List<Integer> getVentasIds(@PathVariable("id") int idVenta) {
        return getUc().getVentasIds(idVenta);
    }

    @Override
    @GetMapping("/{id}/get-resumen")
    public VentaResumenWrapper getResumenVenta(@PathVariable("id") int idVenta) {
        return getUcR().getResumenVenta(idVenta);
    }


    @Override
    @GetMapping("/{id}/get-resources")
    public VentaResourcesWrapper getVentaResources(@PathVariable("id") int idVenta) {
        return getUcR().getVentaResources(idVenta);
    }

    @Override
    @GetMapping("/{id}/get-venta-total-producto")
    public Map<String, Float> getVentaTotalDelProducto(@PathVariable("id") int idVenta) {
        return getUcR().getVentaTotalDelProducto(idVenta);
    }

    @Override
    @GetMapping("/{id}/get-gasto-total-insumos")
    public Map<String, Float> getGastoTotalDeInsumo(@PathVariable("id") int idVenta) {
        return getUcR().getGastoTotalDeInsumo(idVenta);
    }

    @Override
    @GetMapping("/{id}/get-autorizos-total-producto")
    public Map<String, Float> getAutorizosTotalDelProducto(@PathVariable("id") int idVenta) {
        return getUcR().getGastoTotalDeInsumo(idVenta);
    }

    @Override
    @GetMapping("/{id}/print/mesa-resumen/{codMesa}")
    public void printMesaResumen(@PathVariable("codMesa") String s, @PathVariable("id") int idVenta) {
        getUcR().printMesaResumen(s, idVenta);
    }

    @Override
    @GetMapping("/{id}/print/area-resumen/{codArea}")
    public void printAreaResumen(@PathVariable("codArea") String s, @PathVariable("id") int idVenta) {
        getUcR().printAreaResumen(s, idVenta);

    }

    @Override
    @GetMapping("/{id}/print/personal-resumen/{usuario}/{printValues}")
    public void printPersonalResumenRow(@PathVariable("usuario") String personal,
                                        @PathVariable("id") int idVenta,
                                        @PathVariable("printValues") boolean printValues) {
        getUcR().printPersonalResumenRow(personal, idVenta, printValues);

    }

    @Override
    @GetMapping("/{id}/print/cocina-resumen/{idCocina}/{printValues}")
    public void printCocinaResumen(@PathVariable("idCocina") String cocina,
                                   @PathVariable("id") int idVenta,
                                   @PathVariable("printValues") boolean printValues) {
        getUcR().printCocinaResumen(cocina, idVenta, printValues);
    }
}
