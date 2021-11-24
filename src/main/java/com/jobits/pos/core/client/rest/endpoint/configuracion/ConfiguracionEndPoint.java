/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.configuracion;

import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.core.domain.models.Configuracion;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.core.usecase.algoritmo.ParametrosConfiguracion;
import com.jobits.pos.recursos.R;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/configuration")
public class ConfiguracionEndPoint implements ConfiguracionService {

    public static final String CARGAR_CONFIGURACION_PATH = "/list";

    public static final String UPDATE_CONFIGURACION_PATH = "/update/{settingId}/{newValue}";

    public static final String GET_CONFIGURACION_PATH = "/find/{settingId}";

    public static final String GET_CONFIGURACION_Y_PATH = "/y";

    ConfiguracionService service = PosCoreModule.getInstance().getImplementation(ConfiguracionService.class);

    @Override
    @GetMapping(CARGAR_CONFIGURACION_PATH)
    public Map<String, Configuracion> cargarConfiguracion() {
        return service.cargarConfiguracion();
    }

    @Override
    @PutMapping(UPDATE_CONFIGURACION_PATH)
    public Configuracion updateConfiguracion(@PathVariable("settingId") R.SettingID v,
            @PathVariable("newValue") Object configuration) {
        return service.updateConfiguracion(v, configuration);
    }

    @Override
    @GetMapping(GET_CONFIGURACION_Y_PATH)
    public ParametrosConfiguracion cargarConfiguracionY() {
        return service.cargarConfiguracionY();
    }

    @Override
    @GetMapping(GET_CONFIGURACION_PATH)
    public Configuracion getConfiguracion(@PathVariable("settingId") R.SettingID v) {
        return service.getConfiguracion(v);
    }

}
