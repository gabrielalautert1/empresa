package br.com.contmatic.empresa.model;

import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.NOME_CAMPO_CEP;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.NOME_CAMPO_CIDADE;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.NOME_CAMPO_LOGRADOURO;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.NOME_CAMPO_NUMERO;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.TAMANHO_MAX_CIDADE;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.TAMANHO_MAX_LOGRADOURO;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.TAMANHO_MIN_CIDADE;
import static br.com.contmatic.empresa.model.constantes.EnderecoConstantes.TAMANHO_MIN_LOGRADOURO;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarFormato;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarNumeroPositivo;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarTamanho;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.contmatic.empresa.model.constantes.RegexConstantes;

public class Endereco extends Auditoria {

    private String logradouro;
    
    private Integer numero;
    
    private String cep;
    
    private String cidade;

    public Endereco(String logradouro, Integer numero, String cep, String cidade, LocalDateTime dataHoraCriacao, String usuarioCriacao) {
    	super(usuarioCriacao, dataHoraCriacao);
        setLogradouro(logradouro);
        setNumero(numero);
        setCep(cep);
        setCidade(cidade);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = validarTamanho(logradouro, NOME_CAMPO_LOGRADOURO, TAMANHO_MIN_LOGRADOURO,
                TAMANHO_MAX_LOGRADOURO);
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = validarNumeroPositivo(numero, NOME_CAMPO_NUMERO);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = validarFormato(cep, RegexConstantes.CEP, NOME_CAMPO_CEP).trim();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        validarTamanho(cidade, NOME_CAMPO_CIDADE, TAMANHO_MIN_CIDADE, TAMANHO_MAX_CIDADE);
        this.cidade = cidade.trim();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, numero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Endereco outro = (Endereco) obj;
        return Objects.equals(cep, outro.cep) && Objects.equals(numero, outro.numero);
    }

    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder();
        construtor.append("Endereco [logradouro=").append(logradouro)
                .append(", numero=").append(numero)
                .append(", cep=").append(cep)
                .append(", cidade=").append(cidade)
                .append("]");
        return construtor.toString();
    }
    
}