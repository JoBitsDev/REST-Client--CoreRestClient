/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.core.client.rest.assembler.AlmacenModelAssembler;
import com.jobits.pos.core.client.rest.assembler.InsumoAlmacenModelAssembler;
import com.jobits.pos.core.client.rest.assembler.InsumoModelAssembler;
import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.core.domain.models.TransaccionEntrada;
import com.jobits.pos.core.domain.models.TransaccionMerma;
import com.jobits.pos.core.domain.models.TransaccionSalida;
import com.jobits.pos.core.domain.models.TransaccionTransformacion;
import com.jobits.pos.core.domain.models.TransaccionTraspaso;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
@RequestMapping(path = "/almacen_manage")
public class AlmacenManageEndPoint extends CrudRestServiceTemplate<Almacen> {

    public static final String INSUMO_ALMACEN_LIST_PATH = "/insumo_almacen_list";
    public static final RequestMethod INSUMO_ALMACEN_LIST_METHOD = RequestMethod.GET;

    public static final String FIND_INSUMO_PATH = "/find_insumo";
    public static final RequestMethod FIND_INSUMO_METHOD = RequestMethod.GET;

    public static final String BULK_INSUMO_PATH = "/bulk_insumo";
    public static final RequestMethod BULK_INSUMO_METHOD = RequestMethod.POST;

    public static final String DAR_ENTRADA_IPV_PATH = "/dar_entrada_ipv";
    public static final RequestMethod DAR_ENTRADA_IPV__METHOD = RequestMethod.POST;

    public static final String UPDATE_VALOR_TOTAL_ALMACEN_PATH = "/update_valor_total_almacen";
    public static final RequestMethod UPDATE_VALOR_TOTAL_ALMACEN_METHOD = RequestMethod.PUT;

    public static final String REMOVE_INSUMO_FROM_STRORAGE_PATH = "/remove_insumo_from_storage";
    public static final RequestMethod REMOVE_INSUMO_FROM_STRORAGE_METHOD = RequestMethod.PUT;

    public static final String IMPRIMIR_RESUMEN_ALMACEN_PATH = "/imprimir_resumen_almacen";
    public static final RequestMethod IMPRIMIR_RESUMEN_ALMACEN_METHOD = RequestMethod.POST;

    public static final String IMPRIMIR_RESUMEN_COMPRAS_PATH = "/imprimir_resumen_compras";
    public static final RequestMethod IMPRIMIR_RESUMEN_COMPRAS_METHOD = RequestMethod.POST;

    public static final String SET_CENTRO_ELABORACION_PATH = "/set_centro_elaboracion";
    public static final RequestMethod SET_CENTRO_ELABORACION_METHOD = RequestMethod.PUT;

    public static final String CREAR_TRANSFORMACION_PATH = "/crear_transformacion";
    public static final RequestMethod CREAR_TRANSFORMACION_METHOD = RequestMethod.POST;

    public static final String AGREGAR_INSUMO_ALMACEN_PATH = "/agregar_insumo_almacen";
    public static final RequestMethod AGREGAR_INSUMO_ALMACEN_METHOD = RequestMethod.PUT;

    public static final String DAR_TRASPASO_A_INSUMO_PATH = "/dar_traspaso_a_insumo";
    public static final RequestMethod DAR_TRASPASO_A_INSUMO_METHOD = RequestMethod.PUT;

    public static final String DAR_TRANSFORMACION_A_INSUMO_PATH = "/dar_transformacion_a_insumo";
    public static final RequestMethod DAR_TRANSFORMACION_A_INSUMO_METHOD = RequestMethod.PUT;

    public static final String DAR_SALIDA_A_INSUMO_PATH = "/dar_salida_a_insumo";
    public static final RequestMethod DAR_SALIDA_A_INSUMO_METHOD = RequestMethod.PUT;

    public static final String DAR_MERMA_A_INSUMO_PATH = "/dar_merma_a_insumo";
    public static final RequestMethod DAR_MERMA_A_INSUMO_METHOD = RequestMethod.PUT;

    public static final String DAR_ENTRADA_A_INSUMO_PATH = "/dar_entrada_a_insumo";
    public static final RequestMethod DAR_ENTRADA__A_INSUMO_METHOD = RequestMethod.PUT;

    public static final String CREAR_OPERACION_ENTRADA_PATH = "/crear_operacion_entrada";
    public static final RequestMethod CREAR_OPERACION_ENTRADA_METHOD = RequestMethod.POST;

    public static final String CREAR_OPERACION_REBAJA_PATH = "/crear_operacion_rebaja";
    public static final RequestMethod CREAR_OPERACION_REBAJA_METHOD = RequestMethod.POST;

    public static final String CREAR_OPERACION_SALIDA_PATH = "/crear_operacion_salida";
    public static final RequestMethod CREAR_OPERACION_SALIDA_METHOD = RequestMethod.POST;

    public static final String CREAR_OPERACION_TRASPASO_PATH = "/crear_operacion_traspaso";
    public static final RequestMethod CREAR_OPERACION_TRASPASO_METHOD = RequestMethod.POST;

    AlmacenModelAssembler almacenAssembler = new AlmacenModelAssembler();
    InsumoModelAssembler insumoAssembler = new InsumoModelAssembler();
    InsumoAlmacenModelAssembler insumoAlmacenAssembler = new InsumoAlmacenModelAssembler();

    @Override
    public AlmacenManageService getUc() {
        return PosCoreModule.getInstance().getImplementation(AlmacenManageService.class);
    }

    @Override
    public CrudModelAssembler<Almacen> getAssembler() {
        return almacenAssembler;
    }

    @PostMapping(CREAR_OPERACION_ENTRADA_PATH)
    public boolean crearOperacionEntrada(@RequestBody ArrayList<TransaccionSimple> al,
            @RequestParam String string, @RequestParam Date date, @RequestBody Almacen almcn) {
        getUc().crearOperacionEntrada(al, string, date, almcn);
        return true;
    }

    @PostMapping(CREAR_OPERACION_REBAJA_PATH)
    public boolean crearOperacionRebaja(@RequestBody ArrayList<TransaccionSimple> al,
            @RequestParam String string, @RequestParam Date date, @RequestBody Almacen almcn) {
        getUc().crearOperacionRebaja(al, string, date, almcn);
        return true;
    }

    @PostMapping(CREAR_OPERACION_SALIDA_PATH)
    public boolean crearOperacionSalida(@RequestBody ArrayList<TransaccionSimple> al,
            @RequestParam String string, @RequestParam Date date,
            @RequestParam Integer intgr, @RequestParam Almacen almcn) {
        getUc().crearOperacionSalida(al, string, date, intgr, almcn);
        return true;
    }

    @PostMapping(CREAR_OPERACION_TRASPASO_PATH)
    public boolean crearOperacionTraspaso(@RequestBody ArrayList<TransaccionSimple> al,
            @RequestParam String string, @RequestBody Date date, @RequestBody Almacen almcn) {
        getUc().crearOperacionTraspaso(al, string, date, almcn);
        return true;
    }

    @PutMapping(DAR_ENTRADA_A_INSUMO_PATH)
    public boolean darEntradaAInsumo(@RequestBody TransaccionEntrada te, @RequestBody Almacen almcn) {
        getUc().darEntradaAInsumo(te, almcn);
        return true;
    }

    @PutMapping(DAR_MERMA_A_INSUMO_PATH)
    public boolean darMermaInsumo(@RequestBody TransaccionMerma tm, @RequestBody Almacen almcn) {
        getUc().darMermaInsumo(tm, almcn);
        return true;
    }

    @PutMapping(DAR_SALIDA_A_INSUMO_PATH)
    public boolean darSalidaAInsumo(@RequestBody TransaccionSalida ts,
            @RequestParam int i, @RequestBody Almacen almcn) {
        getUc().darSalidaAInsumo(ts, i, almcn);
        return true;
    }

    @PutMapping(DAR_TRANSFORMACION_A_INSUMO_PATH)
    public boolean darTransformacionAInsumo(@RequestBody Transaccion trnscn,
            @RequestBody Almacen almcn, @RequestBody Almacen almcn1) {
        getUc().darTransformacionAInsumo(trnscn, almcn, almcn1);
        return true;
    }

    @PutMapping(DAR_TRASPASO_A_INSUMO_PATH)
    public boolean darTraspasoInsumo(@RequestBody TransaccionTraspaso tt,
            @RequestBody Almacen almcn) {
        getUc().darTraspasoInsumo(tt, almcn);
        return true;
    }

    @PutMapping(AGREGAR_INSUMO_ALMACEN_PATH)
    public boolean agregarInsumoAlmacen(@RequestBody Insumo insumo,
            @RequestBody Almacen almcn) {
        getUc().agregarInsumoAlmacen(insumo, almcn);
        return true;
    }

    @PostMapping(CREAR_TRANSFORMACION_PATH)
    public boolean crearTransformacion(@RequestBody InsumoAlmacen ia,
            @RequestParam float f, @RequestBody List<TransaccionTransformacion> list,
            @RequestBody Almacen almcn) {
      //  getUc().crearTransformacion(ia, f, list, almcn);
        return true;
    }

    @PutMapping(SET_CENTRO_ELABORACION_PATH)
    public boolean setCentroElaboracion(@RequestParam boolean bln, @RequestBody Almacen almcn) {
        getUc().setCentroElaboracion(bln, almcn);
        return true;
    }

    @PostMapping(IMPRIMIR_RESUMEN_COMPRAS_PATH)
    public boolean imprimirReporteParaCompras(@RequestBody Almacen almcn, @RequestParam int i) {
        getUc().imprimirReporteParaCompras(almcn, i);
        return true;
    }

    @PostMapping(IMPRIMIR_RESUMEN_ALMACEN_PATH)
    public boolean imprimirResumenAlmacen(@RequestBody Almacen almcn) {
        getUc().imprimirResumenAlmacen(almcn);
        return true;
    }

    @PutMapping(REMOVE_INSUMO_FROM_STRORAGE_PATH)
    public boolean removeInsumoFromStorage(@RequestBody InsumoAlmacen ia, @RequestBody Almacen almcn) {
        getUc().removeInsumoFromStorage(ia, almcn);
        return true;
    }

    @PutMapping(UPDATE_VALOR_TOTAL_ALMACEN_PATH)
    public boolean updateValorTotalAlmacen(@RequestBody Almacen almcn) {
        getUc().updateValorTotalAlmacen(almcn);
        return true;
    }

    @PostMapping(DAR_ENTRADA_IPV_PATH)
    public boolean darEntradaIPV(@RequestBody Almacen almcn, @RequestBody Insumo insumo, @RequestParam float f) {
        getUc().darEntradaIPV(almcn, insumo, f);
        return true;
    }

    @GetMapping(BULK_INSUMO_PATH)
    public boolean bulkImport(@RequestBody List<InsumoAlmacen> list) {
        return getUc().bulkImport(list);
    }

    @GetMapping(INSUMO_ALMACEN_LIST_PATH)
    public CollectionModel<EntityModel<InsumoAlmacen>> getInsumoAlmacenList(@RequestBody Almacen almcn) {
        CollectionModel<EntityModel<InsumoAlmacen>> entityModel
                = insumoAlmacenAssembler.toCollectionModel(getUc().getInsumoAlmacenList(almcn));
        entityModel.add(linkTo(methodOn(AlmacenManageEndPoint.class).getInsumoAlmacenList(almcn)).withRel("insumo_almacen_list"));
        return entityModel;
    }

    @GetMapping(FIND_INSUMO_PATH)
    public EntityModel<InsumoAlmacen> findInsumo(@RequestBody Insumo insumo, @RequestBody Almacen almcn) {
        EntityModel<InsumoAlmacen> entityModel = insumoAlmacenAssembler.toModel(getUc().findInsumo(insumo, almcn));
        entityModel.add(linkTo(methodOn(AlmacenManageEndPoint.class).findInsumo(insumo, almcn)).withRel("find_insumo"));
        return entityModel;
    }
}
