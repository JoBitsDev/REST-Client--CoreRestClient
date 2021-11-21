/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.insumo;

import com.jobits.pos.controller.insumo.InsumoService;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.List;
import org.jobits.pos.client.rest.endpoint.CrudRestEndPointTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/insumos")
public class InsumoEndPoint extends CrudRestEndPointTemplate<Insumo, InsumoService>
        implements InsumoService {

    @Override
    public InsumoService getUc() {
        return PosCoreModule.getInstance().getImplementation(InsumoService.class);
    }

    @Override
    @PostMapping("/bulk-import")
    public List<Insumo> bulkImport(@RequestBody List<Insumo> importList) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }
}
