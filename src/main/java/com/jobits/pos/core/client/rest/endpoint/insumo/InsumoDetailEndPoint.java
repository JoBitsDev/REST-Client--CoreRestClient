/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.insumo;

import com.jobits.pos.controller.insumo.InsumoDetailService;
import com.jobits.pos.core.client.rest.assembler.InsumoModelAssembler;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.InsumoElaborado;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path = "/insumo_detail")
public class InsumoDetailEndPoint extends CrudRestServiceTemplate<Insumo> {

    public static final String BULK_IMPORT_INSUMO_PATH = "/bulk_import_insumo";
    public static final RequestMethod BULK_IMPORT_INSUMO_METHOD = RequestMethod.POST;

    public static final String AGREGAR_INSUMO_ELABORADO_A_INSUMO_PATH = "/agregar_insumo_elaborado_a_insumo";
    public static final RequestMethod AGREGAR_INSUMO_ELABORADO_A_INSUMO_METHOD = RequestMethod.POST;

    public static final String ELIMINAR_INSUMO_ELABORADO_DE_INSUMO_PATH = "/eliminar_insumo_elaborado_de_insumo";
    public static final RequestMethod ELIMINAR_INSUMO_ELABORADO_DE_INSUMO_METHOD = RequestMethod.DELETE;

    public static final String AGREGAR_PRODUCTO_VENTA_A_INSUMO_PATH = "/agregar_producto_venta_a_insumo";
    public static final RequestMethod AGREGAR_PRODUCTO_VENTA_A_INSUMO_METHOD = RequestMethod.POST;

    public static final String ELIMINAR_PRODUCTO_VENTA_DE_INSUMO_PATH = "/eliminar_producto_venta_de_insumo";
    public static final RequestMethod ELIMINAR_PRODUCTO_VENTA_DE_INSUMO_METHOD = RequestMethod.DELETE;

    public static final String UPDATE_PRODUCTO_ON_INSUMO_PATH = "/update_producto_on_insumo";
    public static final RequestMethod UPDATE_PRODUCTO_ON_INSUMO_METHOD = RequestMethod.DELETE;

    InsumoModelAssembler insumoAssembler = new InsumoModelAssembler();

    @Override
    public InsumoDetailService getUc() {
        return PosCoreModule.getInstance().getImplementation(InsumoDetailService.class);
    }

    @Override
    public CrudModelAssembler<Insumo> getAssembler() {
        return insumoAssembler;
    }

    @PostMapping(BULK_IMPORT_INSUMO_PATH)
    public boolean bulkImport(@RequestBody List<Insumo> listaParaImportar) {
        getUc().bulkImport(listaParaImportar);
        return true;
    }

    @PostMapping(AGREGAR_INSUMO_ELABORADO_A_INSUMO_PATH)
    public boolean agregarInsumoElaboradoaInsumo(@RequestBody Insumo insumo, @RequestBody Insumo insumo_disponible_seleccionado, @RequestParam float cantidad) {
        getUc().agregarInsumoElaboradoaInsumo(insumo, insumo_disponible_seleccionado, cantidad);
        return true;
    }

    @DeleteMapping(ELIMINAR_INSUMO_ELABORADO_DE_INSUMO_PATH)
    public boolean eliminarInsumoElaboradoDeInsumo(@RequestBody Insumo insumo, @RequestBody InsumoElaborado insumo_contenido_seleccionado) {
        getUc().eliminarInsumoElaboradoDeInsumo(insumo, insumo_contenido_seleccionado);
        return true;
    }

    @PostMapping(AGREGAR_PRODUCTO_VENTA_A_INSUMO_PATH)
    public boolean agregarProductoVentaAInsumo(@RequestBody Insumo insumo, @RequestBody ProductoVenta producto_disponible_seleccionado, @RequestParam float cantidad) {
        getUc().agregarProductoVentaAInsumo(insumo, producto_disponible_seleccionado, cantidad);
        return true;
    }

    @DeleteMapping(ELIMINAR_PRODUCTO_VENTA_DE_INSUMO_PATH)
    public boolean eliminarProductoVentaDeInsumo(@RequestBody Insumo insumo, @RequestBody ProductoInsumo producto_contenido_seleccionado) {
        getUc().eliminarProductoVentaDeInsumo(insumo, producto_contenido_seleccionado);
        return true;
    }

    @PostMapping(UPDATE_PRODUCTO_ON_INSUMO_PATH)
    public boolean updateProductoOnInsumo(@RequestBody Insumo insumo) {
        getUc().updateProductoOnInsumo(insumo);
        return true;
    }

}
