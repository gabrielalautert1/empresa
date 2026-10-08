package br.com.contmatic.model;

import static br.com.contmatic.model.constantes.EmpresaConstantes.NOME_CAMPO_AUDITORIA;
import static br.com.contmatic.model.constantes.EmpresaConstantes.NOME_CAMPO_CAPITAL_SOCIAL;
import static br.com.contmatic.model.constantes.EmpresaConstantes.NOME_CAMPO_DATA_ABERTURA;
import static br.com.contmatic.model.constantes.EmpresaConstantes.NOME_CAMPO_ENDERECO;
import static br.com.contmatic.model.constantes.EmpresaConstantes.NOME_CAMPO_RAZAO_SOCIAL;
import static br.com.contmatic.model.constantes.EmpresaConstantes.NOME_CAMPO_SITUACAO;
import static br.com.contmatic.model.constantes.EmpresaConstantes.TAMANHO_MAX_RAZAO_SOCIAL;
import static br.com.contmatic.model.constantes.EmpresaConstantes.TAMANHO_MIN_RAZAO_SOCIAL;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import br.com.contmatic.model.util.CnpjUtil;
import br.com.contmatic.model.util.ValidacaoUtil;

public final class Empresa {

    private final String cnpj;
    private String razaoSocial;
    private LocalDate dataAbertura;
    private BigDecimal capitalSocial;
    private Boolean ativa;
    private final List<Endereco> enderecos;
    private Auditoria auditoria;

    public Empresa(String cnpj, String razaoSocial, LocalDate dataAbertura, BigDecimal capitalSocial) {
        this.cnpj = CnpjUtil.validar(cnpj);
        setRazaoSocial(razaoSocial);
        setDataAbertura(dataAbertura);
        setCapitalSocial(capitalSocial);
        this.ativa = Boolean.TRUE;
        this.enderecos = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = ValidacaoUtil.validarTamanho(razaoSocial, NOME_CAMPO_RAZAO_SOCIAL,
                TAMANHO_MIN_RAZAO_SOCIAL, TAMANHO_MAX_RAZAO_SOCIAL);
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = ValidacaoUtil.validarDataNaoFutura(dataAbertura, NOME_CAMPO_DATA_ABERTURA);
    }

    public BigDecimal getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(BigDecimal capitalSocial) {
        this.capitalSocial = ValidacaoUtil.validarValorMaiorQueZero(capitalSocial, NOME_CAMPO_CAPITAL_SOCIAL);
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ValidacaoUtil.validarObrigatorio(ativa, NOME_CAMPO_SITUACAO);
    }

    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(ValidacaoUtil.validarObrigatorio(endereco, NOME_CAMPO_ENDERECO));
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = ValidacaoUtil.validarObrigatorio(auditoria, NOME_CAMPO_AUDITORIA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Empresa outra = (Empresa) obj;
        return Objects.equals(cnpj, outra.cnpj);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa [cnpj=").append(cnpj);
        sb.append(", razaoSocial=").append(razaoSocial);
        sb.append(", dataAbertura=").append(dataAbertura);
        sb.append(", capitalSocial=").append(capitalSocial);
        sb.append(", ativa=").append(ativa);
        sb.append(", enderecos=").append(enderecos);
        sb.append(", auditoria=").append(auditoria);
        sb.append("]");
        return sb.toString();
    }
}