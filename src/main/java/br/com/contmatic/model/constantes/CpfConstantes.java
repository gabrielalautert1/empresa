package br.com.contmatic.model.constantes;

import java.util.List;

public final class CpfConstantes {

    public static final String NOME_CAMPO_CPF = "CPF";
    public static final String MSG_CPF_INVALIDO = "O campo 'CPF' está inválido. Confira os números digitados";
    public static final String REGEX_CPF = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}";
    public static final Integer QTD_DIGITOS_BASE = 9;
    public static final Integer DIVISOR = 11;
    public static final List<Integer> PESOS_PRIMEIRO_DIGITO = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2);
    public static final List<Integer> PESOS_SEGUNDO_DIGITO = List.of(11, 10, 9, 8, 7, 6, 5, 4, 3, 2);

    private CpfConstantes() {
    }
}