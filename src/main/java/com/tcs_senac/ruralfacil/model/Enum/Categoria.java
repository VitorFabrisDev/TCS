package com.tcs_senac.ruralfacil.model.Enum;

public enum Categoria {
    MUDAS_SEMENTES("Mudas/Sementes"),
    HORTALICAS("Hortaliças"),
    GRAOS("Grãos"),
    LATICINIOS("Laticínios"),
    SUINO("Suíno"),
    SUINO_PROCESSADO("Suíno Processado"),
    BOVINO("Bovino"),
    BOVINO_PROCESSADO("Bovino Processado"),
    OVINO("Ovino"),
    OVINO_PROCESSADO("Ovino Processado"),
    AVES_GRANJA("Aves / Granja"),
    PADARIA("Padaria"),
    MASSAS("Massas");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}
