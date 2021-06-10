/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.assembler.models;

/**
 *
 * @author Home
 */
public class ConfigurationObjectWrapper {

    private Object value;

    public ConfigurationObjectWrapper(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public boolean getBoolean() {
        return (boolean) value;
    }

    public int getInt() {
        return (int) value;
    }

    public String getString() {
        return (String) value;
    }

}
