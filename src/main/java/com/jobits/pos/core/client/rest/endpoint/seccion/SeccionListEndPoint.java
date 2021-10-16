/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.seccion;

import com.jobits.pos.controller.mesa.MesaService;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.core.client.rest.assembler.SeccionModelAssembler;
import com.jobits.pos.core.client.rest.persistence.models.SeccionModel;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.repo.Converter;
import java.util.ArrayList;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/seccion-list")
public class SeccionListEndPoint extends CrudRestServiceTemplate<Seccion> {

    public static final String FIND_SECCION_BY_MESA_PATH = "/list/by-mesa/{idMesa}";
    public static final RequestMethod FIND_SECCIONES_BY_MESA_METHOD = RequestMethod.GET;
    SeccionModelAssembler seccionAssembler = new SeccionModelAssembler();
    

    @Override
    public SeccionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(SeccionListService.class);
    }

    @Override
    public CrudModelAssembler<Seccion> getAssembler() {
        return seccionAssembler;
    }

    @GetMapping(FIND_SECCION_BY_MESA_PATH)
    public ResponseEntity<List<SeccionModel>> findSeccionesByMesa(@PathVariable("idMesa") String mesa) {
        MesaService service = PosCoreModule.getInstance().getImplementation(MesaService.class);
       List<SeccionModel> ret = new ArrayList<>();
        for (Seccion s : getUc().findSeccionesByMesa(service.findBy(mesa))) {
            ret.add(SeccionModel.of(s));
        }
        return ResponseEntity.ok(ret);
    }

}
