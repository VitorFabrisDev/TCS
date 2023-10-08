package com.tcs_senac.ruralfacil.Enum;

public enum CategoriaEnum {
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

    CategoriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    /*public static Categoria fromDescricao(String descricao) {
        EntityManager entityManager = // Obtenha o EntityManager de alguma forma
         List<CategoriaProdutoEntity> categoriaEntities = entityManager.createQuery("SELECT c FROM CategoriaProdutoEntity c", CategoriaProdutoEntity.class).getResultList();

        for (CategoriaProdutoEntity entity : categoriaEntities) {
            if (descricao.equals(entity.getDescricao())) {
                return valueOf(entity.getDescricao()); // Retorna a enumeração correspondente
            }
        }

        throw new IllegalArgumentException("Categoria não encontrada para a descrição: " + descricao);
    }
    }*/
}
