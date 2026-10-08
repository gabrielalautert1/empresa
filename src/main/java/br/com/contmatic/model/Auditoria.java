package br.com.contmatic.model;

import static br.com.contmatic.model.constantes.AuditoriaConstantes.NOME_CAMPO_DATA_HORA_ALTERACAO;
import static br.com.contmatic.model.constantes.AuditoriaConstantes.NOME_CAMPO_DATA_HORA_CRIACAO;
import static br.com.contmatic.model.constantes.AuditoriaConstantes.NOME_CAMPO_USUARIO_ALTERACAO;
import static br.com.contmatic.model.constantes.AuditoriaConstantes.NOME_CAMPO_USUARIO_CRIACAO;
import static br.com.contmatic.model.constantes.AuditoriaConstantes.TAMANHO_MAX_USUARIO;
import static br.com.contmatic.model.constantes.AuditoriaConstantes.TAMANHO_MIN_USUARIO;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.contmatic.model.util.ValidacaoUtil;

public final class Auditoria {

    private String usuarioCriacao;
    private LocalDateTime dataHoraCriacao;
    private String usuarioAlteracao;
    private LocalDateTime dataHoraAlteracao;

    public Auditoria(String usuarioCriacao, LocalDateTime dataHoraCriacao) {
        setUsuarioCriacao(usuarioCriacao);
        setDataHoraCriacao(dataHoraCriacao);
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    private void setUsuarioCriacao(String usuarioCriacao) {
        ValidacaoUtil.validarTamanho(usuarioCriacao, NOME_CAMPO_USUARIO_CRIACAO, TAMANHO_MIN_USUARIO,
                TAMANHO_MAX_USUARIO);
        this.usuarioCriacao = usuarioCriacao.trim();
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    private void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = ValidacaoUtil.validarObrigatorio(dataHoraCriacao, NOME_CAMPO_DATA_HORA_CRIACAO);
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        ValidacaoUtil.validarTamanho(usuarioAlteracao, NOME_CAMPO_USUARIO_ALTERACAO, TAMANHO_MIN_USUARIO,
                TAMANHO_MAX_USUARIO);
        this.usuarioAlteracao = usuarioAlteracao.trim();
    }

    public LocalDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = ValidacaoUtil.validarObrigatorio(dataHoraAlteracao,
                NOME_CAMPO_DATA_HORA_ALTERACAO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioCriacao, dataHoraCriacao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Auditoria outra = (Auditoria) obj;
        return Objects.equals(usuarioCriacao, outra.usuarioCriacao)
                && Objects.equals(dataHoraCriacao, outra.dataHoraCriacao);
    }

    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder();
        construtor.append("Auditoria [usuarioCriacao=").append(usuarioCriacao)
                .append(", dataHoraCriacao=").append(dataHoraCriacao)
                .append(", usuarioAlteracao=").append(usuarioAlteracao)
                .append(", dataHoraAlteracao=").append(dataHoraAlteracao)
                .append("]");
        return construtor.toString();
    }
}