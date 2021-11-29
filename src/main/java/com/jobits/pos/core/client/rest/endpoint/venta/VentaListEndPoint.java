/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.VentaListService;
import com.jobits.pos.core.client.rest.persistence.models.DetallesVentasModel;
import com.jobits.pos.core.client.rest.persistence.models.OrdenConverter;
import com.jobits.pos.core.client.rest.persistence.models.OrdenModel;
import com.jobits.pos.core.client.rest.persistence.models.VentaCalculator;
import com.jobits.pos.core.client.rest.persistence.models.VentaResumenController;
import com.jobits.pos.core.client.rest.persistence.models.VentaResumenModel;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/venta-list")
public class VentaListEndPoint extends CrudRestEndPointTemplate<Venta, VentaListService> {

    public static final String FIND_VENTAS_BY_MONTH_PATH = "/find_ventas_by_month";
    public static final RequestMethod FIND_VENTAS_BY_MONTH_METHOD = RequestMethod.GET;

    public static final String FIND_VENTAS_IN_RANGE_PATH = "/find_ventas_in_range";
    public static final RequestMethod FIND_VENTAS_IN_RANGE_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_VENTAS_PATH = "/get_total_ventas";
    public static final RequestMethod GET_TOTAL_VENTAS_METHOD = RequestMethod.GET;

    public static final String GET_FECHA_VENTAS_PATH = "/get_fecha_ventas";
    public static final RequestMethod GET_FECHA_VENTAS_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_GASTOS_PATH = "/get_total_gastos";
    public static final RequestMethod GET_TOTAL_GASTOS_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_ORDEN_PATH = "/get_total_orden";
    public static final RequestMethod GET_TOTAL_ORDEN_METHOD = RequestMethod.GET;

    public static final String IS_Y_VISIBLE_PATH = "/is_y_visible";
    public static final RequestMethod IS_Y_VISIBLE_METHOD = RequestMethod.GET;

    public static final String RESUMEN = "/{id}/resumen-general";
    public static final RequestMethod RESUMEN_METHOD = RequestMethod.GET;

    public static final String GET_IDS_VENTAS = "/ids/{aaaa}/{mm}/{dd}";
    public static final RequestMethod GET_IDS_VENTAS_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_DETALLADO = "/{id}/resumen-detallado";
    public static final RequestMethod GET_RESUMEN_DETALLADO_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_AREA = "/{id}/detalles-area/{idArea}";
    public static final RequestMethod GET_RESUMEN_AREA_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_DPTE = "/{id}/detalles-mesero/{idUsuario}";
    public static final RequestMethod GET_RESUMEN_DPTE_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_PTO_ELAB = "/{id}/detalles-punto-elaboracion/{idPuntoElab}";
    public static final RequestMethod GET_RESUMEN_PTO_ELAB_METHOD = RequestMethod.GET;

    public static final String CREATE_ORDEN = "/get-venta-abierta/create-orden/{codMesa}";
    public static final RequestMethod CREATE_ORDEN_METHOD = RequestMethod.POST;

    @Override
    public VentaListService getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaListService.class);
    }

    @GetMapping(FIND_VENTAS_BY_MONTH_PATH)
    CollectionModel<EntityModel<DayReviewWrapper<Venta>>> findVentasByMonth(@RequestParam int month, @RequestParam int year) {
        return null;
    }

    @GetMapping(RESUMEN)
    ResponseEntity<VentaResumenModel> getResumenGeneralFrom(@PathVariable("id") int idVentas) {
        Venta v = getUc().findBy(idVentas);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(VentaResumenController.createResumenFromVenta(v));
    }

    @GetMapping(GET_RESUMEN_AREA)
    ResponseEntity<List<DetallesVentasModel>> getResumenFromArea(
            @PathVariable("id") int idVentas,
            @PathVariable("idArea") String idArea) {
        Venta v = getUc().findBy(idVentas);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        AreaVentaService areaService = PosCoreModule.getInstance().getImplementation(AreaVentaService.class);
        return ResponseEntity.ok().body(DetallesVentasModel.createDetallesVentaFromEntity(
                VentaCalculator.getResumenVentaPorArea(
                        getUc().findBy(idVentas),
                        areaService.findBy(idArea))));
    }

    @GetMapping(GET_RESUMEN_PTO_ELAB)
    ResponseEntity<List<DetallesVentasModel>> getResumenFromPuntoElab(
            @PathVariable("id") int idVentas,
            @PathVariable("idPuntoElab") String idPuntoElab) {
        Venta v = getUc().findBy(idVentas);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        PuntoElaboracionService ptoService = PosCoreModule.getInstance().getImplementation(PuntoElaboracionService.class);
        return ResponseEntity.ok().body(DetallesVentasModel.createDetallesVentaFromEntity(
                VentaCalculator.getResumenVentasCocina(
                        getUc().findBy(idVentas),
                        ptoService.findBy(idPuntoElab))));
    }

    @GetMapping(GET_RESUMEN_DPTE)
    ResponseEntity<List<DetallesVentasModel>> getResumenFromDpte(
            @PathVariable("id") int idVentas,
            @PathVariable("idUsuario") String idUsuario) {
        Venta v = getUc().findBy(idVentas);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        PersonalUseCase personalService = PosCoreModule.getInstance().getImplementation(PersonalUseCase.class);
        return ResponseEntity.ok().body(DetallesVentasModel.createDetallesVentaFromEntity(
                VentaCalculator.getResumenVentasCamarero(
                        getUc().findBy(idVentas),
                        personalService.findBy(idUsuario))));
    }

    @GetMapping(GET_RESUMEN_DETALLADO)
    ResponseEntity<List<DetallesVentasModel>> getResumenDetallado(
            @PathVariable("id") int idVentas) {
        Venta v = getUc().findBy(idVentas);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(DetallesVentasModel.createDetallesVentaFromEntity(
                VentaCalculator.getResumenVentas(
                        getUc().findBy(idVentas))));
    }

    @GetMapping(GET_IDS_VENTAS)
    ResponseEntity<List<Integer>> getVentasIdsOf(
            @PathVariable("aaaa") int anno,
            @PathVariable("mm") int mes,
            @PathVariable("dd") int dia) {
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Calendar.YEAR, anno);
        startEnd.set(Calendar.MONTH, mes);
        startEnd.set(Calendar.DAY_OF_MONTH, dia);
        List<Venta> v = getUc().findVentasInRange(startEnd, startEnd);
        if (v.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<Integer> ret = new ArrayList<>();
        for (Venta venta : v) {
            ret.add(venta.getId());
        }
        return ResponseEntity.ok().body(ret);
    }

    @PostMapping(CREATE_ORDEN)
    ResponseEntity<OrdenModel> createOrden(
            @PathVariable("codMesa") String codMesa) {
        VentaDetailService ser = PosCoreModule.getInstance().getImplementation(VentaDetailService.class);
        MesaService mSer = PosCoreModule.getInstance().getImplementation(MesaService.class);
        Orden ret = ser.createNewOrden(getUc().resolveVentaAbierta().getId(), mSer.findBy(codMesa));
        return ResponseEntity.ok(new OrdenConverter().apply(ret));
    }

}
