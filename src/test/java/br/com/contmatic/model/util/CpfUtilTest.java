package br.com.contmatic.model.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CpfUtilTest {

    private static final String MSG_OBRIGATORIO = "O campo 'CPF' é de preenchimento obrigatório";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    @Test
    public void deve_aceitar_cpf_valido_somente_com_numeros() {
        assertThat(CpfUtil.validar("52998224725"), is("52998224725"));
    }

    @Test
    public void deve_aceitar_cpf_valido_com_pontuacao() {
        assertThat(CpfUtil.validar("529.982.247-25"), is("52998224725"));
    }

    @Test
    public void deve_aceitar_cpf_valido_com_primeiro_digito_verificador_zero() {
        assertThat(CpfUtil.validar("10000000108"), is("10000000108"));
    }

    @Test
    public void deve_aceitar_cpf_valido_com_segundo_digito_verificador_zero() {
        assertThat(CpfUtil.validar("10000000280"), is("10000000280"));
    }

    @Test
    public void nao_deve_aceitar_cpf_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_OBRIGATORIO);
        CpfUtil.validar(null);
    }

    @Test
    public void nao_deve_aceitar_cpf_em_branco() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_OBRIGATORIO);
        CpfUtil.validar("   ");
    }
}