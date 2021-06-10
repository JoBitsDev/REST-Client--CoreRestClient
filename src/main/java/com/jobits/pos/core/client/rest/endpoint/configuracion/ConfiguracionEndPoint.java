/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.configuracion;

import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.core.client.rest.assembler.ConfiguracionModelAssembler;
import com.jobits.pos.core.client.rest.assembler.models.ConfigurationObjectModelAssembler;
import com.jobits.pos.core.client.rest.assembler.models.ConfigurationObjectWrapper;
import com.jobits.pos.core.domain.models.Configuracion;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.recursos.R;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/configuracion")
public class ConfiguracionEndPoint extends CrudRestServiceTemplate<Configuracion> {

    public static final String CARGAR_CONFIGURACION_PATH = "/cargar_configuracion";
    public static final RequestMethod CARGAR_CONFIGURACION_METHOD = RequestMethod.GET;

    public static final String UPDATE_CONFIGURACION_PATH = "/update_configuracion";
    public static final RequestMethod UPDATE_CONFIGURACION__METHOD = RequestMethod.PUT;

    public static final String GET_CONFIGURACION_PATH = "/get_configuracion";
    public static final RequestMethod GET_CONFIGURACION_METHOD = RequestMethod.GET;

    ConfiguracionModelAssembler configuracionAssembler = new ConfiguracionModelAssembler();
    ConfigurationObjectModelAssembler configuracionObjectAssembler = new ConfigurationObjectModelAssembler();

    @Override
    public ConfiguracionService getUc() {
        return PosCoreModule.getInstance().getImplementation(ConfiguracionService.class);
    }

    @Override
    public CrudModelAssembler<Configuracion> getAssembler() {
        return configuracionAssembler;
    }

    @GetMapping(CARGAR_CONFIGURACION_PATH)
    public boolean cargarConfiguracion() {
        getUc().cargarConfiguracion();
        return true;
    }

    @PutMapping(UPDATE_CONFIGURACION_PATH)
    public boolean updateConfiguracion(@RequestBody R.SettingID v, @RequestBody Object configuration) {
        getUc().updateConfiguracion(v, configuration);
        return true;
    }

    @GetMapping(GET_CONFIGURACION_PATH)
    public EntityModel<ConfigurationObjectWrapper> getConfiguracion(@RequestBody R.SettingID v) {
        EntityModel<ConfigurationObjectWrapper> entityModel
                = configuracionObjectAssembler.toModel(new ConfigurationObjectWrapper(getUc().getConfiguracion(v)));
        entityModel.add(linkTo(methodOn(ConfiguracionEndPoint.class).getConfiguracion(v)).withRel("get_configuracion"));
        return entityModel;
    }
}
