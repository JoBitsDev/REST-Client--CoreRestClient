/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.gasto;

import com.jobits.pos.controller.gasto.GastoOperacionService;
import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.temporal.DefaultGasto;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.recursos.R;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/gastos")
public class GastoOperacionEndPoint extends CrudRestEndPointTemplate<GastoVenta, GastoOperacionService> implements GastoOperacionService {


    public static final String CREATE_GASTO_PATH = "/create-gasto/{idVenta}/{tipo}/{nombre}/{monto}/{desc}";
    public static final String REMOVE_GASTO_PATH = "/remove-gasto/{idVenta}/{codGasto}";
    public static final String GET_VALOR_TOTAL_GASTOS_PATH = "/{idVenta}/get-valor-total-gastos";
    public static final String GET_NOMBRE_BY_TIPO_PATH = "/nombres-por-tipo/{tipo}";
    public static final String GET_DEFAULT_GASTOS_LIST_PATH = "/default-gastos-list";
    public static final String AGREGAR_DEFAULT_GASTO_PATH = "/agregar-gasto/{categoria}/{alias}/{nombre}/{monto}/{desc}";
    public static final String ELIMINAR_DEFAULT_GASTO_PATH = "/eliminar-gasto";

    @Override
    public GastoOperacionService getUc() {
        return PosCoreModule.getInstance().getImplementation(GastoOperacionService.class);
    }

    @Override
    @PostMapping(CREATE_GASTO_PATH)
    public GastoVenta createGasto(@PathVariable("tipo") R.TipoGasto tipoGasto,
                                  @PathVariable("nombre") String nombre,
                                  @PathVariable("monto") float monto,
                                  @PathVariable(value = "desc", required = false) String desc,
                                  @PathVariable("idVenta") int idVenta) {
        return getUc().createGasto(tipoGasto, nombre, monto, desc, idVenta);
    }

    @Override
    @DeleteMapping(REMOVE_GASTO_PATH)
    public GastoVenta removeGasto(@PathVariable("codGasto") String codGasto, @PathVariable("idVenta") int idVenta) {
        return getUc().removeGasto(codGasto, idVenta);
    }

    @Override
    @GetMapping(GET_VALOR_TOTAL_GASTOS_PATH)
    public float getValorTotalGastos(@PathVariable("idVenta") int idVenta) {
        return getUc().getValorTotalGastos(idVenta);
    }


    @Override
    @GetMapping(GET_NOMBRE_BY_TIPO_PATH)
    public List<String> getNombresByTipo(@PathVariable("tipo") String tipo) {
        return getUc().getNombresByTipo(tipo);
    }


    @Override
    @GetMapping(GET_DEFAULT_GASTOS_LIST_PATH)
    public List<DefaultGasto> getDefaultGastosList() {
        return getUc().getDefaultGastosList();
    }

    @Override
    @PostMapping(AGREGAR_DEFAULT_GASTO_PATH)
    public DefaultGasto agregarDefaultGasto(@PathVariable("alias") String alias, @PathVariable("categoria") R.TipoGasto cat, @PathVariable("nombre") String nombre, @PathVariable("monto") float monto, @PathVariable("desc") String descripcion) {
        return getUc().agregarDefaultGasto(alias, cat, nombre, monto, descripcion);
    }


    @Override
    @PutMapping(ELIMINAR_DEFAULT_GASTO_PATH)
    public DefaultGasto eliminarDefaultGasto(@RequestBody DefaultGasto defaultGasto) {
        return getUc().eliminarDefaultGasto(defaultGasto);
    }

}
