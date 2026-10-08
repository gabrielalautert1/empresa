package br.com.contmatic.model.util;

import static br.com.contmatic.model.constantes.CpfConstantes.DIVISOR;
import static br.com.contmatic.model.constantes.CpfConstantes.MSG_CPF_INVALIDO;
import static br.com.contmatic.model.constantes.CpfConstantes.NOME_CAMPO_CPF;
import static br.com.contmatic.model.constantes.CpfConstantes.PESOS_PRIMEIRO_DIGITO;
import static br.com.contmatic.model.constantes.CpfConstantes.PESOS_SEGUNDO_DIGITO;
import static br.com.contmatic.model.constantes.CpfConstantes.QTD_DIGITOS_BASE;
import static br.com.contmatic.model.constantes.CpfConstantes.REGEX_CPF;

import java.util.List;

import com.google.common.base.Preconditions;

public final class CpfUtil {

    private CpfUtil() {
    }

    public static String validar(String cpf) {
        ValidacaoUtil.validarTextoObrigatorio(cpf, NOME_CAMPO_CPF);
        Preconditions.checkArgument(cpf.trim().matches(REGEX_CPF), MSG_CPF_INVALIDO);
        String numeros = cpf.replaceAll("\\D", "");
        Preconditions.checkArgument(!todosDigitosIguais(numeros) && digitosVerificadoresCorretos(numeros),
                MSG_CPF_INVALIDO);
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