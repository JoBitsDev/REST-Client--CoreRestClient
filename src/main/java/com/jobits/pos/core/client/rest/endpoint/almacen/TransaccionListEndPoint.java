/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.usecase.TransaccionListService;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos")
public class TransaccionListEndPoint extends CrudRestEndPointTemplate<Transaccion, TransaccionListService>
        implements TransaccionListService {

    public static final String FIND_ALL_BY_ALMACEN_PATH = "/almacen/{id}/list-transacciones";
    public static final String FIND_ALL_BY_ALMACEN_MERMA_PATH = "/almacen/{id}/list-transacciones-merma";
    public static final String PRINT_PATH = "/transacciones/print";


    @Override
    public TransaccionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(TransaccionListService.class);
    }

    @GetMapping(FIND_ALL_BY_ALMACEN_PATH)
    @Override
    public List<Transaccion> findAllByAlmacen(@PathVariable("id") String string) {
        return getUc().findAllByAlmacen(string);
    }

    @GetMapping(FIND_ALL_BY_ALMACEN_MERMA_PATH)
    @Override
    public List<Transaccion> findMermasByAlmacen(@PathVariable("id") String string) {
        return getUc().findMermasByAlmacen(string);
    }

    @PutMapping(PRINT_PATH)
    @Override
    public void imprimirTransaccionesSeleccionadas(@RequestBody List<Transaccion> list) {
        getUc().imprimirTransaccionesSeleccionadas(list);
    }

}
