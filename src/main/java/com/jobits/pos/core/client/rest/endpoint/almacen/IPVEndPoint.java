/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
import com.jobits.pos.core.client.rest.persistence.models.IpvRegistroModel;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.inventario.core.almacen.domain.Ipv;
import com.jobits.pos.inventario.core.almacen.domain.IpvPK;
import com.jobits.pos.inventario.core.almacen.domain.IpvRegistro;
import com.jobits.pos.inventario.core.almacen.domain.IpvVentaRegistro;
import com.jobits.pos.inventario.core.almacen.usecase.IPVService;
import java.util.List;
import java.util.stream.Collectors;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/ipv")
public class IPVEndPoint
        extends CrudRestEndPointTemplate<Ipv, IPVService>
        implements IPVService {

    public static final String DAR_ENTRADA_EXISTENCIA_INSUMO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/entrada/{cantidad}";

    public static final String DAR_ENTRADA_IPV_PATH = "/productos/{idVenta}/{codProducto}/entrada/{cantidad}";

    public static final String AJUSTAR_CONSUMO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/ajustar-consumo/{cantidad}";

    public static final String AJUSTAR_COSTO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/ajustar-costo/{cantidad}";

    public static final String TRANSFERIR_IPV_REGISTRO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/transferir-ipv/{codCocinaTransferir}/{cantidad}";

    public static final String TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/transferir-almacen/{codAlmacen}/{cantidad}";

    public static final String REINICIAR_IPV_PATH = "/reiniciar/{idVenta}/{codCocina}";

    public static final String RECALCULAR_IPV_INSUMOS_PATH = "/insumos/{id_venta}/recalcular";

    public static final String RECALCULAR_IPV_VENTAS_PATH = "/productos/{id_venta}/recalcular";

    public static final String HAY_DISPONIBILIDAD_PATH = "/productos/hay-disponibilidad/{id_venta}/{codProducto}/{cantidad}";

    public static final String GET_IPV_REGISTRO_LIST_PATH = "/insumos/{idVenta}/{codCocina}/list";

    public static final String GET_IPV_REGISTRO_VENTA_LIST_PATH = "/productos/{idVenta}/{codCocina}/list";

    public static final String INICIALIZAR_EXISTENCIAS_PATH = "/insumos/{idVenta}/inicializar";

    public static final String INICIALIZAR_IPVS_PATH = "/productos/{idVenta}/inicializar";

    public static final String REGISTRAR_IPVS_PATH = "/insumos/{idVenta}/{codCocina}/registrar/{codInsumo}";

    public static final String DELETE_BY_ID = "/delete/{codCocina}/{codInsumo}";

    @Override
    public IPVService getUc() {
        return PosCoreModule.getInstance().getImplementation(IPVService.class);
    }

    @PutMapping(DAR_ENTRADA_EXISTENCIA_INSUMO_PATH)
    @Override
    public IpvRegistro darEntradaExistencia(@PathVariable("codInsumo") String codInsumo,
            @PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int idVenta,
            @PathVariable("cantidad") float cantidad) {
        return getUc().darEntradaExistencia(codInsumo, codCocina, idVenta, cantidad);
    }

    @PutMapping(DAR_ENTRADA_IPV_PATH)
    @Override
    public IpvVentaRegistro darEntradaIPV(@PathVariable("codProducto") String codProductoVenta,
            @PathVariable("idVenta") int codVenta,
            @PathVariable("cantidad") float cantidad) {
        return getUc().darEntradaIPV(codProductoVenta, codVenta, cantidad);
    }

    @PutMapping(AJUSTAR_CONSUMO_PATH)
    @Override
    public IpvRegistro ajustarConsumo(@PathVariable("codInsumo") String codInsumo,
            @PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int codVenta,
            @PathVariable("cantidad") float cantidad) {
        return getUc().ajustarConsumo(codInsumo, codCocina, codVenta, cantidad);
    }

    @PutMapping(AJUSTAR_COSTO_PATH)
    @Override
    public IpvRegistro ajustarCosto(@PathVariable("codInsumo") String codInsumo,
            @PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int codVenta,
            @PathVariable("cantidad") float cantidad) {
        return getUc().ajustarCosto(codInsumo, codCocina, codVenta, cantidad);
    }

    @PutMapping(TRANSFERIR_IPV_REGISTRO_PATH)
    @Override
    public IpvRegistro transferirIPVRegistro(@PathVariable("codInsumo") String codInsumo,
            @PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int codVenta,
            @PathVariable("codCocinaTransferir") String codCocinaTransferir,
            @PathVariable("cantidad") float cantidad) {
        return getUc().transferirIPVRegistro(codInsumo, codCocina, codVenta, codCocinaTransferir, cantidad);
    }

    @PutMapping(TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_PATH)
    @Override
    public IpvRegistro transferirIPVRegistroToAlmacen(@PathVariable("codInsumo") String codInsumo,
            @PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int codVenta,
            @PathVariable("codAlmacen") String codAlmacenDestino,
            @PathVariable("cantidad") float cantidad) {
        return getUc().transferirIPVRegistroToAlmacen(codInsumo, codCocina, codVenta, codAlmacenDestino, cantidad);
    }

    @PutMapping(REINICIAR_IPV_PATH)
    @Override
    public boolean reiniciarIPV(@PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int codVenta) {
        return getUc().reiniciarIPV(codCocina, codVenta);
    }

    @PutMapping(RECALCULAR_IPV_INSUMOS_PATH)
    @Override
    public boolean recalcularExistencias(@PathVariable("idVenta") int idVenta) {
        return getUc().recalcularExistencias(idVenta);
    }

    @PutMapping(RECALCULAR_IPV_VENTAS_PATH)
    @Override
    public boolean recalcularIpvRegistros(@PathVariable("idVenta") int idVenta) {
        return getUc().recalcularIpvRegistros(idVenta);
    }

    @GetMapping(HAY_DISPONIBILIDAD_PATH)
    @Override
    public boolean hayDisponibilidad(@PathVariable("codProducto") String codProducto,
            @PathVariable("idVenta") int idVenta,
            @PathVariable("cantidad") float cantidad) {
        return getUc().hayDisponibilidad(codProducto, idVenta, cantidad);
    }

    @GetMapping(GET_IPV_REGISTRO_LIST_PATH)
    @Override
    public List<IpvRegistro> getIpvRegistroList(@PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int idVenta) {
        return getUc().getIpvRegistroList(codCocina, idVenta);
    }

    @GetMapping(GET_IPV_REGISTRO_VENTA_LIST_PATH)
    @Override
    public List<IpvVentaRegistro> getIpvRegistroVentaList(@PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int idVenta) {
        return getUc().getIpvRegistroVentaList(codCocina, idVenta);
    }

    @PutMapping(INICIALIZAR_EXISTENCIAS_PATH)
    @Override
    public List<IpvRegistro> inicializarExistencias(@PathVariable("idVenta") int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @PutMapping(INICIALIZAR_IPVS_PATH)
    @Override
    public List<IpvVentaRegistro> inicializarIpvs(@PathVariable("idVenta") int idVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @PutMapping(REGISTRAR_IPVS_PATH)
    @Override
    public IpvRegistro registrarIPV(@PathVariable("codInsumo") String codInsumo,
            @PathVariable("codCocina") String codCocina,
            @PathVariable("idVenta") int idVenta) {
        return getUc().registrarIPV(codInsumo, codCocina, idVenta);
    }

    @DeleteMapping(DELETE_BY_ID)
    public Ipv deleteAux(
            @PathVariable("codCocina") String codCocina,
            @PathVariable("codInsumo") String codInsumo) {
        return getUc().destroyById(new IpvPK(codInsumo, codCocina));
    }

    //
    // Compatibilidad
    //
    @GetMapping(value = {"/ipv-registro-list/{cod_cocina}", "/ipv-registro-list/{cod_cocina}/{id_venta}"})
    public synchronized ResponseEntity<List<IpvRegistroModel>> getIpvRegistroList(
            @PathVariable("cod_cocina") String codCocina,
            @PathVariable(value = "cod_venta", required = false) Integer codVenta) {
        PuntoElaboracionService puntoElaboracionListService = PosCoreModule.getInstance().getImplementation(PuntoElaboracionService.class);
        var pto = puntoElaboracionListService.findBy(codCocina);
        if (codVenta == null) {
            codVenta = PosCoreModule.getInstance().getImplementation(VentaCalendarResumeUseCase.class).resolveVentaAbierta().getId();
        }
        var list = getUc().getIpvRegistroList(pto.getCodCocina(), codVenta);
        List<IpvRegistroModel> ret = list.stream().map(i -> IpvRegistroModel.from(i)).collect(Collectors.toList());
        ret = ret.stream().filter((t) -> {
            return t.getDisponible() != 0;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(ret);
    }

    @GetMapping(value = {"/ipv-venta-list/{cod_cocina}", "/ipv-venta-list/{cod_cocina}/{id_venta}"})
    public synchronized ResponseEntity<List<IpvRegistroModel>> getIpvRegistroVentaList(
            @PathVariable("cod_cocina") String codCocina,
            @PathVariable(value = "cod_venta", required = false) Integer codVenta) {
        PuntoElaboracionService puntoElaboracionListService = PosCoreModule.getInstance().getImplementation(PuntoElaboracionService.class);
        var pto = puntoElaboracionListService.findBy(codCocina);
        if (codVenta == null) {
            codVenta = PosCoreModule.getInstance().getImplementation(VentaCalendarResumeUseCase.class).resolveVentaAbierta().getId();
        }
        var list = getUc().getIpvRegistroVentaList(pto.getCodCocina(), codVenta);
        List<IpvRegistroModel> ret = list.stream().map(i -> IpvRegistroModel.from(i)).collect(Collectors.toList());
        ret = ret.stream().filter((t) -> {
            return t.getDisponible() != 0;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(ret);
    }
}
