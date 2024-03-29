/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobits.pos.core.client.rest.endpoint.notifications;

import com.jobits.pos.controller.puntoelaboracion.NotificacionPuntoElaboracionService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.client.rest.persistence.models.ProductoVentaModel;
import com.jobits.pos.core.client.rest.persistence.models.ProductoVentaOrdenModel;
import com.jobits.pos.core.domain.models.NotificacionEnvioCocina;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.DefaultEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
@RestController
@RequestMapping(path = "pos/notifications")
public class NotificationsEndpoint extends DefaultEndpoint {

    private NotificacionPuntoElaboracionService service = PosCoreModule.getInstance().getImplementation(NotificacionPuntoElaboracionService.class);
    private OrdenService ordService = PosCoreModule.getInstance().getImplementation(OrdenService.class);

    public NotificationsEndpoint() {
    }

    @GetMapping(path = "{cod_cocina}/get-pending")
    public synchronized ResponseEntity<List<ProductoVentaOrdenModel>> showPending(@PathVariable("cod_cocina") String codCocina, HttpServletRequest inRequest) {
        service.linkDeviceAsPrinter(codCocina, inRequest.getRemoteHost());
        List<ProductoVentaOrdenModel> ret = new ArrayList<>();
        var all = service.getPendingNotificationsFrom(codCocina);
        Collections.sort(all, (o1, o2) -> {
            return o1.getHoraNotificacion().compareTo(o2.getHoraNotificacion());
        });
        for (NotificacionEnvioCocina x : all) {
            ret.add(addProductoVentaOrdenModel(x));
        }
        return ResponseEntity.ok(ret);
    }

    @PostMapping("notify/{cod_orden}/{id_producto_orden}/{cantidad}")
    public synchronized ResponseEntity<List<String>> notifyCocina(@PathVariable("cod_orden") String codOrden,
                                                                  @PathVariable("id_producto_orden") int codProductoOrden,
                                                                  @PathVariable("cantidad") float cantidad) {
        ordService.markReadyToPickup(codOrden, codProductoOrden, cantidad);
        return ResponseEntity.ok(Collections.singletonList("Notificacion Exitosa"));
    }

    private ProductoVentaOrdenModel addProductoVentaOrdenModel(NotificacionEnvioCocina n) {

        var x = n.getProductovOrden();
        if (x == null) {
            throw new IllegalArgumentException("Producto de venta invalido");
        }
        ProductoVenta p = x.getProductoVenta();
        if (p == null) {
            throw new IllegalArgumentException("Producto de venta invalido");
        }
        ProductoVentaModel productoVenta = new ProductoVentaModel(
                p.getCodigoProducto(), p.getNombre(), p.getPrecioVenta(),
                p.getDescripcion());

        String nota = x.getNota() != null ? x.getNota().getDescripcion() : null;

        ProductoVentaOrdenModel po = new ProductoVentaOrdenModel(
                x.getEnviadosacocina(),
                x.getId(),
                n.getCantidad(),
                productoVenta,
                x.getNumeroComensal(),
                nota,
                x.getOrden().getCodOrden(),
                x.getOrden().getMesacodMesa().getCodMesa(),
                x.getOrden().getPersonalusuario().getUsuario());
        return po;

    }
}
