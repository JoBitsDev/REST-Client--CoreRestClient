/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.almacen.IPVEndPoint;
import com.jobits.pos.core.domain.models.Ipv;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class IpvModelAssembler extends CrudModelAssembler<Ipv> {

    public IpvModelAssembler() {
        super(IPVEndPoint.class);
    }

    @Override
    public Object getId(Ipv entity) {
        return entity.getIpvPK();
    }

}