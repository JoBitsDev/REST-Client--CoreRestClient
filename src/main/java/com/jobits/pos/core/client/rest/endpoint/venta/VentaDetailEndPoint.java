/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.client.rest.assembler.OrdenModelAssembler;
import com.jobits.pos.core.client.rest.assembler.ProductovOrdenModelAssembler;
import com.jobits.pos.core.client.rest.assembler.VentaModelAssembler;
import com.jobits.pos.core.client.rest.assembler.models.BooleanModelAssembler;
import com.jobits.pos.core.client.rest.assembler.models.FloatModelAssembler;
import com.jobits.pos.core.client.rest.assembler.models.StringModelAssembler;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/venta_detail")
public class VentaDetailEndPoint extends CrudRestServiceTemplate<Venta> {

    public static final String IMPORTAR_VENTA_PATH = "/importar_venta";
    public static final RequestMethod IMPORTAR_VENTA_METHOD = RequestMethod.POST;

    public static final String PRINT_GASTOS_CASA_PATH = "/print_gastos_casa";
    public static final RequestMethod PRINT_GASTOS_CASA_METHOD = RequestMethod.POST;

    public static final String PRINT_PAGO_POR_VENTA_PERSONAL_PATH = "/print_pago_por_venta";
    public static final RequestMethod PRINT_PAGO_POR_VENTA_PERSONAL_METHOD = RequestMethod.POST;

    public static final String PRINT_COMISION_PORCENTUAL_RESUMEN_PATH = "/print_comision_porcentual";
    public static final RequestMethod PRINT_COMISION_PORCENTUAL_RESUMEN_METHOD = RequestMethod.POST;

    public static final String PRINT_Z_PATH = "/print_z";
    public static final RequestMethod PRINT_Z_METHOD = RequestMethod.POST;

    public static final String REABRIR_VENTAS_PATH = "/reabrir_ventas";
    public static final RequestMethod REABRIR_VENTAS_METHOD = RequestMethod.PUT;

    public static final String TERMINAR_VENTAS_PATH = "/terminar_ventas";
    public static final RequestMethod TERMINAR_VENTAS_METHOD = RequestMethod.PUT;

    public static final String TERMINAR_Y_EXPORTAR_PATH = "/terminar-y_exportar";
    public static final RequestMethod ERMINAR_Y_EXPORTAR_METHOD = RequestMethod.PUT;

    public static final String PRINT_MESA_RESUMEN_PATH = "/print_mesa_resumen";
    public static final RequestMethod PRINT_MESA_RESUMEN_METHOD = RequestMethod.POST;

    public static final String PRINT_AREA_RESUMEN_PATH = "/print_area_resumen";
    public static final RequestMethod PRINT_AREA_RESUMEN_METHOD = RequestMethod.POST;

    public static final String PRINT_PERSONAL_RESUMEN_PATH = "/print_personal_resumen";
    public static final RequestMethod PRINT_PERSONAL_RESUMEN_METHOD = RequestMethod.POST;

    public static final String PRINT_COCINA_RESUMEN_PATH = "/print_cocina_resumen";
    public static final RequestMethod PRINT_COCINA_RESUMEN_METHOD = RequestMethod.POST;

    public static final String GET_VENTAS_DE_FECHA_PATH = "/get_ventas_de_fecha";
    public static final RequestMethod GET_VENTAS_DE_FECHA_METHOD = RequestMethod.GET;

    public static final String CAMBIO_TURNO_PATH = "/cambio_turno";
    public static final RequestMethod CAMBIO_TURNO_METHOD = RequestMethod.POST;

    public static final String CAN_OPEN_NUEVO_TURNO_PATH = "/can_open_nuevo_turno";
    public static final RequestMethod CAN_OPEN_NUEVO_TURNO_METHOD = RequestMethod.GET;

    public static final String CAN_REABRIR_VENTA_PATH = "/can_reabrir_venta";
    public static final RequestMethod CAN_REABRIR_VENTA_METHOD = RequestMethod.GET;

    public static final String CREATE_NEW_ORDEN_PATH = "/create_new_orden";
    public static final RequestMethod CREATE_NEW_ORDEN_METHOD = RequestMethod.POST;

    public static final String GET_ORDENES_ACTIVAS_PATH = "/get_ordenes_activas";
    public static final RequestMethod GET_ORDENES_ACTIVAS_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_AUTORIZOS_PATH = "/get_total_autorizos";
    public static final RequestMethod GET_TOTAL_AUTORIZOS_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_GASTADO_INSUMOS_PATH = "/get_total_gastado_insumos";
    public static final RequestMethod GET_TOTAL_GASTADO_INSUMOS_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_GASTOS_PATH = "/get_total_gastos";
    public static final RequestMethod GET_TOTAL_GASTOS_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_PAGO_TRABAJADORES_PATH = "/get_total_pago_trabajadores";
    public static final RequestMethod GET_TOTAL_PAGO_TRABAJADORES_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_PROPINA_PATH = "/get_total_propina";
    public static final RequestMethod GET_TOTAL_PROPINA_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_VENDIDO_PATH = "/get_total_vendido";
    public static final RequestMethod GET_TOTAL_VENDIDO_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_VENDIDO_NETO_PATH = "/get_total_vendido_neto";
    public static final RequestMethod GET_TOTAL_VENDIDO_NETO_METHOD = RequestMethod.GET;

    public static final String GET_MESA_POR_VENTA_PATH = "/get_mesa_por_venta";
    public static final RequestMethod GET_MESA_POR_VENTA_METHOD = RequestMethod.GET;

    public static final String GET_PERSONAL_POR_VENTA_PATH = "/get_personal_por_venta";
    public static final RequestMethod GET_PERSONAL_POR_VENTA_METHOD = RequestMethod.GET;

    public static final String GET_COCINAS_POR_VENTA_PATH = "/get_cocinas_por_venta";
    public static final RequestMethod GET_COCINAS_POR_VENTA_METHOD = RequestMethod.GET;

    public static final String GET_AREAS_POR_VENTA_PATH = "/get_areas_por_venta";
    public static final RequestMethod GET_AREAS_POR_VENTA_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_POR_MESA_PATH = "/get_resumen_por_mesa";
    public static final RequestMethod GET_RESUMEN_POR_MESA_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_POR_PERSONAL_PATH = "/get_resumen_por_personal";
    public static final RequestMethod GET_RESUMEN_POR_PERSONAL_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_POR_COCINA_PATH = "/get_resumen_por_cocina";
    public static final RequestMethod GET_RESUMEN_POR_COCINA_METHOD = RequestMethod.GET;

    public static final String GET_RESUMEN_POR_AREA_PATH = "/get_resumen_por_area";
    public static final RequestMethod GET_RESUMEN_POR_AREA_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_RESUMEN_MESA_PATH = "/get_total_resumen_mesa";
    public static final RequestMethod GET_TOTAL_RESUMEN_MESA_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_RESUMEN_DEPENDIENTE_PATH = "/get_total_resumen_dependiente";
    public static final RequestMethod GET_TOTAL_RESUMEN_DEPENDIENTE_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_RESUMEN_COCINA_PATH = "/get_total_resumen_cocina";
    public static final RequestMethod GET_TOTAL_RESUMEN_COCINA_METHOD = RequestMethod.GET;

    public static final String GET_TOTAL_RESUMEN_AREA_PATH = "/get_total_resumen_area";
    public static final RequestMethod GET_TOTAL_RESUMEN_AREA_METHOD = RequestMethod.GET;

    VentaModelAssembler ventaAssembler = new VentaModelAssembler();
    OrdenModelAssembler ordenAssembler = new OrdenModelAssembler();
    ProductovOrdenModelAssembler productoVordenAssembler = new ProductovOrdenModelAssembler();
    BooleanModelAssembler booleanAssembler = new BooleanModelAssembler();
    FloatModelAssembler floatAssembler = new FloatModelAssembler();
    StringModelAssembler stringAssembler = new StringModelAssembler();

    @Override
    public VentaDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(VentaDetailService.class);
    }

    @Override
    public CrudModelAssembler<Venta> getAssembler() {
        return ventaAssembler;
    }

    @PostMapping(IMPORTAR_VENTA_PATH)
    void importarVenta(@RequestBody File file) {
        getUc().importarVenta(file);
    }

    @PostMapping(PRINT_GASTOS_CASA_PATH)
    void printGastosCasa(@RequestParam int codVenta) {
        getUc().printGastosCasa(codVenta);
    }

    @PostMapping(PRINT_PAGO_POR_VENTA_PERSONAL_PATH)
    void printPagoPorVentaPersonal(@RequestBody Personal user, @RequestParam int codVenta, @RequestBody boolean printValores) {
        getUc().printPagoPorVentaPersonal(user, codVenta, printValores);
    }

    @PostMapping(PRINT_COMISION_PORCENTUAL_RESUMEN_PATH)
    void printComisionPorcentualResumen(@RequestBody Mesa mesa, @RequestBody Venta venta) {
        getUc().printComisionPorcentualResumen(mesa, venta.getId());
    }

    @PostMapping(PRINT_Z_PATH)
    void printZ(@RequestParam int codVenta) {
        getUc().printZ(codVenta);
    }

    @PutMapping(REABRIR_VENTAS_PATH)
    void reabrirVentas(@RequestParam int codVenta) {
        getUc().reabrirVentas(codVenta);
    }

    @PutMapping(TERMINAR_VENTAS_PATH)
    void terminarVentas(@RequestParam int codVenta) {
        getUc().terminarVentas(codVenta);
    }

    @PutMapping(TERMINAR_Y_EXPORTAR_PATH)
    void terminarYExportar(@RequestBody File file, @RequestParam int codVenta) {
        getUc().terminarYExportar(file, codVenta);
    }

    @PostMapping(PRINT_MESA_RESUMEN_PATH)
    void printMesaResumen(@RequestBody Mesa mesa, @RequestBody Venta venta) {
        getUc().printMesaResumen(mesa, venta.getId());
    }

    @PostMapping(PRINT_AREA_RESUMEN_PATH)
    void printAreaResumen(@RequestBody Area a, @RequestParam int codVenta) {
        getUc().printAreaResumen(a, codVenta);
    }

    @PostMapping(PRINT_PERSONAL_RESUMEN_PATH)
    void printPersonalResumenRow(@RequestBody Personal p, @RequestParam int codVenta, @RequestBody boolean printValores) {
        getUc().printPersonalResumenRow(p, codVenta, printValores);
    }

    @PostMapping(PRINT_COCINA_RESUMEN_PATH)
    void printCocinaResumen(@RequestBody String codCocina, @RequestParam int codVenta, @RequestBody boolean printValores) {
        getUc().printCocinaResumen(codCocina, codVenta, printValores);
    }

    @GetMapping(GET_VENTAS_DE_FECHA_PATH)
    CollectionModel<EntityModel<Venta>> getVentasDeFecha(@RequestBody Date date) {
        CollectionModel<EntityModel<Venta>> entityModel
                = ventaAssembler.toCollectionModel(getUc().getVentasDeFecha(date));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getVentasDeFecha(date)).withRel("get_ventas_de_fecha"));
        return entityModel;
    }

    @PostMapping(CAMBIO_TURNO_PATH)
    EntityModel<Venta> cambiarTurno(@RequestBody Venta fecha, @RequestBody Personal user) {
        EntityModel<Venta> entityModel = ventaAssembler.toModel(getUc().cambiarTurno(fecha.getId(), user));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).cambiarTurno(fecha, user)).withRel("cambiar_turno"));
        return entityModel;
    }

    @GetMapping(CAN_OPEN_NUEVO_TURNO_PATH)
    EntityModel<Boolean> canOpenNuevoTurno(@RequestBody Date fecha) {
        EntityModel<Boolean> entityModel = booleanAssembler.toModel(getUc().canOpenNuevoTurno(fecha));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).canOpenNuevoTurno(fecha)).withRel("can_open_nuevo_turno"));
        return entityModel;
    }

    @GetMapping(CAN_REABRIR_VENTA_PATH)
    EntityModel<Boolean> canReabrirVenta(@RequestParam int codVenta) {
        EntityModel<Boolean> entityModel = booleanAssembler.toModel(getUc().canReabrirVenta(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).canReabrirVenta(codVenta)).withRel("can_reabrir_venta"));
        return entityModel;
    }

    @PostMapping(CREATE_NEW_ORDEN_PATH)
    EntityModel<Orden> createNewOrden(@RequestParam int codVenta, @RequestBody Mesa mesa) {
        EntityModel<Orden> entityModel = ordenAssembler.toModel(getUc().createNewOrden(codVenta, mesa));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).createNewOrden(codVenta, mesa)).withRel("create_new_orden"));
        return entityModel;
    }

    @GetMapping(GET_ORDENES_ACTIVAS_PATH)
    CollectionModel<EntityModel<Orden>> getOrdenesActivas(@RequestParam int codVenta) {
        CollectionModel<EntityModel<Orden>> entityModel
                = ordenAssembler.toCollectionModel(getUc().getOrdenesActivas(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getOrdenesActivas(codVenta)).withRel("get_ordenes_activas"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_AUTORIZOS_PATH)
    EntityModel<String> getTotalAutorizos(@RequestParam int codVenta) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalAutorizos(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalAutorizos(codVenta)).withRel("get_total_autorizos"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_GASTADO_INSUMOS_PATH)
    EntityModel<String> getTotalGastadoInsumos(@RequestParam int codVenta) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalGastadoInsumos(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalGastadoInsumos(codVenta)).withRel("get_total_gastado_insumos"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_GASTOS_PATH)
    EntityModel<String> getTotalGastos(@RequestParam int codVenta) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalGastos(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalGastos(codVenta)).withRel("get_total_gastos"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_PAGO_TRABAJADORES_PATH)
    EntityModel<String> getTotalPagoTrabajadores(@RequestParam int codVenta) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalPagoTrabajadores(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalPagoTrabajadores(codVenta)).withRel("get_total_pago_trabajadores"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_PROPINA_PATH)
    EntityModel<Float> getTotalPropina(int codVenta) {
        EntityModel<Float> entityModel = floatAssembler.toModel(getUc().getTotalPropina(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalPropina(codVenta)).withRel("get_total_propina"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_VENDIDO_PATH)
    EntityModel<String> getTotalVendido(@RequestParam int codVenta) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalVendido(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalVendido(codVenta)).withRel("get_total_vendido"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_VENDIDO_NETO_PATH)
    EntityModel<String> getTotalVendidoNeto(@RequestParam int codVenta) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalVendidoNeto(codVenta));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalVendidoNeto(codVenta)).withRel("get_total_vendido_neto"));
        return entityModel;
    }

    @GetMapping(GET_MESA_POR_VENTA_PATH)
    CollectionModel<EntityModel<Mesa>> getMesasPorVenta(@RequestParam int codVenta) {
        throw new UnsupportedOperationException();
    }

    @GetMapping(GET_PERSONAL_POR_VENTA_PATH)
    CollectionModel<EntityModel<Personal>> getPersonalPorVenta(@RequestParam int codVenta) {
        throw new UnsupportedOperationException();
    }

    @GetMapping(GET_COCINAS_POR_VENTA_PATH)
    List<Cocina> getCocinasPorVenta(@RequestParam int codVenta) {
        return getUc().getCocinasPorVenta(codVenta);
    }

    @GetMapping(GET_AREAS_POR_VENTA_PATH)
    CollectionModel<EntityModel<Area>> getAreasPorVenta(@RequestParam int codVenta) {
        throw new UnsupportedOperationException();
    }

    @GetMapping(GET_RESUMEN_POR_MESA_PATH)
    CollectionModel<EntityModel<ProductovOrden>> getResumenPorMesa(@RequestParam int codVenta, @RequestBody Mesa mesa) {
        CollectionModel<EntityModel<ProductovOrden>> entityModel
                = productoVordenAssembler.toCollectionModel(getUc().getResumenPorMesa(codVenta, mesa));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getResumenPorMesa(codVenta, mesa)).withRel("get_resumen_por_mesa"));
        return entityModel;
    }

    @GetMapping(GET_RESUMEN_POR_PERSONAL_PATH)
    CollectionModel<EntityModel<ProductovOrden>> getResumenPorPersonal(int codVenta, @RequestBody Personal personal) {
        CollectionModel<EntityModel<ProductovOrden>> entityModel
                = productoVordenAssembler.toCollectionModel(getUc().getResumenPorPersonal(codVenta, personal));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getResumenPorPersonal(codVenta, personal)).withRel("get_resumen_por_personal"));
        return entityModel;
    }

    @GetMapping(GET_RESUMEN_POR_COCINA_PATH)
    CollectionModel<EntityModel<ProductovOrden>> getResumenPorCocina(@RequestParam int codVenta, @RequestBody Cocina cocina) {
        CollectionModel<EntityModel<ProductovOrden>> entityModel
                = productoVordenAssembler.toCollectionModel(getUc().getResumenPorCocina(codVenta, cocina));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getResumenPorCocina(codVenta, cocina)).withRel("get_resumen_por_personal"));
        return entityModel;
    }

    @GetMapping(GET_RESUMEN_POR_AREA_PATH)
    CollectionModel<EntityModel<ProductovOrden>> getResumenPorArea(int codVenta, @RequestBody Area area) {
        CollectionModel<EntityModel<ProductovOrden>> entityModel
                = productoVordenAssembler.toCollectionModel(getUc().getResumenPorArea(codVenta, area));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getResumenPorArea(codVenta, area)).withRel("get_resumen_por_personal"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_RESUMEN_MESA_PATH)
    EntityModel<String> getTotalResumenMesa(@RequestParam int codVenta, @RequestBody Mesa mesa) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalResumenMesa(codVenta, mesa));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalResumenMesa(codVenta, mesa)).withRel("get_total_resumen_mesa"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_RESUMEN_DEPENDIENTE_PATH)
    EntityModel<String> getTotalResumenDependiente(@RequestParam int codVenta, @RequestBody Personal personal) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalResumenDependiente(codVenta, personal));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalResumenDependiente(codVenta, personal)).withRel("get_total_resumen_dependiente"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_RESUMEN_COCINA_PATH)
    EntityModel<String> getTotalResumenCocina(@RequestParam int codVenta, @RequestBody Cocina cocina) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalResumenCocina(codVenta, cocina));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalResumenCocina(codVenta, cocina)).withRel("get_total_resumen_cocina"));
        return entityModel;
    }

    @GetMapping(GET_TOTAL_RESUMEN_AREA_PATH)
    EntityModel<String> getTotalResumenArea(@RequestParam int codVenta, @RequestBody Area area) {
        EntityModel<String> entityModel = stringAssembler.toModel(getUc().getTotalResumenArea(codVenta, area));
        entityModel.add(linkTo(methodOn(VentaDetailEndPoint.class).getTotalResumenArea(codVenta, area)).withRel("get_total_resumen_area"));
        return entityModel;
    }

}
