package br.com.contmatic.model.constantes;

import java.util.List;

public final class CnpjConstantes {

    public static final String NOME_CAMPO_CNPJ = "CNPJ";
    public static final String MSG_CNPJ_INVALIDO = "O campo 'CNPJ' está inválido. Confira os números digitados";
    public static final String REGEX_CNPJ = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}";
    public static final Integer QTD_DIGITOS_BASE = 12;
    public static final Integer DIVISOR = 11;
    public static final List<Integer> PESOS_PRIMEIRO_DIGITO = List.of(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    public static final List<Integer> PESOS_SEGUNDO_DIGITO = List.of(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);

    private CnpjConstantes() {
    }
}