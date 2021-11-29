/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.persistence.models;

import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.utils.utils;
import java.util.List;

/**
 * Clase para enviar la informacion en JSON y parsearla en el dispositivo
 * destino
 *
 * @author Jorge
 *
 */
public class VentaResumenModel {

    private final float 
            ventaTotal,
            ventaNeta,
            gastosInsumo,
            gastosSalario,
            autorizos,
            gastosOtros;

    private List<AreaListModel> areas;
    private List<DpteListModel> dptes;
    private List<PuntoElaboracionListModel> ptosElaboracion;

    public VentaResumenModel(Venta v) {
        ventaTotal = utils.setDosLugaresDecimalesFloat(VentaCalculator.getValorTotalVentas(v));
        ventaNeta = VentaCalculator.getValorTotalVentasNeta(v);
        gastosInsumo = utils.setDosLugaresDecimalesFloat(VentaCalculator.getValorTotalGastosInsumo(v));
        gastosSalario = utils.setDosLugaresDecimalesFloat(VentaCalculator.getValorTotalPagoTrabajadores(v));
        autorizos = utils.setDosLugaresDecimalesFloat(VentaCalculator.getValorTotalVentasCasa(v));
        gastosOtros = utils.setDosLugaresDecimalesFloat(VentaCalculator.getValorTotalOtrosGastos(v));

    }

    public VentaResumenModel(Venta v, List<AreaListModel> areas, List<DpteListModel> dptes, List<PuntoElaboracionListModel> ptosElaboracion) {
        this(v);
        this.areas = areas;
        this.dptes = dptes;
        this.ptosElaboracion = ptosElaboracion;
    }

    public float getVentaTotal() {
        return ventaTotal;
    }

    public float getVentaNeta() {
        return ventaNeta;
    }

    public float getGastosInsumo() {
        return gastosInsumo;
    }

    public float getGastosSalario() {
        return gastosSalario;
    }

    public float getAutorizos() {
        return autorizos;
    }

    public float getGastosOtros() {
        return gastosOtros;
    }

    public List<AreaListModel> getAreas() {
        return areas;
    }

    public List<DpteListModel> getDptes() {
        return dptes;
    }

    public List<PuntoElaboracionListModel> getPtosElaboracion() {
        return ptosElaboracion;
    }
}
