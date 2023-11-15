package com.tcs_senac.ruralfacil.util;


import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CpfValid {

    public static boolean isValid(String cpf) {
        try {
            // Cria uma instância do CPFValidator e valida o CPF
            new CPFValidator().assertValid(cpf);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }
}
