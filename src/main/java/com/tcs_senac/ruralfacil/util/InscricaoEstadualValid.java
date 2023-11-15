package com.tcs_senac.ruralfacil.util;


import com.tcs_senac.ruralfacil.exception.ValidationException;

public class InscricaoEstadualValid {

    public static boolean validaIESantaCatarina(String ie) {
        if (ie.length() != 9) {
            return false;
        }

        int soma = 0;
        int peso = 9;
        int d = -1;

        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Character.getNumericValue(ie.charAt(i)) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d = 0;
        }

        return ie.endsWith(String.valueOf(d));
    }
}
