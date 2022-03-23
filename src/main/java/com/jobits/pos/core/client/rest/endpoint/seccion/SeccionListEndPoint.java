/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.seccion;

import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.jobits.pos.client.rest.endpoint.UrlTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/seccion")
public class SeccionListEndPoint implements SeccionListService {

    public static final String FIND_SECCION_BY_MESA_PATH = "/list/by-mesa/{idMesa}";
    public static final String MOVE_SECCION_PATH = "/{nombreSeccion}/move-to/{codCarta}";

    public SeccionListEndPoint() {
    }

    public SeccionListService getUc() {
        return PosCoreModule.getInstance().getImplementation(SeccionListService.class);
    }

    @Override
    @PutMapping(UrlTemplate.EDIT_PATH)
    public Seccion edit(@RequestBody Seccion t) throws RuntimeException {
        return getUc().edit(t);
    }

    @Override
    public Seccion destroy(Seccion t) throws RuntimeException {
        throw new UnsupportedOperationException("En desarrollo");
    }

    @Override
    @DeleteMapping(UrlTemplate.DESTROY_ID_PATH)
    public Seccion destroyById(@PathVariable("id") Object o) throws RuntimeException {
        return getUc().destroyById(o);
    }

    @Override
    @GetMapping(UrlTemplate.FIND_BY_PATH)
    public Seccion findBy(@PathVariable("id") Object o) throws RuntimeException {
        return getUc().findBy(o);
    }

    @Override
    @GetMapping(UrlTemplate.FIND_ALL_PATH)
    public List<Seccion> findAll() throws RuntimeException {
        return getUc().findAll();
    }

//    @GetMapping(FIND_SECCION_BY_MESA_PATH)
//    public ResponseEntity<List<SeccionModel>> findSeccionesByMesa(@PathVariable("idMesa") String mesa) {
//        MesaService service = PosCoreModule.getInstance().getImplementation(MesaService.class);
//        List<SeccionModel> ret = new ArrayList<>();
//        for (Seccion s : getUc().findSeccionesByMesa(service.findBy(mesa))) {
//            ret.add(SeccionModel.of(s));
//        }
//        return ResponseEntity.ok(ret);
//    }
    @Override
    @GetMapping(FIND_SECCION_BY_MESA_PATH)
    public synchronized  ResponseEntity<List<SeccionModel>> findSeccionesByMesa(@PathVariable("idMesa") String mesa) {
        List<SeccionModel> ret = new ArrayList<>();
        List<Seccion> aux = getUc().findSeccionesByMesa(mesa);
        for (Seccion s : aux) {
            ret.add(SeccionModel.of(s));
        }
        return ResponseEntity.ok(ret);
    }

    @Override
    @PutMapping(MOVE_SECCION_PATH)
    public Seccion moveSeccionToCarta(@PathVariable("nombreSeccion") String seccionNombre,
            @PathVariable("codCarta") String codigoCarta) {
        return getUc().moveSeccionToCarta(seccionNombre, codigoCarta);
    }

}
