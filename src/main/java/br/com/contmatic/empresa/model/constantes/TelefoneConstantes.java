package br.com.contmatic.empresa.model.constantes;

public final class TelefoneConstantes {

    public static final int DDD_MINIMO = 11;
    
    public static final int DDD_MAXIMO = 99;
   
    public static final String MENSAGEM_DDD_OBRIGATORIO = "O campo 'DDD' é de preenchimento obrigatório";
    
    public static final String MENSAGEM_DDD_INVALIDO = "O campo 'DDD' deve ser um número entre 11 e 99";
    
    public static final String MENSAGEM_NUMERO_OBRIGATORIO = "O campo 'Número' é de preenchimento obrigatório";
    
    public static final String MENSAGEM_NUMERO_INVALIDO = "O campo 'Número' deve ter 8 ou 9 números";

    private TelefoneConstantes() {
    }
}