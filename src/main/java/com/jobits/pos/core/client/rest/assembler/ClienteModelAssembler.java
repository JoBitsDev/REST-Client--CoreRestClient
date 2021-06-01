/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.cliente.ClienteDetailEndPoint;
import com.jobits.pos.core.domain.models.Cliente;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class ClienteModelAssembler extends CrudModelAssembler<Cliente> {

    public ClienteModelAssembler() {
        super(ClienteDetailEndPoint.class);
    }

    @Override
    public Object getId(Cliente entity) {
        return entity.getIdCliente();
    }

}
