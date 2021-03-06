/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest;

import com.jobits.pos.core.client.rest.service.CoreUserResolver;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.services.UserResolver;
import org.springframework.stereotype.Component;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@Component
public class CoreRESTClientConfig {

    public static final String BASE_PACKAGE = "com.jobits.pos.core.client.rest";

    static {
        PosCoreModule.init();
        UserResolver.registerUserResolverService(CoreUserResolver.getInstance());
    }

}
