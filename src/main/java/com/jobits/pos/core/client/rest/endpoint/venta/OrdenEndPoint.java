/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.venta;

import com.jobits.pos.controller.areaventa.MesaService;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Override
    @PostMapping(value = OrdenService.ADD_PRODUCT_PATH)
    public Orden addProduct(@PathVariable("codOrden") String codOrden,
                            @PathVariable("codProduct") String producto_seleccionado,
                            @PathVariable("cantidad") Float cantidad,
                            @PathVariable("productoAgregado") int productoOrdenAgregar) {
        return getUc().addProduct(codOrden, producto_seleccionado, cantidad, productoOrdenAgregar);
    }

    @Override
    @PostMapping(OrdenService.ADD_PRODUCT_ORDEN_PATH)
    public Orden addProductvOrden(@PathVariable("idProductovOrden") int codProductvOrden,
                                  @PathVariable("cantidad") Float cantidad) {
        return getUc().addProductvOrden(codProductvOrden, cantidad);
    }

    @Override
    @PostMapping(ADD_PRODUCTO_COMPUESTO_PATH)
    public Orden addProductoCompuesto(@PathVariable("codOrden") String codOrden,
                                      @PathVariable("idProducto") String producto_agregar,
                                      @PathVariable("cantidad") Float cantidad,
                                      @RequestBody List<ProductoVentaWrapper> lista_agregos) {
        return getUc().addProductoCompuesto(codOrden, producto_agregar, cantidad, lista_agregos);
    }

    @Override
    @PostMapping(ADD_PRODUCTO_IN_HOT_PATH)
    public Orden addProductInHot(@PathVariable("codOrden") String codOrden,
                                 @PathVariable("nombre") String nombre,
                                 @PathVariable("precio") String precio,
                                 @PathVariable("cantidad") String cantidad) {
        return getUc().addProductInHot(codOrden, nombre, precio, cantidad);
    }

    @Override
    @DeleteMapping(REMOVE_PRODUCT_PATH)
    public Orden removeProduct(@PathVariable("codOrden") String codOrden, @PathVariable("idProducto") int idProductoOrden, @PathVariable("cantidad") float cantidad) {
        return getUc().removeProduct(codOrden, idProductoOrden, cantidad);
    }

    @Override
    @PostMapping(IMPRIMIR_PRE_TICKET_PATH)
    public void imprimirPreTicket(@PathVariable("codOrden") String codOrden) {
        getUc().imprimirPreTicket(codOrden);
    }


    @Override
    @PostMapping()
    public Orden setCliente(@PathVariable("codOrden") String codOrden, @PathVariable("idClient") Integer clienteId) {
        return getUc().setCliente(codOrden, clienteId);

    }

    @Override
    @PostMapping("{id}/mark-ready-to-pick-up/{codProductoOrden}/{ammount}")
    public Orden markReadyToPickup(String codOrden, int codProductoOrden, float ammount) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }


    @PostMapping(OrdenService.SET_PAGADO_POR_TARJETA_PATH)
    @Override
    public Orden setPagadoPorTarjeta(@PathVariable("codOrden") String codOrden, @PathVariable("pagadoTarjeta") boolean pagadoPorTarjeta) {
        return getUc().setPagadoPorTarjeta(codOrden, pagadoPorTarjeta);
    }


    @Override
    public Orden cerrarOrden(String string, boolean bln) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PutMapping(CERRAR_ORDEN_PATH)
    synchronized public Orden cerrarOrden(@PathVariable("codOrden") String codOrden,
                                          @PathVariable("imprimirTicket") boolean imprimirTickets,
                                          @PathVariable("pagadoCash") float pagadoCash,
                                          @PathVariable("pagadoTarjeta") float pagadoTarjeta) {
        return getUc().cerrarOrden(codOrden, imprimirTickets, pagadoCash, pagadoTarjeta);
    }


    @Override
    @PutMapping(SET_PORCIENTO_PATH)
    public Orden setPorciento(@PathVariable("codOrden") String codOrden, @PathVariable("porciento") float porciento_servicio) {
        return getUc().setPorciento(codOrden, porciento_servicio);
    }


    @Override
    public Orden enviarACocina(String codOrden) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @PutMapping(ENVIAR_COCINA_PATH)
    public Orden enviarACocinaApi(@PathVariable("codOrden") String codOrden,
                                  @PathVariable("uuid") String uuid,
                                  HttpServletRequest request) {
        if (uuid.equals("127.0.0.1")) {
            uuid = request.getRemoteHost();
        }
        return getUc().enviarACocina(codOrden, uuid);
    }

    @Override
    public Orden enviarACocina(String s, String s1) {
        return getUc().enviarACocina(s, s1);
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
    public synchronized Orden moverA(@PathVariable("codOrden") String codOrden, @PathVariable("codMesa") String codMesa) {
        Orden o = getUc().findBy(codOrden);
        MesaService mService = PosCoreModule.getInstance().getImplementation(MesaService.class);
        return getUc().moverA(codOrden, codMesa);
    }

    @PostMapping(ADD_NOTA_PATH)
    public synchronized Orden addNota(@PathVariable("codOrden") String idOrden, @PathVariable("codProducto") int idProducto, @PathVariable("nota") String nota) {
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
    synchronized ResponseEntity<Map<String, String>> getNota(@PathVariable("codOrden") String idOrden, @PathVariable("codProducto") int idProducto) {
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
    public synchronized Orden setDeLaCasa(@PathVariable("codOrden") String codOrden, @PathVariable("boolValue") boolean booleanValue) {
        return getUc().setDeLaCasa(codOrden, booleanValue);
    }

}
