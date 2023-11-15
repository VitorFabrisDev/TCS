package com.tcs_senac.ruralfacil.model.Enum;

public enum Sazonalidade {

    NENHUM("0"),
    JANEIRO("01"),
    FEVEREIRO("02"),
    MARCO("03"),
    ABRIL("04"),
    MAIO("05"),
    JUNHO("06"),
    JULHO("07"),
    AGOSTO("08"),
    SETEMBRO("09"),
    OUTUBRO("10"),
    NOVEMBRO("11"),
    DEZEMBRO("12"),
    ;


    private final String sazonalidade;


    Sazonalidade(String sazonalidade) {
        this.sazonalidade = sazonalidade;
    }

    public String getSazonalidade() {
        return sazonalidade;
    }

}
