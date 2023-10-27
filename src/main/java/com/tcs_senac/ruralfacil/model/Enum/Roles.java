package com.tcs_senac.ruralfacil.model.Enum;

public enum Roles {
    ROLE_AGRICULTOR("Agricultor"),
    ROLE_CLIENTE("Cliente"),
    ROLE_ADMIN("Administrador");

    private final String roles;
    Roles (String roles) {this.roles = roles;}

    public String getRoles() {return roles;}
}
