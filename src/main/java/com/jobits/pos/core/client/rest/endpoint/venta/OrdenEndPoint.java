/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.client.rest.assembler.MesaModelAssembler;
import com.jobits.pos.core.client.rest.assembler.OrdenModelAssembler;
import com.jobits.pos.core.client.rest.assembler.ProductoVentaModelAssembler;
import com.jobits.pos.core.client.rest.assembler.ProductovOrdenModelAssembler;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path = "/orden")
public class OrdenEndPoint extends CrudRestServiceTemplate<Orden> {

    public static final String ADD_PRODUCT_PATH = "/add_product";
    public static final RequestMethod ADD_PRODUCT_METHOD = RequestMethod.POST;

    public static final String ADD_PRODUCTO_COMPUESTO_PATH = "/add_producto_compuesto";
    public static final RequestMethod ADD_PRODUCTO_COMPUESTO_METHOD = RequestMethod.POST;

    public static final String ADD_PRODUCTO_IN_HOT_PATH = "/add_producto_in_hot";
    public static final RequestMethod ADD_PRODUCTO_IN_HOT_METHOD = RequestMethod.POST;

    public static final String REMOVE_PRODUCT_PATH = "/remove_producto";
    public static final RequestMethod REMOVE_PRODUCT_METHOD = RequestMethod.DELETE;

    public static final String ADD_NOTA_PATH = "/add_nota";
    public static final RequestMethod ADD_NOTA_METHOD = RequestMethod.POST;

    public static final String CAN_VIEW_ORDEN_LOG_PATH = "/can_view_orden_log";
    public static final RequestMethod CAN_VIEW_ORDEN_LOG_METHOD = RequestMethod.GET;

    public static final String VALIDATE_ADD_ORDEN_PATH = "/validate_add_orden";
    public static final RequestMethod VALIDATE_ADD_METHOD = RequestMethod.GET;

    public static final String SET_DE_LA_CASA_PATH = "/set_de_la_casa";
    public static final RequestMethod SET_DE_LA_CASA_METHOD = RequestMethod.PUT;

    public static final String SET_PORCIENTO_PATH = "/set_porciento";
    public static final RequestMethod SET_PORCIENTO_METHOD = RequestMethod.PUT;

    public static final String GET_VALOR_TOTAL_ORDEN_PATH = "/get_valor_total_orden";
    public static final RequestMethod GET_VALOR_TOTAL_METHOD = RequestMethod.GET;

    public static final String IMPRIMIR_PRE_TICKET_PATH = "/imprimir_pre_ticket";
    public static final RequestMethod IMPRIMIR_PRE_METHOD = RequestMethod.POST;

    public static final String IMPRIMIR_LISTA_ORDENES_PATH = "/imprimir_lista_ordenes";
    public static final RequestMethod IMPRIMIR_LISTA_METHOD = RequestMethod.POST;

    public static final String ENVIAR_COCINA_PATH = "/enviar_cocina";
    public static final RequestMethod ENVIAR_COCINA_METHOD = RequestMethod.PUT;

    public static final String CERRAR_ORDEN_PATH = "/cerrar_orden";
    public static final RequestMethod CERRAR_ORDEN_METHOD = RequestMethod.PUT;

    public static final String FIND_MESA_CAJA_PATH = "/find_mesa_caja";
    public static final RequestMethod FIND_MESA_CAJA_METHOD = RequestMethod.GET;

    public static final String GET_PDV_LIST_PATH = "/get_pdv_list";
    public static final RequestMethod GET_PDV_LIST_METHOD = RequestMethod.GET;

    public static final String GET_PRODUCTO_BY_SEECION_PATH = "/get_producto_by_seccion";
    public static final RequestMethod GET_PRODUCTO_BY_SEECION_METHOD = RequestMethod.GET;

    public static final String GET_LISTA_MESAS_DISPONIBLES_PATH = "/get_lista_mesas_disponibles";
    public static final RequestMethod GET_LISTA_MESAS_DISPONIBLES_METHOD = RequestMethod.GET;

    OrdenModelAssembler ordenAssembler = new OrdenModelAssembler();
    MesaModelAssembler mesaAssembler = new MesaModelAssembler();
    ProductoVentaModelAssembler productoVentaAssembler = new ProductoVentaModelAssembler();
    ProductovOrdenModelAssembler productovOrdenAssembler = new ProductovOrdenModelAssembler();

    @Override
    public OrdenService getUc() {
        return PosCoreModule.getInstance().getImplementation(OrdenService.class);
    }

    @Override
    public CrudModelAssembler<Orden> getAssembler() {
        return ordenAssembler;
    }

    @PostMapping(ADD_PRODUCT_PATH)
    EntityModel<ProductovOrden> addProduct(@RequestParam String codOrden, @RequestBody ProductoVenta producto_seleccionado, @RequestBody Float cantidad, @RequestBody ProductovOrden productoOrdenAgregar) {
        EntityModel<ProductovOrden> entityModel
                = productovOrdenAssembler.toModel(getUc().addProduct(codOrden, producto_seleccionado, cantidad, productoOrdenAgregar));
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).findMesaCaja()).withRel("add_product"));
        return entityModel;
    }

    @PostMapping(ADD_PRODUCTO_COMPUESTO_PATH)
    void addProductoCompuesto(@RequestParam String codOrden, @RequestBody ProductoVenta producto_agregar, @RequestBody Float cantidad, @RequestBody List<ProductoVentaWrapper> lista_agregos) {
        getUc().addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos);
    }

    @PostMapping(ADD_PRODUCTO_IN_HOT_PATH)
    void addProductInHot(@RequestParam String codOrden, @RequestParam String nombre, @RequestParam String precio, @RequestParam String cantidad) {
        getUc().addProductInHot(codOrden, nombre, precio, cantidad);
    }

    @DeleteMapping(REMOVE_PRODUCT_PATH)
    void removeProduct(@RequestParam String codOrden, @RequestBody ProductovOrden producto_orden_seleccionado, @RequestBody float cantidad) {
        getUc().removeProduct(codOrden, producto_orden_seleccionado, cantidad);
    }

    @PostMapping(ADD_NOTA_PATH)
    void addNota(@RequestParam String codOrden, @RequestBody ProductovOrden producto_orden_seleccionado, @RequestParam String nuevaNota) {
        getUc().addNota(codOrden, producto_orden_seleccionado, nuevaNota);
    }

    @GetMapping(CAN_VIEW_ORDEN_LOG_PATH)
    void canViewOrdenLog(@RequestBody Personal usuario, String codOrden) {
        getUc().canViewOrdenLog(usuario, codOrden);
    }

    @GetMapping(VALIDATE_ADD_ORDEN_PATH)
    boolean validateAddOrden() {
        return getUc().validateAddOrden();
    }

    @PutMapping(SET_DE_LA_CASA_PATH)
    void setDeLaCasa(@RequestParam String codOrden, @RequestBody boolean es_autorizo) {
        getUc().setDeLaCasa(codOrden, es_autorizo);
    }

    @PutMapping(SET_PORCIENTO_PATH)
    void setPorciento(@RequestParam String codOrden, @RequestBody float porciento_servicio) {
        getUc().setPorciento(codOrden, porciento_servicio);
    }

    @GetMapping(GET_VALOR_TOTAL_ORDEN_PATH)
    float getValorTotalOrden(@RequestParam String codOrden) {
        return getUc().getValorTotalOrden(codOrden);
    }

    @PostMapping(IMPRIMIR_PRE_TICKET_PATH)
    void imprimirPreTicket(@RequestParam String codOrden) {
        getUc().imprimirPreTicket(codOrden);
    }

    @PostMapping(IMPRIMIR_LISTA_ORDENES_PATH)
    void impimirListaOrdenes(@RequestBody List<Orden> list, @RequestParam int codVenta) {
        getUc().impimirListaOrdenes(list, codVenta);
    }

    @PutMapping(ENVIAR_COCINA_PATH)
    void enviarACocina(@RequestParam String codOrden) {
        getUc().enviarACocina(codOrden);
    }

    @PutMapping(CERRAR_ORDEN_PATH)
    EntityModel<Orden> cerrarOrden(@RequestParam String codOrden, @RequestBody boolean imprimirTicket) {
        EntityModel<Orden> entityModel = ordenAssembler.toModel(getUc().cerrarOrden(codOrden, imprimirTicket));
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).cerrarOrden(codOrden, imprimirTicket)).withRel("cerrar_orden"));
        return entityModel;
    }

    @GetMapping(FIND_MESA_CAJA_PATH)
    EntityModel<Mesa> findMesaCaja() {
        EntityModel<Mesa> entityModel = mesaAssembler.toModel(getUc().findMesaCaja());
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).findMesaCaja()).withRel("find_mesa_caja"));
        return entityModel;
    }

    @GetMapping(GET_PDV_LIST_PATH)
    CollectionModel<EntityModel<ProductoVenta>> getPDVList(@RequestParam String codOrden) {
        CollectionModel<EntityModel<ProductoVenta>> entityModel
                = productoVentaAssembler.toCollectionModel(getUc().getPDVList(codOrden));
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).getPDVList(codOrden)).withRel("get_producto_by_seccion"));
        return entityModel;
    }

    @GetMapping(GET_PRODUCTO_BY_SEECION_PATH)
    CollectionModel<EntityModel<ProductoVenta>> getProductoBySeccion(@RequestBody Seccion seccion) {
        CollectionModel<EntityModel<ProductoVenta>> entityModel
                = productoVentaAssembler.toCollectionModel(getUc().getProductoBySeccion(seccion));
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).getProductoBySeccion(seccion)).withRel("get_pdv_list"));
        return entityModel;
    }

    @GetMapping(GET_LISTA_MESAS_DISPONIBLES_PATH)
    CollectionModel<EntityModel<Mesa>> getListaMesasDisponibles() {
        CollectionModel<EntityModel<Mesa>> entityModel
                = mesaAssembler.toCollectionModel(getUc().getListaMesasDisponibles());
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).getListaMesasDisponibles()).withRel("get_lista_mesas_disponibles"));
        return entityModel;
    }
}
