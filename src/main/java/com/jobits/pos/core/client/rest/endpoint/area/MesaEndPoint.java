/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.area;

import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.AbstractUseCaseImpl;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.jobits.pos.client.rest.endpoint.UrlTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/mesa")
public class MesaEndPoint extends AbstractUseCaseImpl implements MesaService {

    public MesaEndPoint() {
    }

    public MesaService getUc() {
        return PosCoreModule.getInstance().getImplementation(MesaService.class);
    }

    @Override
    @PutMapping(UrlTemplate.EDIT_PATH)
    public Mesa edit(@RequestBody Mesa t) throws RuntimeException {
        return getUc().edit(t);
    }

    @Override
    public Mesa destroy(Mesa t) throws RuntimeException {
        throw new UnsupportedOperationException("En desarrollo");
    }

    @Override
    @DeleteMapping(UrlTemplate.DESTROY_ID_PATH)
    public Mesa destroyById(@PathVariable("id") Object o) throws RuntimeException {
        return getUc().destroyById(o);
    }

    @Override
    @GetMapping(UrlTemplate.FIND_BY_PATH)
    public Mesa findBy(@PathVariable("id") Object o) throws RuntimeException {
        return getUc().findBy(o);
    }

    @Override
    @GetMapping(UrlTemplate.FIND_ALL_PATH)
    public List<Mesa> findAll() throws RuntimeException {
        return getUc().findAll();
    }

    @Override
    @GetMapping(UrlTemplate.FIND_ALL_PATH)
    public List<Mesa> getListaMesasDisponibles() {
        return getUc().getListaMesasDisponibles();
    }

}
