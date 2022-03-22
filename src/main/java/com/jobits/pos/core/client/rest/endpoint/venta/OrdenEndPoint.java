/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.mesa.MesaService;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.client.rest.assembler.MesaModelAssembler;
import com.jobits.pos.core.client.rest.assembler.OrdenModelAssembler;
import com.jobits.pos.core.client.rest.assembler.ProductoVentaModelAssembler;
import com.jobits.pos.core.client.rest.assembler.ProductovOrdenModelAssembler;
import com.jobits.pos.core.client.rest.persistence.models.OrdenConverter;
import com.jobits.pos.core.client.rest.persistence.models.OrdenModel;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.core.repo.impl.OrdenDAO;
import com.jobits.pos.core.repo.impl.ProductovOrdenDAO;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/orden")
public class OrdenEndPoint extends CrudRestServiceTemplate<Orden> {

    public static final String ADD_PRODUCT_PATH = "/{id}/add-product/{idProducto}/{cantidad}";
    public static final RequestMethod ADD_PRODUCT_METHOD = RequestMethod.POST;

    public static final String ADD_PRODUCTO_COMPUESTO_PATH = "/add_producto_compuesto";
    public static final RequestMethod ADD_PRODUCTO_COMPUESTO_METHOD = RequestMethod.POST;

    public static final String ADD_PRODUCTO_IN_HOT_PATH = "/add_producto_in_hot";
    public static final RequestMethod ADD_PRODUCTO_IN_HOT_METHOD = RequestMethod.POST;

    public static final String REMOVE_PRODUCT_PATH = "/{id}/remove-product/{idProducto}/{cantidad}";
    public static final RequestMethod REMOVE_PRODUCT_METHOD = RequestMethod.DELETE;

    public static final String ADD_NOTA_PATH = "{id}/add-nota/{idProductoOrden}/{nota}";
    public static final RequestMethod ADD_NOTA_METHOD = RequestMethod.PUT;

    public static final String GET_NOTA_PATH = "{id}/get-nota-from/{idProductoOrden}";
    public static final RequestMethod GET_NOTA_PATH_METHOD = RequestMethod.GET;

    public static final String CAN_VIEW_ORDEN_LOG_PATH = "/can_view_orden_log";
    public static final RequestMethod CAN_VIEW_ORDEN_LOG_METHOD = RequestMethod.GET;

    public static final String VALIDATE_ADD_ORDEN_PATH = "/validate_add_orden";
    public static final RequestMethod VALIDATE_ADD_METHOD = RequestMethod.GET;

    public static final String SET_DE_LA_CASA_PATH = "{id}/set-autorizo/{boolValue}";
    public static final RequestMethod SET_DE_LA_CASA_METHOD = RequestMethod.PUT;

    public static final String SET_PORCIENTO_PATH = "/set_porciento";
    public static final RequestMethod SET_PORCIENTO_METHOD = RequestMethod.PUT;

    public static final String GET_VALOR_TOTAL_ORDEN_PATH = "/get_valor_total_orden";
    public static final RequestMethod GET_VALOR_TOTAL_METHOD = RequestMethod.GET;

    public static final String IMPRIMIR_PRE_TICKET_PATH = "/imprimir_pre_ticket";
    public static final RequestMethod IMPRIMIR_PRE_METHOD = RequestMethod.POST;

    public static final String IMPRIMIR_LISTA_ORDENES_PATH = "/imprimir_lista_ordenes";
    public static final RequestMethod IMPRIMIR_LISTA_METHOD = RequestMethod.POST;

    public static final String ENVIAR_COCINA_PATH = "/{id}/enviar-a-cocina";
    public static final RequestMethod ENVIAR_COCINA_METHOD = RequestMethod.PUT;

    public static final String CERRAR_ORDEN_PATH = "/{id}/cerrar-orden";
    public static final RequestMethod CERRAR_ORDEN_METHOD = RequestMethod.PUT;

    public static final String FIND_MESA_CAJA_PATH = "/find_mesa_caja";
    public static final RequestMethod FIND_MESA_CAJA_METHOD = RequestMethod.GET;

    public static final String GET_PDV_LIST_PATH = "/get_pdv_list";
    public static final RequestMethod GET_PDV_LIST_METHOD = RequestMethod.GET;

    public static final String GET_PRODUCTO_BY_SEECION_PATH = "/get_producto_by_seccion";
    public static final RequestMethod GET_PRODUCTO_BY_SEECION_METHOD = RequestMethod.GET;

    public static final String GET_LISTA_MESAS_DISPONIBLES_PATH = "/get_lista_mesas_disponibles";
    public static final RequestMethod GET_LISTA_MESAS_DISPONIBLES_METHOD = RequestMethod.GET;

    public static final String VALIDATE_ORDEN_PATH = "/{id}/validate";
    public static final RequestMethod VALIDATE_ORDEN_METHOD = RequestMethod.GET;

    public static final String MOVER_A_PATH = "/{id}/mover-a/{codMesa}";
    public static final RequestMethod MOVER_A_METHOD = RequestMethod.PUT;

    public static final String NOTA_PATH = "/{id}/nota-from/{codProducto}";
    public static final RequestMethod NOTA_METHOD = RequestMethod.GET;

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

    @GetMapping("{id}")
    synchronized public ResponseEntity<OrdenModel> findSimple(@PathVariable("id") String codOrden) {
        getUc().findAll();//TODO: pifia metida aqui para refrescar la instancia
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().findBy(codOrden)));
    }

    @PostMapping(ADD_PRODUCT_PATH)
    synchronized ResponseEntity<OrdenModel> addProduct(@PathVariable("id") String codOrden, @PathVariable("idProducto") String producto_seleccionado, @PathVariable("cantidad") Float cantidad) {
        ProductoVentaListService productoService = PosCoreModule.getInstance().getImplementation(ProductoVentaListService.class);
        getUc().addProduct(codOrden, productoService.findBy(producto_seleccionado), cantidad, null);
        Orden o = getUc().findBy(codOrden);
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @PostMapping(ADD_PRODUCTO_COMPUESTO_PATH)
    synchronized void addProductoCompuesto(@RequestParam String codOrden, @RequestBody ProductoVenta producto_agregar, @RequestBody Float cantidad, @RequestBody List<ProductoVentaWrapper> lista_agregos) {
        getUc().addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos);
    }

    @PostMapping(ADD_PRODUCTO_IN_HOT_PATH)
    synchronized void addProductInHot(@RequestParam String codOrden, @RequestParam String nombre, @RequestParam String precio, @RequestParam String cantidad) {
        getUc().addProductInHot(codOrden, nombre, precio, cantidad);
    }

    @DeleteMapping(REMOVE_PRODUCT_PATH)
    synchronized ResponseEntity<OrdenModel> removeProduct(@PathVariable("id") String codOrden, @PathVariable("idProducto") int producto_orden_seleccionado, @PathVariable("cantidad") Float cantidad) {
        var orden = getUc().findBy(codOrden);
        ProductovOrden prod = null;
        for (ProductovOrden p : orden.getProductovOrdenList()) {
            if (p.getId() == producto_orden_seleccionado) {
                prod = p;
                break;
            }
        }
        getUc().removeProduct(codOrden, prod, cantidad);
        Orden o = getUc().findBy(codOrden);
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @GetMapping(VALIDATE_ORDEN_PATH)
    synchronized ResponseEntity<OrdenModel> validateOrden(@PathVariable("id") String codOrden) {
        Orden o = getUc().findBy(codOrden);
        MesaService mService = PosCoreModule.getInstance().getImplementation(MesaService.class);
        if (o != null) {
//            if (o.getHoraTerminada() != null) {
//                Mesa m = o.getMesacodMesa();
//                m.setEstado("vacia");
//                getUc().cerrarOrden(o.getCodOrden(),false);
//                return ResponseEntity.status(HttpStatus.GONE).body(null);
//                throw new ResponseStatusException(HttpStatus.GONE, "La mesa ya no se encuentra abierta");
//            } else {
            return ResponseEntity.ok(new OrdenConverter().apply(o));
//            }
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "La orden se elimino de manera inesperada");
        }
    }

    @PutMapping(MOVER_A_PATH)
    synchronized ResponseEntity<OrdenModel> moverA(@PathVariable("id") String codOrden, @PathVariable("codMesa") String codMesa) {
        Orden o = getUc().findBy(codOrden);
        MesaService mService = PosCoreModule.getInstance().getImplementation(MesaService.class);
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().moverA(codOrden, codMesa)));
    }
//
//     @PutMapping(MOVER_A_PATH)
//    ResponseEntity<OrdenModel> moverA(@PathVariable("id") String codOrden,@PathVariable("codMesa") String codMesa) {
//        Orden o = getUc().findBy(codOrden);
//        MesaService mService = PosCoreModule.getInstance().getImplementation(MesaService.class);
//        getUc().moverA(codOrden, codMesa);
//        if (o != null) {
//            if (o.getHoraTerminada() != null) {
//                Mesa m = o.getMesacodMesa();
//                m.setEstado("Vacia");
//                mService.edit(m);
//                throw new ResponseStatusException(HttpStatus.GONE, "La mesa ya no se encuentra abierta");
//            } else {
//                return ResponseEntity.ok(new OrdenConverter().apply(o));
//            }
//        } else {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "La orden se elimino de manera inesperada");
//        }
//    }

    @PostMapping(ADD_NOTA_PATH)
    synchronized void addNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto, @PathVariable("nota") String nota) {
        var o = getUc().findBy(idOrden);
        if (o == null) {
            return;
        }
        for (ProductovOrden p : o.getProductovOrdenList()) {
            if (p.getId() == idProducto) {
                getUc().addNota(idOrden, p, nota);
                return;
            }
        }
    }

    @GetMapping(GET_NOTA_PATH)
    synchronized ResponseEntity<Map<String, String>> getNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto) {
        var o = getUc().findBy(idOrden);
        if (o == null) {
            return ResponseEntity.ok(Collections.singletonMap("nota", ""));
        }
        for (ProductovOrden p : o.getProductovOrdenList()) {
            if (p.getId() == idProducto) {
                if (p.getNota() != null) {
                    return ResponseEntity.ok(Collections.singletonMap("nota", Objects.requireNonNullElse(p.getNota().getDescripcion(), "")));
                }
            }
        }
        return ResponseEntity.ok(Collections.singletonMap("nota", ""));
    }

    @GetMapping(CAN_VIEW_ORDEN_LOG_PATH)
    synchronized void canViewOrdenLog(@RequestBody Personal usuario, String codOrden) {
        getUc().canViewOrdenLog(usuario, codOrden);
    }

    @GetMapping(VALIDATE_ADD_ORDEN_PATH)
    synchronized boolean validateAddOrden() {
        return getUc().validateAddOrden();
    }

    @PutMapping(SET_DE_LA_CASA_PATH)
    synchronized void setDeLaCasa(@PathVariable("id") String codOrden, @PathVariable("boolValue") boolean booleanValue) {
        getUc().setDeLaCasa(codOrden, booleanValue);
    }

    @PutMapping(SET_PORCIENTO_PATH)
    synchronized void setPorciento(@RequestParam String codOrden, @RequestBody float porciento_servicio) {
        getUc().setPorciento(codOrden, porciento_servicio);
    }

    @GetMapping(GET_VALOR_TOTAL_ORDEN_PATH)
    synchronized float getValorTotalOrden(@RequestParam String codOrden) {
        return getUc().getValorTotalOrden(codOrden);
    }

    @PostMapping(IMPRIMIR_PRE_TICKET_PATH)
    synchronized void imprimirPreTicket(@RequestParam String codOrden) {
        getUc().imprimirPreTicket(codOrden);
    }

    @PostMapping(IMPRIMIR_LISTA_ORDENES_PATH)
    synchronized void impimirListaOrdenes(@RequestBody List<Orden> list, @RequestParam int codVenta) {
        getUc().impimirListaOrdenes(list, codVenta);
    }

    @PutMapping(ENVIAR_COCINA_PATH)
    synchronized ResponseEntity<Boolean> enviarACocina(@PathVariable("id") String codOrden, HttpServletRequest inRequest) {
        getUc().enviarACocina(codOrden, inRequest.getRemoteHost());
        return ResponseEntity.ok(true);
    }

    @PutMapping(CERRAR_ORDEN_PATH)
    synchronized ResponseEntity<OrdenModel> cerrarOrden(@PathVariable("id") String codOrden, @RequestBody boolean imprimirTicket) {
        Orden o = getUc().cerrarOrden(codOrden, imprimirTicket);
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @GetMapping(FIND_MESA_CAJA_PATH)
    synchronized EntityModel<Mesa> findMesaCaja() {
        EntityModel<Mesa> entityModel = mesaAssembler.toModel(getUc().findMesaCaja());
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).findMesaCaja()).withRel("find_mesa_caja"));
        return entityModel;
    }

    @GetMapping(GET_PDV_LIST_PATH)
    synchronized CollectionModel<EntityModel<ProductoVenta>> getPDVList(@RequestParam String codOrden) {
        CollectionModel<EntityModel<ProductoVenta>> entityModel
                = productoVentaAssembler.toCollectionModel(getUc().getPDVList(codOrden));
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).getPDVList(codOrden)).withRel("get_producto_by_seccion"));
        return entityModel;
    }

    @GetMapping(GET_PRODUCTO_BY_SEECION_PATH)
    synchronized CollectionModel<EntityModel<ProductoVenta>> getProductoBySeccion(@RequestBody Seccion seccion) {
        CollectionModel<EntityModel<ProductoVenta>> entityModel
                = productoVentaAssembler.toCollectionModel(getUc().getProductoBySeccion(seccion));
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).getProductoBySeccion(seccion)).withRel("get_pdv_list"));
        return entityModel;
    }

    @GetMapping(GET_LISTA_MESAS_DISPONIBLES_PATH)
    synchronized CollectionModel<EntityModel<Mesa>> getListaMesasDisponibles() {
        CollectionModel<EntityModel<Mesa>> entityModel
                = mesaAssembler.toCollectionModel(getUc().getListaMesasDisponibles());
        entityModel.add(linkTo(methodOn(OrdenEndPoint.class).getListaMesasDisponibles()).withRel("get_lista_mesas_disponibles"));
        return entityModel;
    }
}
