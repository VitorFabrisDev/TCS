package com.tcs_senac.ruralfacil.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;
import com.tcs_senac.ruralfacil.model.QAnuncioSazonalidade;

public class CustomPredicates {

    // Exemplo de constante personalizada para sazonalidade
    public static BooleanExpression sazonalidadeEquals(String month) {
        return QAnuncioSazonalidade.anuncioSazonalidade.sazonalidade.eq(Sazonalidade.valueOf(month));
    }

    // Adicione outras constantes personalizadas conforme necessário
}