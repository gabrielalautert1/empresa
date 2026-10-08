package br.com.contmatic.model.constantes;

public final class EnderecoConstantes {

    public static final String NOME_CAMPO_LOGRADOURO = "Logradouro";
    public static final String NOME_CAMPO_NUMERO = "Número";
    public static final String NOME_CAMPO_CEP = "CEP";
    public static final String NOME_CAMPO_CIDADE = "Cidade";
    public static final String REGEX_CEP = "\\d{5}-?\\d{3}";
    public static final Integer TAMANHO_MIN_LOGRADOURO = 3;
    public static final Integer TAMANHO_MAX_LOGRADOURO = 100;
    public static final Integer TAMANHO_MIN_CIDADE = 2;
    public static final Integer TAMANHO_MAX_CIDADE = 60;

    private EnderecoConstantes() {
    }
}