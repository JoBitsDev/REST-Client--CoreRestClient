/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.cliente;

import com.jobits.pos.controller.clientes.ClientesDetailService;
import com.jobits.pos.core.client.rest.assembler.ClienteModelAssembler;
import com.jobits.pos.core.domain.models.Cliente;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/cliente_detail")
public class ClienteDetailEndPoint extends CrudRestServiceTemplate<Cliente> {

    ClienteModelAssembler clienteAssembler = new ClienteModelAssembler();

    @Override
    public CRUDUseCase<Cliente> getUc() {
        return PosCoreModule.getInstance().getImplementation(ClientesDetailService.class);
    }

    @Override
    public CrudModelAssembler<Cliente> getAssembler() {
        return clienteAssembler;
    }

}
