/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler;

import com.jobits.pos.core.client.rest.endpoint.area.AreaDetailEndPoint;
import com.jobits.pos.core.domain.models.Area;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;

/**
 *
 * @author Home
 */
public class AreaModelAssembler extends CrudModelAssembler<Area> {

    public AreaModelAssembler() {
        super(AreaDetailEndPoint.class);
    }

    @Override
    public Object getId(Area entity) {
        return entity.getCodArea();
    }

}
