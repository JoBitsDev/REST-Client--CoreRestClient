/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobits.pos.core.client.rest.endpoint.notifications;

import com.jobits.pos.controller.puntoelaboracion.NotificacionPuntoElaboracionService;
import com.jobits.pos.core.client.rest.persistence.models.ProductoVentaModel;
import com.jobits.pos.core.client.rest.persistence.models.ProductoVentaOrdenModel;
import com.jobits.pos.core.domain.models.NotificacionEnvioCocina;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.minidev.json.JSONObject;
import org.jobits.pos.client.rest.endpoint.DefaultEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@RestController
@RequestMapping(path = "/notifications")
public class NotificationsEndpoint extends DefaultEndpoint {

    private NotificacionPuntoElaboracionService service = PosCoreModule.getInstance().getImplementation(NotificacionPuntoElaboracionService.class);

    public NotificationsEndpoint() {
    }

    @GetMapping(path = "{cod_cocina}/get-pending")
    public ResponseEntity<List<ProductoVentaOrdenModel>> showPending(@PathVariable("cod_cocina") String codCocina, HttpServletRequest inRequest) {
        service.linkDeviceAsPrinter(codCocina, inRequest.getRemoteHost());
        List<ProductoVentaOrdenModel> ret = new ArrayList<>();
        var all = service.getPendingNotificationsFrom(codCocina);
        for (NotificacionEnvioCocina x : all) {
            x.getProductovOrden().setCantidad(x.getCantidad());
            ret.add(addProductoVentaOrdenModel(x));
        }
        return ResponseEntity.ok(ret);
    }

    @PostMapping("notify/{cod_orden}/{id_producto_orden}/{cantidad}")
    public ResponseEntity<List<String>> notifyCocina(@PathVariable("cod_orden") String codOrden,
            @PathVariable("id_producto_orden") int codProductoOrden,
            @PathVariable("cantidad") float cantidad) {
        return ResponseEntity.ok(Collections.singletonList(service.markProductAsCompleted(codOrden, codProductoOrden, cantidad)));
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
                x.getCantidad(),
                productoVenta,
                x.getNumeroComensal(),
                nota,
                x.getOrden().getCodOrden(),
                x.getOrden().getMesacodMesa().getCodMesa(),
                x.getOrden().getPersonalusuario().getUsuario());
        return po;

    }
}
