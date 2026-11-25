package br.com.contmatic.empresa.model;

import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.NOME_CAMPO_CAPITAL_SOCIAL;
import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.NOME_CAMPO_DATA_ABERTURA;
import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.NOME_CAMPO_ENDERECO;
import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.NOME_CAMPO_RAZAO_SOCIAL;
import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.NOME_CAMPO_SITUACAO;
import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.TAMANHO_MAX_RAZAO_SOCIAL;
import static br.com.contmatic.empresa.model.constantes.EmpresaConstantes.TAMANHO_MIN_RAZAO_SOCIAL;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarDataNaoFutura;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarObrigatorio;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarTamanho;
import static br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil.validarValorMaiorQueZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import br.com.contmatic.empresa.model.constantes.ValidacaoConstantes;
import br.com.contmatic.empresa.model.util.CnpjUtil;
import br.com.contmatic.empresa.model.util.ValidacaoPreenchimentoCamposUtil;

public class Empresa extends Auditoria {

    private String cnpj;
    
    private String razaoSocial;
    
    private LocalDate dataAbertura;
    
    private BigDecimal capitalSocial;
    
    private Boolean ativa;
    
    private List<Endereco> enderecos = new ArrayList<>();
    
    private Set<Telefone> telefones = new HashSet<>();

    public Empresa(String cnpj, String razaoSocial, LocalDate dataAbertura, BigDecimal capitalSocial, LocalDateTime dataHoraCriacao, String usuarioCriacao) {
    	super(usuarioCriacao, dataHoraCriacao);
        setCnpj(cnpj);
        setRazaoSocial(razaoSocial);
        setDataAbertura(dataAbertura);
        setCapitalSocial(capitalSocial);
        setAtiva(Boolean.TRUE);
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = CnpjUtil.validar(cnpj);
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = validarTamanho(razaoSocial, NOME_CAMPO_RAZAO_SOCIAL,
                TAMANHO_MIN_RAZAO_SOCIAL, TAMANHO_MAX_RAZAO_SOCIAL);
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = validarDataNaoFutura(dataAbertura, NOME_CAMPO_DATA_ABERTURA);
    }

    public BigDecimal getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(BigDecimal capitalSocial) {
        this.capitalSocial = validarValorMaiorQueZero(capitalSocial, NOME_CAMPO_CAPITAL_SOCIAL);
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = validarObrigatorio(ativa, NOME_CAMPO_SITUACAO);
    }

    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(ValidacaoPreenchimentoCamposUtil.validarObrigatorio(endereco, NOME_CAMPO_ENDERECO));
    }
    
    public Set<Telefone> getTelefones() {
        return Collections.unmodifiableSet(telefones);   
    }

    public void adicionarTelefone(Telefone telefone) {
        ValidacaoPreenchimentoCamposUtil.validarObrigatorio(telefone, ValidacaoConstantes.MENSAGEM_TELEFONE_OBRIGATORIO);
        telefones.add(telefone);
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
        sb.append(", telefones=").append(telefones);
        sb.append(", auditoria=").append(super.toString());
        sb.append("]");
        return sb.toString();
    }
}