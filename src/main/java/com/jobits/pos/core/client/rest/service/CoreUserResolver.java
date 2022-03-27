/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.service;

import com.jobits.pos.core.client.rest.endpoint.auth.UserWrapper;
import com.jobits.pos.core.domain.models.Personal;
import com.root101.clean.core.app.services.UserResolverService;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CoreUserResolver implements UserResolverService<Personal> {

    private Map<String, UserWrapper> tokenMap = new HashMap<>();

    @Autowired
    private LastRequestTokenStore requestToken;

    private static CoreUserResolver INSTANCE = new CoreUserResolver();

    private CoreUserResolver() {
    }

    @Override
    public Personal resolveUser() throws RuntimeException {
        var resolved = tokenMap.get(requestToken.getTenantId());
        if (resolved == null) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sesion expirada.");
        }
        return resolved.getPersonal();
    }

    public static CoreUserResolver getInstance() {
        return INSTANCE;
    }

    public void resolveCurrentToken(String token) {
        requestToken.setTenantId(token);
    }

    public void clearToken() {
        requestToken.clear();
    }

    public String addUserAndSetCurrent(Personal newPersonal, String tennantToken) {
        for (Map.Entry<String, UserWrapper> e : tokenMap.entrySet()) {
            if (e.getValue().getTennantToken().equals(tennantToken)) {
                if (e.getValue().getPersonal().equals(newPersonal)) {
                    return e.getKey();
                }
            }
        }
        String ret = generateStringToken();
        tokenMap.put(ret, new UserWrapper(newPersonal, tennantToken, ret));
        return ret;
    }

    private String generateStringToken() {
        Random random = new SecureRandom();
        return new BigInteger(121, random).toString(32);
    }

}
