/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.controller.venta.OrdenService;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/orden")
public class OrdenEndPoint extends CrudRestEndPointTemplate<Orden, OrdenService>
        implements OrdenService {

    @Override
    public OrdenService getUc() {
        return PosCoreModule.getInstance().getImplementation(OrdenService.class);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdenModel> findSimple(@PathVariable("id") String codOrden) {
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().findBy(codOrden)));
    }

    @Override
    public ProductovOrden addProduct(String codOrden, String producto_seleccionado, Float cantidad, Optional<Integer> productoOrdenAgregar) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden addProductoCompuesto(String codOrden, String producto_agregar, Float cantidad, List<ProductoVentaWrapper> lista_agregos) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden addProductInHot(String codOrden, String nombre, String precio, String cantidad) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden removeProduct(String codOrden, int idProductoOrden, float cantidad) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoVenta> getPDVList(String codOrden) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoVenta> getProductoBySeccion(String seccion) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimirPreTicket(String codOrden) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void impimirListaOrdenes(int codVenta) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden setCliente(String codOrden, Integer clienteId) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @PostMapping(ADD_PRODUCT_PATH)
    ResponseEntity<OrdenModel> addProduct(@PathVariable("id") String codOrden, @PathVariable("idProducto") String producto_seleccionado, @PathVariable("cantidad") Float cantidad) {
        ProductoVentaService productoService = PosCoreModule.getInstance().getImplementation(ProductoVentaService.class);
        getUc().addProduct(codOrden, producto_seleccionado, cantidad, Optional.empty());
        Orden o = getUc().findBy(codOrden);
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @DeleteMapping(REMOVE_PRODUCT_PATH)
    ResponseEntity<OrdenModel> removeProduct(@PathVariable("id") String codOrden, @PathVariable("idProducto") int producto_orden_seleccionado, @PathVariable("cantidad") Float cantidad) {
        var orden = getUc().findBy(codOrden);
        ProductovOrden prod = null;
        for (ProductovOrden p : orden.getProductovOrdenList()) {
            if (p.getId() == producto_orden_seleccionado) {
                prod = p;
                break;
            }
        }
        getUc().removeProduct(codOrden, prod.getId(), cantidad);
        Orden o = getUc().findBy(codOrden);
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @GetMapping(VALIDATE_ORDEN_PATH)
    ResponseEntity<OrdenModel> validateOrden(@PathVariable("id") String codOrden) {
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
    @Override
    public Orden moverA(@PathVariable("id") String codOrden, @PathVariable("codMesa") String codMesa) {
        return getUc().findBy(codOrden);
    }

    @PostMapping(ADD_NOTA_PATH)
    public Orden addNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto, @PathVariable("nota") String nota) {
        var o = getUc().findBy(idOrden);
        if (o == null) {
            throw new NullPointerException("Codigo de orden invalido");
        }
        for (ProductovOrden p : o.getProductovOrdenList()) {
            if (p.getId() == idProducto) {
                getUc().addNota(idOrden, p.getId(), nota);
                return o;
            }
        }
        throw new NullPointerException("Codigo de producto invalido");
    }

    @GetMapping(GET_NOTA_PATH)
    ResponseEntity<Map<String, String>> getNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto) {
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

    @PutMapping(SET_DE_LA_CASA_PATH)
    public Orden setDeLaCasa(@PathVariable("id") String codOrden, @PathVariable("boolValue") boolean booleanValue) {
        return getUc().setDeLaCasa(codOrden, booleanValue);
    }

    @PutMapping(SET_PORCIENTO_PATH)
    public Orden setPorciento(@RequestParam String codOrden, @RequestBody float porciento_servicio) {
        return getUc().setPorciento(codOrden, porciento_servicio);
    }

    @PutMapping(ENVIAR_COCINA_PATH)
    public Orden enviarACocina(@PathVariable("id") String codOrden) {
        return getUc().enviarACocina(codOrden);
    }

    @PutMapping(CERRAR_ORDEN_PATH)
    public Orden cerrarOrden(@PathVariable("id") String codOrden, @RequestBody boolean imprimirTicket) {
        return getUc().cerrarOrden(codOrden, imprimirTicket);
    }

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

}
