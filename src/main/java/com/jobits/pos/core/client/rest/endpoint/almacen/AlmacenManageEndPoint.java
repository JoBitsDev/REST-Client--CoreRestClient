/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.core.domain.TransaccionSimple;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionEntrada;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionMerma;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionSalida;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTransformacion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTraspaso;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/almacen_manage")
public class AlmacenManageEndPoint extends CrudRestEndPointTemplate<Almacen,AlmacenManageService> 
        implements AlmacenManageService{

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

    @Override
    public AlmacenManageService getUc() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Operacion crearOperacion(Operacion.Tipo tipoOp, List<TransaccionSimple> transacciones, String recibo, Date fechaFactura, String codAlmacen, Integer codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Operacion> getOperacionesPendientes(String codAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejecutarOperacion(String codAlmacen, Operacion operacionToUpdate) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejecutarOperacion(String codAlmacen, int idOperacion) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarInsumoAlmacen(String codInsumo, String codAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimirReporteParaCompras(String codAlmacen, int selection) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimirResumenAlmacen(String codAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeInsumoFromStorage(InsumoAlmacen insumoAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean bulkImport(List<InsumoAlmacen> importList) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InsumoAlmacen findInsumo(String codIns, String codAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetAlmacen(String codAlmacen) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

   

}
