/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobits.pos.core.client.rest.persistence.models.ProductoVentaModel;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeccionModel implements Serializable, Comparable<SeccionModel> {

    private static final long serialVersionUID = 1L;
    private String nombreSeccion;
    private String descripcion;

    private List<ProductoVentaModel> productos = new ArrayList<ProductoVentaModel>();

    public SeccionModel() {
    }

    public SeccionModel(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public SeccionModel(String nombreSeccion, String cartacodCarta) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoVentaModel> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoVentaModel> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreSeccion != null ? nombreSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SeccionModel)) {
            return false;
        }
        SeccionModel other = (SeccionModel) object;
        if ((this.nombreSeccion == null && other.nombreSeccion != null) || (this.nombreSeccion != null && !this.nombreSeccion.equals(other.nombreSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.services.objetos.SeccionModel[ nombreSeccion=" + nombreSeccion + " ]";
    }

    @Override
    public int compareTo(SeccionModel another) {
        return nombreSeccion.compareTo(another.nombreSeccion);
    }

    public void addProducto(ProductoVentaModel x) {
        if (!productos.contains(x)) {
            productos.add(x);
        }
    }

    public static SeccionModel of(Seccion s) {
        SeccionModel ret = new SeccionModel();
        ret.setNombreSeccion(s.getNombreSeccion());
        ret.setDescripcion(s.getDescripcion());
        List<ProductoVentaModel> pVenta = new ArrayList<>();
        for (ProductoVenta p : s.getProductoVentaList()) {
            pVenta.add(ProductoVentaModel.of(p));
        }
        ret.setProductos(pVenta);
        return ret;
    }
}
