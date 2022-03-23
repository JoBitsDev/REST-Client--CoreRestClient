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
import com.jobits.pos.core.repo.impl.OrdenDAO;
import com.jobits.pos.core.repo.impl.ProductovOrdenDAO;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
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
@RequestMapping(path = "pos/orden")
public class OrdenEndPoint extends CrudRestEndPointTemplate<Orden, OrdenService>
        implements OrdenService {

    @Override
    public OrdenService getUc() {
        return PosCoreModule.getInstance().getImplementation(OrdenService.class);
    }

    @GetMapping("{id}")
    @Deprecated
    public ResponseEntity<OrdenModel> findSimple(@PathVariable("id") String codOrden) {
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().findBy(codOrden)));
    }

    @Override
    @PostMapping("{codOrden}/add-product/{codProduct}/{cantidad}/extras")
    public ProductovOrden addProduct(@PathVariable("codOrden") String codOrden,
            @PathVariable("codProduct") String producto_seleccionado,
            @PathVariable("cantidad") Float cantidad,
            @PathParam("agregadoA") Optional<Integer> productoOrdenAgregar) {
        return getUc().addProduct(codOrden, codOrden, cantidad, productoOrdenAgregar);
    }

    @Override
    @PostMapping("{codOrden}/add-product-advanced/{codProduct}/{cantidad}")
    public Orden addProductoCompuesto(@PathVariable("codOrden") String codOrden,
            @PathVariable("codProduct") String producto_agregar,
            @PathVariable("cantidad") Float cantidad,
            @RequestBody List<ProductoVentaWrapper> lista_agregos) {
        return getUc().addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos);
    }

    @Override
    @PostMapping("{codOrden}/add-product-hot/{nombre}/{precio}/{cantidad}")
    public Orden addProductInHot(@PathParam("codOrden") String codOrden,
            @PathVariable("nombre") String nombre,
            @PathVariable("precio") String precio, @PathVariable("cantidad") String cantidad) {
        return getUc().addProductInHot(codOrden, nombre, precio, cantidad);
    }

    @Override
    @DeleteMapping("{codOrden}/remove-product/{id}/{cantidad}")
    public Orden removeProduct(@PathVariable("codOrden") String codOrden, @PathVariable("id") int idProductoOrden, @PathVariable("cantidad") float cantidad) {
        return getUc().removeProduct(codOrden, idProductoOrden, cantidad);
    }

    @Override
    @PostMapping("{id}/print-summary")
    public void imprimirPreTicket(@PathVariable("id") String codOrden) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostMapping("{id}/set-client/{idClient}")
    public Orden setCliente(@PathVariable("id") String codOrden, @PathVariable("idClient") Integer clienteId) {
        return getUc().setCliente(codOrden, clienteId);
    @GetMapping("{id}")
    synchronized public ResponseEntity<OrdenModel> findSimple(@PathVariable("id") String codOrden) {
        getUc().findAll();//TODO: pifia metida aqui para refrescar la instancia
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().findBy(codOrden)));
    }

    @Deprecated
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
        getUc().removeProduct(codOrden, prod.getId(), cantidad);
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
    @Override
    public Orden moverA(@PathVariable("id") String codOrden, @PathVariable("codMesa") String codMesa) {
        return getUc().findBy(codOrden);
    synchronized ResponseEntity<OrdenModel> moverA(@PathVariable("id") String codOrden, @PathVariable("codMesa") String codMesa) {
        Orden o = getUc().findBy(codOrden);
        MesaService mService = PosCoreModule.getInstance().getImplementation(MesaService.class);
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().moverA(codOrden, codMesa)));
    }

    @PostMapping(ADD_NOTA_PATH)
    public Orden addNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto, @PathVariable("nota") String nota) {
    synchronized void addNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto, @PathVariable("nota") String nota) {
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
