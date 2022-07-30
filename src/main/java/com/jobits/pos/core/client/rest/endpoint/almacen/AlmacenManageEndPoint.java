/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(path = "pos/almacen")
public class AlmacenManageEndPoint extends CrudRestEndPointTemplate<Almacen, AlmacenManageService>
        implements AlmacenManageService {

    public static final String RESET_ALMACEN = "/{id}/reset-almacen";

    public static final String CREAR_OPERACION = "/{id}/crear-operacion/{tipo}/{recibo}/{fecha}/{idVenta}";

    public static final String OPERACIONES_PENDIENTES = "/{id}/operaciones-pendientes";

    public static final String EJECUTAR_OPERACION = "/{id}/ejecutar-operacion/{idOp}";

    public static final String ACTUALIZAR_OPERACION = "/{id}/actualizar-operacion";

    public static final String REGISTRAR_INSUMO = "/{id}/registrar-insumo/{codInsumo}";

    public static final String IMPRIMIR_REPORTE_COMPRAS = "/{id}/imprimir/reporte-compras";

    public static final String IMPRIMIR_RESUMEN = "/{id}/imprimir/resumen";

    public static final String BULK_IMPORT = "/bulk-import";

    public static final String ELIMINAR_INSUMO = "/eliminar-insumo";

    public static final String BUSCAR_INSUMO = "/{id}/insumo/{codInsumo}";

    @Override
    public AlmacenManageService getUc() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @PostMapping(CREAR_OPERACION)
    @Override
    public Operacion crearOperacion(@PathVariable("tipo") Operacion.Tipo tipoOp,
            @RequestBody List<TransaccionSimple> transacciones,
            @PathVariable("recibo") String recibo,
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFactura,
            @PathVariable("id") String codAlmacen,
            @PathVariable("idVenta") Integer codVenta) {
        return getUc().crearOperacion(tipoOp, transacciones, recibo, fechaFactura,
                recibo, codVenta);
    }

    @GetMapping(OPERACIONES_PENDIENTES)
    @Override
    public List<Operacion> getOperacionesPendientes(@PathVariable("id") String codAlmacen) {
        return getUc().getOperacionesPendientes(codAlmacen);
    }

    @PutMapping(ACTUALIZAR_OPERACION)
    @Override
    public void ejecutarOperacion(@PathVariable("id") String codAlmacen,
            @RequestBody Operacion operacionToUpdate) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @PutMapping(EJECUTAR_OPERACION)
    @Override
    public void ejecutarOperacion(@PathVariable("id") String codAlmacen,
            @PathVariable("idOp") int idOperacion) {
        getUc().ejecutarOperacion(codAlmacen, idOperacion);
    }

    @PutMapping(REGISTRAR_INSUMO)
    @Override
    public void agregarInsumoAlmacen(@PathVariable("codInsumo") String codInsumo, @PathVariable("id") String codAlmacen) {
        getUc().agregarInsumoAlmacen(codInsumo, codAlmacen);
    }

    @GetMapping(IMPRIMIR_REPORTE_COMPRAS)
    @Override
    public void imprimirReporteParaCompras(@PathVariable("id") String codAlmacen, int selection) {
        getUc().imprimirReporteParaCompras(codAlmacen, 1);
    }

    @GetMapping(IMPRIMIR_RESUMEN)
    @Override
    public void imprimirResumenAlmacen(@PathVariable("id") String codAlmacen) {
        getUc().imprimirResumenAlmacen(codAlmacen);
    }

    @DeleteMapping(ELIMINAR_INSUMO)
    @Override
    public void removeInsumoFromStorage(@RequestBody InsumoAlmacen insumoAlmacen) {
        getUc().removeInsumoFromStorage(insumoAlmacen);
    }

    @PostMapping(BULK_IMPORT)
    @Override
    public boolean bulkImport(@RequestBody List<InsumoAlmacen> importList) {
        return getUc().bulkImport(importList);
    }

    @GetMapping(BUSCAR_INSUMO)
    @Override
    public InsumoAlmacen findInsumo(@PathVariable("codInsumo") String codIns,
            @PathVariable("id") String codAlmacen) {
        return getUc().findInsumo(codIns, codAlmacen);
    }

    @PutMapping(RESET_ALMACEN)
    @Override
    public void resetAlmacen(@PathVariable("id") String codAlmacen) {
        getUc().resetAlmacen(codAlmacen);
    }

}
