package br.com.contmatic.empresa.model;

import static br.com.contmatic.empresa.model.constantes.TelefoneConstantes.DDD_MAXIMO;
import static br.com.contmatic.empresa.model.constantes.TelefoneConstantes.DDD_MINIMO;
import static br.com.contmatic.empresa.model.constantes.TelefoneConstantes.MENSAGEM_DDD_INVALIDO;
import static br.com.contmatic.empresa.model.constantes.TelefoneConstantes.MENSAGEM_NUMERO_INVALIDO;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarFormato;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarNumeroEntre;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.contmatic.empresa.model.constantes.RegexConstantes;

public class Telefone extends Auditoria {

    private Integer ddd;

    private String numero;

    public Telefone(Integer ddd, String numero, LocalDateTime dataHoraCriacao, String usuarioCriacao) {
    	super(usuarioCriacao, dataHoraCriacao);
        setDdd(ddd);
        setNumero(numero);
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = validarNumeroEntre(ddd, DDD_MINIMO, DDD_MAXIMO, "DDD", MENSAGEM_DDD_INVALIDO);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = validarFormato(numero, RegexConstantes.TELEFONE_NUMERO, "Número", MENSAGEM_NUMERO_INVALIDO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, numero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Telefone outro = (Telefone) obj;
        return Objects.equals(ddd, outro.ddd) && Objects.equals(numero, outro.numero);
    }

    @Override
    public String toString() {
        return new StringBuilder("Telefone [")
                .append("ddd=").append(ddd)
                .append(", numero=").append(numero)
                .append("]")
                .toString();
    }
}