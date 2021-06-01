/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.configuracion.ConfiguracionEndPoint;
import com.jobits.pos.core.domain.models.Configuracion;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class ConfiguracionModelAssembler extends CrudModelAssembler<Configuracion> {

    public ConfiguracionModelAssembler() {
        super(ConfiguracionEndPoint.class);
    }

    @Override
    public Object getId(Configuracion entity) {
        return entity.getNombre();
    }

}
