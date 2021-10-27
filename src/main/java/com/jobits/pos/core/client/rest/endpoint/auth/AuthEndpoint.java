/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.auth;

import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.controller.login.impl.LogInController;
import com.jobits.pos.core.client.rest.service.CoreUserResolver;
import com.jobits.pos.core.module.PosCoreModule;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import org.jobits.pos.client.rest.endpoint.DefaultEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/auth")
public class AuthEndpoint extends DefaultEndpoint {

    private final String HEADER_BASIC = "Basic";

    @GetMapping(path = "/basic")
    public Map<String,String> create(@RequestHeader("Tennant") String tennantToken,
            @RequestHeader("Authorization") String base64Credentials) {
        if (base64Credentials.contains(HEADER_BASIC)) {
            String ret = new String(Base64.getDecoder().decode(base64Credentials.substring(6)));
            String[] credentials = ret.split(":");
            if (credentials.length == 2) {
                LogInService service = PosCoreModule.getInstance().getImplementation(LogInService.class);
                if (service.autenticar(credentials[0], credentials[1].toCharArray())) {
                    return Collections.singletonMap("Token",CoreUserResolver.addUserAndSetCurrent(service.getUsuarioConectado(),tennantToken));

                }
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

}
