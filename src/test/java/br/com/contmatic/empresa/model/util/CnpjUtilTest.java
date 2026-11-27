package br.com.contmatic.empresa.model.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CnpjUtilTest {

    private static final String MSG_OBRIGATORIO = "O campo 'CNPJ' é de preenchimento obrigatório";

    private static final String MSG_CNPJ_INVALIDO = "O campo 'CNPJ' está inválido. Confira os números digitados";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    @Test
    public void deve_aceitar_cnpj_valido_somente_com_numeros() {
        assertThat(CnpjUtil.validar("11222333000181"), is("11222333000181"));
    }

    @Test
    public void deve_aceitar_cnpj_valido_com_pontuacao() {
        assertThat(CnpjUtil.validar("11.222.333/0001-81"), is("11222333000181"));
    }

    @Test
    public void deve_aceitar_cnpj_valido_com_primeiro_digito_verificador_zero() {
        assertThat(CnpjUtil.validar("10000008000101"), is("10000008000101"));
    }

    @Test
    public void deve_aceitar_cnpj_valido_com_segundo_digito_verificador_zero() {
        assertThat(CnpjUtil.validar("10000001000190"), is("10000001000190"));
    }

    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_OBRIGATORIO);

        CnpjUtil.validar(null);
    }

    @Test
    public void nao_deve_aceitar_cnpj_em_branco() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_OBRIGATORIO);

        CnpjUtil.validar("   ");
    }

    @Test
    public void nao_deve_aceitar_cnpj_com_formato_invalido() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_CNPJ_INVALIDO);

        CnpjUtil.validar("123");
    }

    @Test
    public void nao_deve_aceitar_cnpj_com_todos_os_digitos_iguais() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_CNPJ_INVALIDO);

        CnpjUtil.validar("11111111111111");
    }

    @Test
    public void nao_deve_aceitar_cnpj_com_digitos_verificadores_incorretos() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_CNPJ_INVALIDO);

        CnpjUtil.validar("11222333000180");
    }
}