/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.persistence.models;

import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.module.PosCoreModule;
import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa: Controllers
 *
 * clase encargada de crear el objeto {@link  VentaResumenModel}
 *
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaResumenController {

    public static VentaResumenModel createResumenFromVenta(Venta v) {
        List<DpteListModel> dptes = getDpteListModel(v);
        List<AreaListModel> areas = getAreaListModel(v);
        List<PuntoElaboracionListModel> ptosElaboracion = getPtosElaboracionListmodel(v);
        return new VentaResumenModel(v, areas, dptes, ptosElaboracion);

    }

    private static List<DpteListModel> getDpteListModel(Venta v) {
        List<DpteListModel> dptes = new ArrayList<>();

        for (Personal p : (List<Personal>) findAll(Personal.class)) {
            DpteListModel auxModel = VentaCalculator.getResumenVentasCamareroOnModel(v, p);
            if (auxModel != null) {
                dptes.add(auxModel);
            }
        }
        return dptes;
    }

    private static List<AreaListModel> getAreaListModel(Venta v) {
        List<AreaListModel> areas = new ArrayList<>();
        for (Area a : (List<Area>) findAll(Area.class)) {
            AreaListModel auxModel = VentaCalculator.getResumenVentaPorAreaOnModel(v, a);
            if (auxModel != null) {
                areas.add(auxModel);
            }
        }
        return areas;
    }

    private static List<PuntoElaboracionListModel> getPtosElaboracionListmodel(Venta v) {
        List<PuntoElaboracionListModel> puntos = new ArrayList<>();
        for (Cocina c : (List<Cocina>) findAll(Cocina.class)) {
            PuntoElaboracionListModel auxModel = VentaCalculator.getResumenVentasCocinaOnTable(v, c);
            if (auxModel != null) {
                puntos.add(auxModel);
            }
        }
        return puntos;

    }

    private static List findAll(Class entityClass) {
        CRUDUseCase service = null;
        if (entityClass == Cocina.class) {
            service = PosCoreModule.getInstance().getImplementation(PuntoElaboracionService.class);
        }
        if (entityClass == Personal.class) {
            service = PosCoreModule.getInstance().getImplementation(PersonalUseCase.class);
        }
        if (entityClass == Area.class) {
            service = PosCoreModule.getInstance().getImplementation(AreaVentaService.class);
        }
        return service != null ?  service.findAll() : new ArrayList();
    }

}
