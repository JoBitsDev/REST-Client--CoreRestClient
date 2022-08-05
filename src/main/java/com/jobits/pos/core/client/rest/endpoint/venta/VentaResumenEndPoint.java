/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.resumen.ResumenFacadeInterface;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.escandallos.InsumoRegistro;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaWrapper;
import com.jobits.pos.core.module.PosCoreModule;
import java.time.LocalDate;
import org.jobits.pos.client.rest.endpoint.DefaultEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/venta/resumen/{desde}/{hasta}")
public class VentaResumenEndPoint extends DefaultEndpoint implements ResumenFacadeInterface {

    public static final String AUTORIZO_RESUMEN = "/autorizo";
    public static final String COSTO_RESUMEN = "/costo";
    public static final String GASTO_RESUMEN = "/gasto";
    public static final String SALARIO_RESUMEN = "/salario";
    public static final String VENTAS_RESUMEN = "/ventas";

    private ResumenFacadeInterface i = PosCoreModule.getInstance().getImplementation(ResumenFacadeInterface.class);

    @GetMapping(AUTORIZO_RESUMEN)
    @Override
    public ResumenVentaWrapper<DayReviewWrapper<ProductovOrden>, ProductovOrden> getAutorizoResumen(
            @PathVariable("desde") LocalDate desde,
            @PathVariable("hasta") LocalDate hasta) {
        return i.getAutorizoResumen(desde, hasta);
    }

    @GetMapping(COSTO_RESUMEN)
    @Override
    public ResumenVentaWrapper<DayReviewWrapper<InsumoRegistro>, InsumoRegistro> getCostoResumen(
            @PathVariable("desde") LocalDate desde,
            @PathVariable("hasta") LocalDate hasta) {
        return i.getCostoResumen(desde, hasta);
    }

    @GetMapping(GASTO_RESUMEN)
    @Override
    public ResumenVentaWrapper<DayReviewWrapper<GastoVenta>, GastoVenta> getGastoResumen(
            @PathVariable("desde") LocalDate desde,
            @PathVariable("hasta") LocalDate hasta) {
        return i.getGastoResumen(desde, hasta);
    }

    @GetMapping(SALARIO_RESUMEN)
    @Override
    public ResumenVentaWrapper<DayReviewWrapper<AsistenciaPersonal>, AsistenciaPersonal> getSalarioResumen(
            @PathVariable("desde") LocalDate desde,
            @PathVariable("hasta") LocalDate hasta) {
        return i.getSalarioResumen(desde, hasta);
    }

    @GetMapping(VENTAS_RESUMEN)
    @Override
    public ResumenVentaWrapper<DayReviewWrapper<ProductovOrden>, ProductovOrden> getVentaResumen(
            @PathVariable("desde") LocalDate desde,
            @PathVariable("hasta") LocalDate hasta) {
        return i.getVentaResumen(desde, hasta);
    }

}
