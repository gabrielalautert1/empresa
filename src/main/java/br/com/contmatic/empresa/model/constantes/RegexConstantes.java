package br.com.contmatic.empresa.model.constantes;

public final class RegexConstantes {

    public static final String TELEFONE_NUMERO = "\\d{8,9}";

    public static final String CEP = "\\d{5}-\\d{3}";

    public static final String CPF = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}";

    public static final String CNPJ = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}";

    private RegexConstantes() {
    }
}