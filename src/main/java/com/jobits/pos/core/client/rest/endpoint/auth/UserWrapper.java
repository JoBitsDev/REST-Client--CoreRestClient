/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.auth;

import com.jobits.pos.core.domain.models.Personal;
import java.util.Objects;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UserWrapper {

    private Personal personal;
    private String tennantToken;
    private String token;

    public UserWrapper(Personal p, String tennantToken, String token) {
        this.personal = p;
        this.tennantToken = tennantToken;
        this.token = token;
    }

    public UserWrapper() {
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getTennantToken() {
        return tennantToken;
    }

    public void setTennantToken(String tennantToken) {
        this.tennantToken = tennantToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.personal);
        hash = 13 * hash + Objects.hashCode(this.tennantToken);
        hash = 13 * hash + Objects.hashCode(this.token);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserWrapper other = (UserWrapper) obj;
        if (!Objects.equals(this.tennantToken, other.tennantToken)) {
            return false;
        }
        if (!Objects.equals(this.personal, other.personal)) {
            return false;
        }
        return true;
    }

}
