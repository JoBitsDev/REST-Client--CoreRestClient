/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobits.pos.core.client.rest.service;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class LastRequestTokenStore {

    private String tenantId;

    public LastRequestTokenStore() {
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void clear() {
        this.tenantId = null;
    }

}
