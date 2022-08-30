/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/venta-list")
public class VentaListEndPoint extends CrudRestEndPointTemplate<Venta, VentaCalendarResumeUseCase>
        implements VentaCalendarResumeUseCase {

    public static final String RESOLVE_VENTA_ABIERTA = "/venta-abierta";
    public static final String FIND_VENTAS_BY_MONTH = "/find-by/month/{anno}/{mes}";
    public static final String FIND_VENTAS_IN_RANGE = "/find-by/range/{start}/{end}";
    public static final String IS_Y_VISIBLE = "/is-y-visible";
    public static final String FIND_VENTAS_BY_MONTH_VIEW = "/find-by/month-view/{anno}/{mes}";
    public static final String GET_RESUMEN_VENTAS_ESTAIDISTICAS = "/resumen-por-ids";

    @GetMapping(RESOLVE_VENTA_ABIERTA)
    @Override
    public Venta resolveVentaAbierta() {
        return getUc().resolveVentaAbierta();
    }

    @GetMapping(FIND_VENTAS_BY_MONTH)
    @Override
    public List<DayReviewWrapper<Venta>> findVentasByMonth(
            @PathVariable("mes") int month,
            @PathVariable("anno") int year) {
        return getUc().findVentasByMonth(month, year);
    }

    @GetMapping(FIND_VENTAS_IN_RANGE)
    @Override
    public List<Venta> findVentasInRange(@PathVariable("start") LocalDate start,
                                         @PathVariable("end") LocalDate end) {
        return getUc().findVentasInRange(start, end);
    }

    @GetMapping(IS_Y_VISIBLE)
    @Override
    public boolean isYVisible() {
        return getUc().isYVisible();
    }

    @GetMapping(FIND_VENTAS_BY_MONTH_VIEW)
    @Override
    public List<DayReviewWrapper<ResumenVentaEstadisticas>>
    findVentasByMonthView(@PathVariable("mes") int month,
                          @PathVariable("anno") int year) {
        return getUc().findVentasByMonthView(month, year);
    }

    @PutMapping(GET_RESUMEN_VENTAS_ESTAIDISTICAS)
    @Override
    public List<ResumenVentaEstadisticas> getResumenDeVentasEstadisticas(
            @RequestBody List<Integer> idVentas) {
        return getUc().getResumenDeVentasEstadisticas(idVentas);
    }

    @Override
    public Venta deleteVenta(int idVenta) {
        return getUc().deleteVenta(idVenta);
    }

    @Override
    public Venta destroyById(@PathVariable("id") Object o) throws RuntimeException {
        return getUc().deleteVenta(Integer.valueOf(o.toString()));
    }

    @Override
    public VentaCalendarResumeUseCase getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaCalendarResumeUseCase.class);
    }

}
