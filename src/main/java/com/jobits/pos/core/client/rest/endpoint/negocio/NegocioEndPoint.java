/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.negocio;

import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.controller.negocio.NegocioService;
import com.jobits.pos.core.client.rest.assembler.NegocioModelAssembler;
import com.jobits.pos.core.domain.models.Negocio;
import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.recursos.R;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/negocio")
public class NegocioEndPoint extends CrudRestServiceTemplate<Negocio> {
    
    NegocioModelAssembler negocioAssembler = new NegocioModelAssembler();
    
    @Override
    public CRUDUseCase<Negocio> getUc() {
        return PosCoreModule.getInstance().getImplementation(NegocioService.class);
    }
    
    @GetMapping("{id}/info")
    public ResponseEntity<Map<String, Object>> getInfoFromNegocio(@PathParam("id") int negocioId) {
      
        ConfiguracionService cService = PosCoreModule.getInstance().getImplementation(ConfiguracionService.class);
        Negocio n = getUc().findBy(negocioId);
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("nombre", n.getNombre());
        ret.put("monedaPrincipal", " " + n.getMonedaPrincipal());
        String secundaria = n.getMonedaPrincipal().equals("CUC") ? " MN" : " CUC";
        ret.put("monedaSecundaria", secundaria);
        ret.put("cambio", cService.getConfiguracion(R.SettingID.GENERAL_CAMBIO_MONEDA));
        return ResponseEntity.ok().body(ret);
    }
    
    @Override
    public CrudModelAssembler<Negocio> getAssembler() {
        return negocioAssembler;
    }
    
}
