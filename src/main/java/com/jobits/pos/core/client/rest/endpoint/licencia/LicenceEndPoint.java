/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.licencia;

import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.licencia.impl.Licence;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.Collections;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/licence")
public class LicenceEndPoint {

    public static final String UID_PATH = "/uid";

    public static final String LICENCE_PATH = "/";

    public static final String UPDATE_LICENCE_PATH = "/renew";

    private LicenceService service = PosCoreModule.getInstance().getImplementation(LicenceService.class);

    @GetMapping(UID_PATH)
    public ResponseEntity<Map<String, String>> getUID() {
        return ResponseEntity.ok(Collections.singletonMap("uid", service.getSoftwareUID()));
    }

    @GetMapping(LICENCE_PATH)
    public ResponseEntity<Licence> getLicence(@RequestBody Map<String,Object> paramMap) {
        Licence.getInstance().LICENCIA_ACTIVA = true;
        Licence.getInstance().LICENCIA_VALIDA = true;
        return ResponseEntity.ok(Licence.getInstance());
    }

    @PostMapping(UPDATE_LICENCE_PATH)
    public ResponseEntity<Licence> renewLicence(@RequestBody String key) {
        boolean ret = service.validateAndSafe(key);
        return ResponseEntity.ok(Licence.getInstance());
    }

}
