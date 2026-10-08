package br.com.contmatic.model.util;

import static br.com.contmatic.model.constantes.ValidacaoConstantes.MSG_CAMPO_OBRIGATORIO;
import static br.com.contmatic.model.constantes.ValidacaoConstantes.MSG_FORMATO_INVALIDO;
import static br.com.contmatic.model.constantes.ValidacaoConstantes.MSG_NUMERO_INVALIDO;
import static br.com.contmatic.model.constantes.ValidacaoConstantes.MSG_TAMANHO_INVALIDO;
import static br.com.contmatic.model.constantes.ValidacaoConstantes.MSG_DATA_FUTURA;
import static br.com.contmatic.model.constantes.ValidacaoConstantes.MSG_VALOR_INVALIDO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import com.google.common.base.Preconditions;

public final class ValidacaoUtil {

    private ValidacaoUtil() {
    }

    public static <T> T validarObrigatorio(T valor, String nomeCampo) {
        Preconditions.checkArgument(valor != null, MSG_CAMPO_OBRIGATORIO, nomeCampo);
        return valor;
    }

    public static String validarTextoObrigatorio(String valor, String nomeCampo) {
        Preconditions.checkArgument(valor != null && !valor.trim().isEmpty(),
                MSG_CAMPO_OBRIGATORIO, nomeCampo);
        return valor;
    }

    public static String validarTamanho(String valor, String nomeCampo, int min, int max) {
        validarTextoObrigatorio(valor, nomeCampo);
        int tamanho = valor.trim().length();
        Preconditions.checkArgument(tamanho >= min && tamanho <= max,
                MSG_TAMANHO_INVALIDO, nomeCampo, min, max);
        return valor;
    }

    public static Integer validarNumeroPositivo(Integer valor, String nomeCampo) {
        validarObrigatorio(valor, nomeCampo);
        Preconditions.checkArgument(valor > 0, MSG_NUMERO_INVALIDO, nomeCampo);
        return valor;
    }

    public static String validarFormato(String valor, String regex, String nomeCampo) {
        validarTextoObrigatorio(valor, nomeCampo);
        Preconditions.checkArgument(valor.trim().matches(regex), MSG_FORMATO_INVALIDO, nomeCampo);
        return valor;
    }
    
    public static LocalDate validarDataNaoFutura(LocalDate data, String nomeCampo) {
        validarObrigatorio(data, nomeCampo);
        Preconditions.checkArgument(!data.isAfter(LocalDate.now(ZoneId.systemDefault())), MSG_DATA_FUTURA, nomeCampo);
        return data;
    }

    public static BigDecimal validarValorMaiorQueZero(BigDecimal valor, String nomeCampo) {
        validarObrigatorio(valor, nomeCampo);
        Preconditions.checkArgument(valor.signum() > 0, MSG_VALOR_INVALIDO, nomeCampo);
        return valor;
    }
}