/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.controller.trabajadores.AsistenciaPersonalService;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.module.PosCoreModule;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/asistencia-personal")
public class AsistenciaPersonalEndPoint
        extends CrudRestEndPointTemplate<AsistenciaPersonal, AsistenciaPersonalService>
        implements AsistenciaPersonalService {

    public static final String IMPRIMIR_ASISTENCIA = "/venta/{id}/imprimir-asistencia";

    public static final String CALCULAR_PAGO_TRABAJADOR = "/venta/{id}/calcular-pago/{usuario}";

    public static final String UPDATE_A_MAYORES = "/venta/{id}/a-mayores/{usuario}/{cantidad}";

    public static final String GET_PERSONAL_TRABAJANDO = "/venta/{id}/get-personal-trabajando";

    public static final String UPDATE_SALARIES = "/venta/{id}/update-salaries";


    @Override
    public AsistenciaPersonalService getUc() {
        return PosCoreModule.getInstance().getImplementation(AsistenciaPersonalService.class);
    }

    @Override
    @PostMapping(CALCULAR_PAGO_TRABAJADOR)
    public AsistenciaPersonal calcularPagoTrabajador(@PathVariable("id") int idVenta, @PathVariable String usuario) {
        return getUc().calcularPagoTrabajador(idVenta, usuario);
    }

    @Override
    @GetMapping(IMPRIMIR_ASISTENCIA)
    public void imprimirAsistencia(@PathVariable("id") int idVenta) {
        getUc().imprimirAsistencia(idVenta);
    }

    @Override
    @PutMapping(UPDATE_A_MAYORES)
    public AsistenciaPersonal updateAMayores(@PathVariable("id") int idVenta, @PathVariable String usuario, @PathVariable float cantidad) {
        return getUc().updateAMayores(idVenta, usuario, cantidad);
    }

    @Override
    @GetMapping(GET_PERSONAL_TRABAJANDO)
    public List<AsistenciaPersonal> getPersonalTrabajando(@PathVariable("id") int idVenta) {
        return getUc().getPersonalTrabajando(idVenta);
    }

    @Override
    @GetMapping(UPDATE_SALARIES)
    public List<AsistenciaPersonal> updateSalaries(@PathVariable("id") int idVenta) {
        return getUc().updateSalaries(idVenta);
    }

}
