/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.trabajador;

import com.jobits.pos.controller.trabajadores.AsistenciaPersonalService;
import com.jobits.pos.core.client.rest.assembler.AsistenciaPersonalModelAssembler;
import com.jobits.pos.core.client.rest.endpoint.almacen.AlmacenManageEndPoint;
import static com.jobits.pos.core.client.rest.endpoint.almacen.AlmacenManageEndPoint.CREAR_OPERACION_ENTRADA_PATH;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.InsumoAlmacen;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.List;
import org.jobits.pos.client.rest.assembler.CrudModelAssembler;
import org.jobits.pos.client.rest.endpoint.CrudRestServiceTemplate;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "/asistencia_personal")
public class AsistenciaPersonalEndPoint extends CrudRestServiceTemplate<AsistenciaPersonal> {

    public static final String IMPRIMIR_ASISTENCIA_PATH = "/imprimir_asistencia";
    public static final RequestMethod IMPRIMIR_ASISTENCIA_METHOD = RequestMethod.POST;

    public static final String UPDATE_A_MAYORES_PATH = "/find_insumo";
    public static final RequestMethod UPDATE_A_MAYORES_METHOD = RequestMethod.PUT;

    public static final String GET_PERSONAL_TRABAJANDO_PATH = "/get_personal_trabajando";
    public static final RequestMethod GET_PERSONAL_TRABAJANDO_METHOD = RequestMethod.GET;

    public static final String UPDATE_SALARIES_PATH = "/update_salaries";
    public static final RequestMethod UPDATE_SALARIES_METHOD = RequestMethod.PUT;

    AsistenciaPersonalModelAssembler asistenciaPersonalAssembler = new AsistenciaPersonalModelAssembler();

    @Override
    public AsistenciaPersonalService getUc() {
        return PosCoreModule.getInstance().getImplementation(AsistenciaPersonalService.class);
    }

    @Override
    public CrudModelAssembler<AsistenciaPersonal> getAssembler() {
        return asistenciaPersonalAssembler;
    }

    @PostMapping(IMPRIMIR_ASISTENCIA_PATH)
    public boolean imprimirAsistencia(@RequestBody Venta venta) {
        getUc().imprimirAsistencia(venta.getId());
        return true;
    }

    @PutMapping(UPDATE_A_MAYORES_PATH)
    public boolean updateAMayores(@RequestBody AsistenciaPersonal personalABuscar, @RequestParam float aMayoresValor) {
        getUc().updateAMayores(personalABuscar, aMayoresValor);
        return true;
    }

    @GetMapping(GET_PERSONAL_TRABAJANDO_PATH)
    CollectionModel<EntityModel<AsistenciaPersonal>> getPersonalTrabajando(@RequestBody Venta v) {
        CollectionModel<EntityModel<AsistenciaPersonal>> entityModel
                = asistenciaPersonalAssembler.toCollectionModel(getUc().getPersonalTrabajando(v.getId()));
        entityModel.add(linkTo(methodOn(AsistenciaPersonalEndPoint.class).getPersonalTrabajando(v)).withRel("get_personal_trabajando"));
        return entityModel;
    }

    @PutMapping(UPDATE_SALARIES_PATH)
    CollectionModel<EntityModel<AsistenciaPersonal>> updateSalaries(@RequestBody Venta venta) {
        CollectionModel<EntityModel<AsistenciaPersonal>> entityModel
                = asistenciaPersonalAssembler.toCollectionModel(getUc().updateSalaries(venta.getId()));
        entityModel.add(linkTo(methodOn(AsistenciaPersonalEndPoint.class).updateSalaries(venta)).withRel("update_salaries"));
        return entityModel;
    }
}
