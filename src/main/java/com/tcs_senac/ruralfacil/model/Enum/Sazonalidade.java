package com.tcs_senac.ruralfacil.model.Enum;

public enum Sazonalidade {

    JANEIRO("Janeiro"),
    FEVEREIRO("Fevereir0"),
    MARÇO("Março"),
    ABRIL("Abril"),
    MAIO("Maio"),
    JUNHO("Junho"),
    JULHO("Julho"),
    AGOSTO("Agosto"),
    SETEMBRO("Setembro"),
    OUTUBRO("Outubro"),
    NOVEMBRO("Novembro"),
    DEZEMBRO("Dezembro"),
    ;


    private final String sazonalidade;


    Sazonalidade(String sazonalidade) {
        this.sazonalidade = sazonalidade;
    }

    public String getSazonalidade() {
        return sazonalidade;
    }

}
