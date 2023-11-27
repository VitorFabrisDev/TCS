package com.tcs_senac.ruralfacil.model.Enum;

public enum Sazonalidade {

    NENHUM("NENHUM"),
    JANEIRO("JANEIRO"),
    FEVEREIRO("FEVEREIRO"),
    MARCO("MARCO"),
    ABRIL("ABRIL"),
    MAIO("MAIO"),
    JUNHO("JUNHO"),
    JULHO("JULHO"),
    AGOSTO("AGOSTO"),
    SETEMBRO("SETEMBRO"),
    OUTUBRO("OUTUBRO"),
    NOVEMBRO("NOVEMBRO"),
    DEZEMBRO("DEZEMBRO"),
    ;


    private final String sazonalidade;


    Sazonalidade(String sazonalidade) {
        this.sazonalidade = sazonalidade;
    }

    public String getSazonalidade() {
        return sazonalidade;
    }

}
