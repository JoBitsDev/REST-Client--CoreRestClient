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
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.*;

/**
 * @author Home
 */
@RestController
@RequestMapping(path = OrdenService.BASE)
public class OrdenEndPoint extends CrudRestEndPointTemplate<Orden, OrdenService>
        implements OrdenService {

    @Override
    public OrdenService getUc() {
        return PosCoreModule.getInstance().getImplementation(OrdenService.class);
    }

    @GetMapping("{id}")
    @Deprecated
    synchronized public ResponseEntity<OrdenModel> findSimple(@PathVariable("id") String codOrden) {
        getUc().findAll();//TODO: pifia metida aqui para refrescar la instancia 
        return ResponseEntity.ok(new OrdenConverter().apply(getUc().findBy(codOrden)));
    }

    @Override
    @PostMapping(OrdenService.ADD_PRODUCT_PATH)
    public ProductovOrden addProduct(@PathVariable("codOrden") String codOrden,
                                     @PathVariable("codProduct") String producto_seleccionado,
                                     @PathVariable("cantidad") Float cantidad,
                                     @PathParam("agregadoA") Optional<Integer> productoOrdenAgregar) {
        return getUc().addProduct(codOrden, codOrden, cantidad, productoOrdenAgregar);
    }

    @Override
    @PostMapping(ADD_PRODUCTO_COMPUESTO_PATH)
    public Orden addProductoCompuesto(@PathVariable("codOrden") String codOrden,
                                      @PathVariable("codProduct") String producto_agregar,
                                      @PathVariable("cantidad") Float cantidad,
                                      @RequestBody List<ProductoVentaWrapper> lista_agregos) {
        return getUc().addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos);
    }

    @Override
    @PostMapping(ADD_PRODUCTO_IN_HOT_PATH)
    public Orden addProductInHot(@PathParam("codOrden") String codOrden,
                                 @PathVariable("nombre") String nombre,
                                 @PathVariable("precio") String precio, @PathVariable("cantidad") String cantidad) {
        return getUc().addProductInHot(codOrden, nombre, precio, cantidad);
    }

    @Override
    @DeleteMapping(REMOVE_PRODUCT_PATH)
    public Orden removeProduct(@PathVariable("codOrden") String codOrden, @PathVariable("id") int idProductoOrden, @PathVariable("cantidad") float cantidad) {
        return getUc().removeProduct(codOrden, idProductoOrden, cantidad);
    }

    @Override
    @PostMapping(IMPRIMIR_PRE_TICKET_PATH)
    public void imprimirPreTicket(@PathVariable("id") String codOrden) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostMapping()
    public Orden setCliente(@PathVariable("id") String codOrden, @PathVariable("idClient") Integer clienteId) {
        return getUc().setCliente(codOrden, clienteId);

    }

    @Override
    @PostMapping("{id}/mark-ready-to-pick-up/{codProductoOrden}/{ammount}")
    public Orden markReadyToPickup(String codOrden, int codProductoOrden, float ammount) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }


    @PostMapping(OrdenService.SET_PAGADO_POR_TARJETA_PATH)
    @Override
    public Orden setPagadoPorTarjeta(@PathVariable("id") String codOrden, @PathVariable("pagadoTarjeta") boolean pagadoPorTarjeta) {
        return getUc().setPagadoPorTarjeta(codOrden, pagadoPorTarjeta);
    }


    @Override
    public Orden cerrarOrden(String string, boolean bln) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PutMapping(CERRAR_ORDEN_PATH)
    synchronized public Orden cerrarOrden(@PathVariable("id") String codOrden,
                                          @PathVariable("imprimirTickets") boolean imprimirTickets,
                                          @PathVariable("pagadoCash") float pagadoCash,
                                          @PathVariable("pagadoTarjeta") float pagadoTarjeta) {
        return getUc().cerrarOrden(codOrden, imprimirTickets, pagadoCash, pagadoTarjeta);
    }

    @PutMapping(CERRAR_ORDEN_PATH+"old")
    synchronized ResponseEntity<OrdenModel> cerrarOrdenOld(@PathVariable("id") String codOrden, @RequestBody boolean imprimirTicket) {
        // return ResponseEntity.badRequest().body(new OrdenModel());
        var o = getUc().findBy(codOrden);
        o = getUc().cerrarOrden(codOrden, imprimirTicket, o.getOrdenvalorMonetario(), 0);//TODO: implementar el pago por tarjeta
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @Override
    @PostMapping(SET_PORCIENTO_PATH)
    public Orden setPorciento(String codOrden, float porciento_servicio) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden enviarACocina(String codOrden) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostMapping(ENVIAR_COCINA_PATH)
    public Orden enviarACocina(String codOrden, String uuid) {
        return getUc().enviarACocina(codOrden, uuid);
    }


    @Deprecated
    @PostMapping(ADD_PRODUCT_PATH+"old")
    synchronized ResponseEntity<OrdenModel> addProduct(@PathVariable("id") String codOrden, @PathVariable("idProducto") String producto_seleccionado, @PathVariable("cantidad") Float cantidad) {
        ProductoVentaService productoService = PosCoreModule.getInstance().getImplementation(ProductoVentaService.class);
        getUc().addProduct(codOrden, productoService.findBy(producto_seleccionado).getCodigoProducto(), cantidad, null);
        Orden o = getUc().findBy(codOrden);
        return ResponseEntity.ok(new OrdenConverter().apply(o));
    }

    @DeleteMapping(REMOVE_PRODUCT_PATH+"old")
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

    @GetMapping("/{id}/validate")
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
    public synchronized Orden moverA(@PathVariable("id") String codOrden, @PathVariable("codMesa") String codMesa) {
        Orden o = getUc().findBy(codOrden);
        MesaService mService = PosCoreModule.getInstance().getImplementation(MesaService.class);
        return getUc().moverA(codOrden, codMesa);
    }

    @PostMapping(ADD_NOTA_PATH)
    public synchronized Orden addNota(@PathVariable("id") String idOrden, @PathVariable("idProductoOrden") int idProducto, @PathVariable("nota") String nota) {
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
    synchronized ResponseEntity<Map<String, String>> getNota(@PathVariable("id") String idOrden, @PathVariable("codProducto") int idProducto) {
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
    @Override
    public synchronized Orden setDeLaCasa(@PathVariable("id") String codOrden, @PathVariable("boolValue") boolean booleanValue) {
        return getUc().setDeLaCasa(codOrden, booleanValue);
    }

    @PutMapping(ENVIAR_COCINA_PATH)
    synchronized ResponseEntity<Boolean> enviarACocina(@PathVariable("id") String codOrden, HttpServletRequest inRequest) {
        getUc().enviarACocina(codOrden, inRequest.getRemoteHost());
        return ResponseEntity.ok(true);
    }

}
