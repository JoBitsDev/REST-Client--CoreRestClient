/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.module.PosCoreModule;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jobits.pos.controller.trabajadores.NominasUseCase;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/nominas")
public class NominasEndPoint implements NominasUseCase {

    public static final String GET_PERSONAL_ACTIVO_PATH = "/personal-activo/{desde}/{hasta}";//ISO 8601
    public static final RequestMethod GET_PERSONAL_ACTIVO_METHOD = RequestMethod.GET;

    public static final String IMPRIMIR_ESTADISTICAS_PATH = "/imprimir-estadisticas";
    public static final RequestMethod IMPRIMIR_ESTADISTICAS_METHOD = RequestMethod.GET;

    public static final String PAGAR_PATH = "/pagar/{hasta}/{imprimir}";
    public static final RequestMethod PAGAR_METHOD = RequestMethod.PUT;

    public NominasUseCase getUc() {
        return PosCoreModule.getInstance().getImplementation(NominasUseCase.class);
    }

    @Override
    @GetMapping(GET_PERSONAL_ACTIVO_PATH)
    public List<AsistenciaPersonalEstadisticas> getPersonalActivo(
            @PathVariable("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_desde,
            @PathVariable("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_hasta) {
        return getUc().getPersonalActivo(fecha_desde, fecha_hasta);
    }

    @Override
    @PutMapping(IMPRIMIR_ESTADISTICAS_PATH)
    public void imprimirEstadisticas(@RequestBody List<AsistenciaPersonalEstadisticas> lista_personal) {
        getUc().imprimirEstadisticas(lista_personal);
    }

    @PutMapping(PAGAR_PATH)
    @Override
    public List<AsistenciaPersonalEstadisticas> pagar(@RequestBody List<AsistenciaPersonalEstadisticas> list,
            @PathVariable("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable("imprimir") boolean flag) {
        return getUc().pagar(list, date, flag);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
