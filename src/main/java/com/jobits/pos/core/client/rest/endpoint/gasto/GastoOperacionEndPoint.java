/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.gasto;

import com.jobits.pos.controller.gasto.GastoOperacionService;
import com.jobits.pos.core.client.rest.assembler.DefaultCostoModelAssembler;
import com.jobits.pos.core.client.rest.assembler.GastoOperacionModelAssembler;
import com.jobits.pos.core.client.rest.assembler.TipoGastoModelAssembler;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DefaultGasto;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.recursos.R;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/gasto_operacion")
public class GastoOperacionEndPoint extends CrudRestServiceTemplate<GastoVenta> {

    public static final String CREATE_GASTO_PATH = "/create_gasto";
    public static final RequestMethod CREATE_GASTO_METHOD = RequestMethod.POST;

    public static final String REMOVE_GASTO_PATH = "/remove_gasto";
    public static final RequestMethod DAR_ENTRADA_IPV_METHOD = RequestMethod.DELETE;

    public static final String GET_VALOR_TOTAL_GASTOS_PATH = "/dar_entrada_ipv";
    public static final RequestMethod GET_VALOR_TOTAL_GASTOS_METHOD = RequestMethod.GET;

    public static final String GET_NOMBRE_BY_TIPO_PATH = "/get_nombres_by_tipo";
    public static final RequestMethod GET_NOMBRES_BY_TIPO_METHOD = RequestMethod.GET;

    public static final String GET_DEFAULT_GASTOS_LIST_PATH = "/get_default_gastos_list";
    public static final RequestMethod GET_DEFAULT_GASTOS_LIST__METHOD = RequestMethod.GET;

    public static final String AGREGAR_DEFAULT_GASTO_PATH = "/agregar_default_gasto";
    public static final RequestMethod AGREGAR_DEFAULT_GASTO_METHOD = RequestMethod.POST;

    public static final String ELIMINAR_DEFAULT_GASTO_PATH = "/eliminar_default_gasto";
    public static final RequestMethod ELIMINAR_DEFAULT_GASTO_METHOD = RequestMethod.DELETE;

    GastoOperacionModelAssembler gastoOperacionAssembler = new GastoOperacionModelAssembler();
    DefaultCostoModelAssembler defaultCostoAssembler = new DefaultCostoModelAssembler();
    TipoGastoModelAssembler tipoGastoAssembler = new TipoGastoModelAssembler();

    @Override
    public GastoOperacionService getUc() {
        return PosCoreModule.getInstance().getImplementation(GastoOperacionService.class);
    }

    @Override
    public CrudModelAssembler<GastoVenta> getAssembler() {
        return gastoOperacionAssembler;
    }

    @PutMapping(CREATE_GASTO_PATH)
    public boolean createGasto(@RequestBody R.TipoGasto cat, @RequestParam String nombre, @RequestParam float monto, @RequestParam String descripcion, @RequestBody Venta venta) {
        getUc().createGasto(cat, nombre, monto, descripcion, venta);
        return true;
    }

    @PutMapping(REMOVE_GASTO_PATH)
    public boolean removeGasto(@RequestBody GastoVenta gastoVenta, @RequestBody Venta diaVenta) {
        getUc().removeGasto(gastoVenta, diaVenta);
        return true;
    }

    @GetMapping(GET_VALOR_TOTAL_GASTOS_PATH)
    public float getValorTotalGastos(@RequestBody Venta diaVenta) {
        return getUc().getValorTotalGastos(diaVenta);
    }

    @GetMapping(GET_NOMBRE_BY_TIPO_PATH)
    public CollectionModel<EntityModel<String>> getNombresByTipo(@RequestParam String toString) {
        CollectionModel<EntityModel<String>> entityModel
                = tipoGastoAssembler.toCollectionModel(getUc().getNombresByTipo(toString));
        entityModel.add(linkTo(methodOn(GastoOperacionEndPoint.class).getNombresByTipo(toString)).withRel("get_nombres_by_tipo"));
        return entityModel;
    }

    @GetMapping(GET_DEFAULT_GASTOS_LIST_PATH)
    public CollectionModel<EntityModel<DefaultGasto>> getDefaultGastosList() {
        CollectionModel<EntityModel<DefaultGasto>> entityModel
                = defaultCostoAssembler.toCollectionModel(getUc().getDefaultGastosList());
        entityModel.add(linkTo(methodOn(GastoOperacionEndPoint.class).getDefaultGastosList()).withRel("get_default_gastos_list"));
        return entityModel;

    }

    @PutMapping(AGREGAR_DEFAULT_GASTO_PATH)
    public EntityModel<DefaultGasto> agregarDefaultGasto(@RequestParam String alias, @RequestBody R.TipoGasto cat, @RequestParam String nombre, @RequestParam float monto, @RequestParam String descripcion) {
        EntityModel<DefaultGasto> entityModel
                = defaultCostoAssembler.toModel(getUc().agregarDefaultGasto(alias, cat, nombre, monto, descripcion));
        entityModel.add(linkTo(methodOn(GastoOperacionEndPoint.class).getDefaultGastosList()).withRel("agregar_default_gasto"));
        return entityModel;
    }

    @PutMapping(ELIMINAR_DEFAULT_GASTO_PATH)
    public boolean eliminarDefaultGasto(@RequestBody DefaultGasto gasto) {
        getUc().eliminarDefaultGasto(gasto);
        return true;
    }
}
