/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.almacen;

import com.jobits.pos.controller.almacen.IPVService;
import com.jobits.pos.core.client.rest.assembler.IpvModelAssembler;
import com.jobits.pos.core.client.rest.assembler.IpvRegistroModelAssembler;
import com.jobits.pos.core.client.rest.assembler.IpvVentaRegistroModelAssembler;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.Ipv;
import com.jobits.pos.core.domain.models.IpvRegistro;
import com.jobits.pos.core.domain.models.IpvVentaRegistro;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(path = "/ipv")
public class IPVEndPoint extends CrudRestServiceTemplate<Ipv> {

    public static final String DAR_ENTRADA_EXISTENCIA_INSUMO_PATH = "/dar_entrada_existencia_insumo";
    public static final RequestMethod DAR_ENTRADA_EXISTENCIA_INSUMO_METHOD = RequestMethod.PUT;

    public static final String DAR_ENTRADA_IPV_PATH = "/dar_entrada_ipv";
    public static final RequestMethod DAR_ENTRADA_IPV_METHOD = RequestMethod.PUT;

    public static final String AJUSTAR_CONSUMO_PATH = "/ajustar_consumo";
    public static final RequestMethod AJUSTAR_CONSUMO_METHOD = RequestMethod.PUT;

    public static final String TRANSFERIR_IPV_REGISTRO_PATH = "/transferir_ipv_registro";
    public static final RequestMethod TRANSFERIR_IPV_REGISTRO_METHOD = RequestMethod.PUT;

    public static final String TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_PATH = "/transferir_ipv_registro_to_almacen";
    public static final RequestMethod TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_METHOD = RequestMethod.PUT;

    public static final String REINICIAR_IPV_PATH = "/reiniciar_ipv";
    public static final RequestMethod REINICIAR_IPV_METHOD = RequestMethod.PUT;

    public static final String GET_IPV_REGISTRO_LIST_PATH = "/ipv_registro_list";
    public static final RequestMethod GET_IPV_REGISTRO_LIST_METHOD = RequestMethod.GET;

    public static final String GET_IPV_REGISTRO_VENTA_LIST_PATH = "/ipv_registro_venta_list";
    public static final RequestMethod GET_IPV_REGISTRO_VENTA_LIST_METHOD = RequestMethod.GET;

    IpvModelAssembler ipvAssembler = new IpvModelAssembler();
    IpvRegistroModelAssembler ipvRegistroAssembler = new IpvRegistroModelAssembler();
    IpvVentaRegistroModelAssembler ipvVentaRegistroAssembler = new IpvVentaRegistroModelAssembler();

    @Override
    public IPVService getUc() {
        return PosCoreModule.getInstance().getImplementation(IPVService.class);
    }

    @Override
    public CrudModelAssembler<Ipv> getAssembler() {
        return ipvAssembler;
    }

    @PutMapping(DAR_ENTRADA_EXISTENCIA_INSUMO_PATH)
    public boolean darEntradaExistencia(Insumo insumo, Cocina cocina, @RequestParam int idVenta, @RequestParam float cantidad) {
        getUc().darEntradaExistencia(insumo, cocina, idVenta, cantidad);
        return true;
    }

    @PutMapping(DAR_ENTRADA_IPV_PATH)
    public boolean darEntradaIPV(@RequestBody IpvVentaRegistro ipv_venta_registro_seleccionado, @RequestParam float cantidad) {
        getUc().darEntradaIPV(ipv_venta_registro_seleccionado, cantidad);
        return true;
    }

    @PutMapping(AJUSTAR_CONSUMO_PATH)
    public boolean ajustarConsumo(@RequestBody IpvRegistro ipv_registro_seleciconado, @RequestParam float cantidad) {
        getUc().ajustarConsumo(ipv_registro_seleciconado, cantidad);
        return true;
    }

    @PutMapping(TRANSFERIR_IPV_REGISTRO_PATH)
    public boolean transferirIPVRegistro(@RequestBody IpvRegistro ipv_registro_seleciconado, @RequestBody Cocina cocina, @RequestParam float cantidad) {
        getUc().transferirIPVRegistro(ipv_registro_seleciconado, cocina, cantidad);
        return true;
    }

    @PutMapping(TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_PATH)
    public boolean transferirIPVRegistroToAlmacen(@RequestBody IpvRegistro ipv_registro_seleciconado, @RequestBody Almacen almacen, @RequestParam float cantidad) {
        getUc().transferirIPVRegistroToAlmacen(ipv_registro_seleciconado, almacen, cantidad);
        return true;
    }

    @PutMapping(REINICIAR_IPV_PATH)
    public boolean reiniciarIPV(@RequestBody Cocina cocina, @RequestBody Venta venta) {
        getUc().reiniciarIPV(cocina, venta);
        return true;
    }

    @GetMapping(GET_IPV_REGISTRO_LIST_PATH)
    public CollectionModel<EntityModel<IpvRegistro>> getIpvRegistroList(@RequestBody Cocina cocina, @RequestParam int codVenta) {
        CollectionModel<EntityModel<IpvRegistro>> entityModel
                = ipvRegistroAssembler.toCollectionModel(getUc().getIpvRegistroList(cocina, codVenta));
        entityModel.add(linkTo(methodOn(IPVEndPoint.class).getIpvRegistroList(cocina, codVenta)).withRel("ipv_registro_list"));
        return entityModel;
    }

    @GetMapping(GET_IPV_REGISTRO_VENTA_LIST_PATH)
    public CollectionModel<EntityModel<IpvVentaRegistro>> getIpvRegistroVentaList(@RequestBody Cocina cocina, @RequestParam int codVenta) {
        CollectionModel<EntityModel<IpvVentaRegistro>> entityModel
                = ipvVentaRegistroAssembler.toCollectionModel(getUc().getIpvRegistroVentaList(cocina, codVenta));
        entityModel.add(linkTo(methodOn(IPVEndPoint.class).getIpvRegistroVentaList(cocina, codVenta)).withRel("ipv_registro_venta_list"));
        return entityModel;
    }
}
