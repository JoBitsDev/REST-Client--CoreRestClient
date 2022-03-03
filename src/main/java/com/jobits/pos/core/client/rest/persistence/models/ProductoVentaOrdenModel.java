/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.persistence.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoVentaOrdenModel {

    private int id;
    private float enviadosACocina;
    private float cantidad;
    private ProductoVentaModel productoVenta;
    private int numeroComensal;
    private String codOrden;
    private String codMesa;
    private String usuario;
    private String nota;

    public ProductoVentaOrdenModel(float enviadosACocina,
            int id, float cantidad,
            ProductoVentaModel productoVenta, int numeroComensal,
            String nota) {
        this.enviadosACocina = enviadosACocina;
        this.cantidad = cantidad;
        this.productoVenta = productoVenta;
        this.numeroComensal = numeroComensal;
        this.nota = nota;
        this.id = id;
    }

    public ProductoVentaOrdenModel(float enviadosACocina,
            int id, float cantidad,
            ProductoVentaModel productoVenta, int numeroComensal,
            String nota,
            String codOrden,
            String codMesa,
            String usuario) {
        this.enviadosACocina = enviadosACocina;
        this.cantidad = cantidad;
        this.productoVenta = productoVenta;
        this.numeroComensal = numeroComensal;
        this.nota = nota;
        this.id = id;
        this.codMesa = codMesa;
        this.codOrden = codOrden;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getEnviadosACocina() {
        return enviadosACocina;
    }

    public void setEnviadosACocina(float enviadosACocina) {
        this.enviadosACocina = enviadosACocina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoVentaModel getProductoVenta() {
        return productoVenta;
    }

    public void setProductoVenta(ProductoVentaModel productoVenta) {
        this.productoVenta = productoVenta;
    }

    public int getNumeroComensal() {
        return numeroComensal;
    }

    public void setNumeroComensal(int numeroComensal) {
        this.numeroComensal = numeroComensal;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
    }

    public String getCodMesa() {
        return codMesa;
    }

    public void setCodMesa(String codMesa) {
        this.codMesa = codMesa;
    }

}
