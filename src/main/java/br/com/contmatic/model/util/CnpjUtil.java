package br.com.contmatic.model.util;

import static br.com.contmatic.model.constantes.CnpjConstantes.DIVISOR;
import static br.com.contmatic.model.constantes.CnpjConstantes.MSG_CNPJ_INVALIDO;
import static br.com.contmatic.model.constantes.CnpjConstantes.NOME_CAMPO_CNPJ;
import static br.com.contmatic.model.constantes.CnpjConstantes.PESOS_PRIMEIRO_DIGITO;
import static br.com.contmatic.model.constantes.CnpjConstantes.PESOS_SEGUNDO_DIGITO;
import static br.com.contmatic.model.constantes.CnpjConstantes.QTD_DIGITOS_BASE;
import static br.com.contmatic.model.constantes.CnpjConstantes.REGEX_CNPJ;

import java.util.List;

import com.google.common.base.Preconditions;

public final class CnpjUtil {

    private CnpjUtil() {
    }

    public static String validar(String cnpj) {
        ValidacaoUtil.validarTextoObrigatorio(cnpj, NOME_CAMPO_CNPJ);
        Preconditions.checkArgument(cnpj.trim().matches(REGEX_CNPJ), MSG_CNPJ_INVALIDO);
        String numeros = cnpj.replaceAll("\\D", "");
        Preconditions.checkArgument(!todosDigitosIguais(numeros) && digitosVerificadoresCorretos(numeros),
                MSG_CNPJ_INVALIDO);
        return numeros;
    }

    private static boolean todosDigitosIguais(String numeros) {
        return numeros.chars().distinct().count() == 1;
    }

    private static boolean digitosVerificadoresCorretos(String numeros) {
        String base = numeros.substring(0, QTD_DIGITOS_BASE);
        int primeiroDigito = calcularDigito(base, PESOS_PRIMEIRO_DIGITO);
        int segundoDigito = calcularDigito(base + primeiroDigito, PESOS_SEGUNDO_DIGITO);
        return numeros.equals(base + primeiroDigito + segundoDigito);
    }

    private static int calcularDigito(String base, List<Integer> pesos) {
        int soma = 0;
        for (int i = 0; i < base.length(); i++) {
            soma += Character.getNumericValue(base.charAt(i)) * pesos.get(i);
        }
        int resto = soma % DIVISOR;
        return resto < 2 ? 0 : DIVISOR - resto;
    }
}