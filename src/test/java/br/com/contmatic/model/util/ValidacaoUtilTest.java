package br.com.contmatic.model.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidacaoUtilTest {

    private static final String MSG_OBRIGATORIO_NUMERO = "O campo 'Numero' é de preenchimento obrigatório";
    private static final String MSG_NUMERO_INVALIDO = "O campo 'Numero' deve ser um número maior que zero";
    private static final String REGEX_CEP = "\\d{5}-?\\d{3}";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    @Test
    public void deve_aceitar_texto_preenchido() {
        assertThat(ValidacaoUtil.validarTextoObrigatorio("Contmatic", "Nome"), is("Contmatic"));
    }

    @Test
    public void nao_deve_aceitar_texto_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Nome' é de preenchimento obrigatório");
        ValidacaoUtil.validarTextoObrigatorio(null, "Nome");
    }

    @Test
    public void nao_deve_aceitar_texto_so_com_espacos() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Nome' é de preenchimento obrigatório");
        ValidacaoUtil.validarTextoObrigatorio("   ", "Nome");
    }

    @Test
    public void nao_deve_aceitar_nome_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Nome' deve ter entre 3 e 50 caracteres");
        ValidacaoUtil.validarTamanho("Ab", "Nome", 3, 50);
    }

    @Test
    public void deve_aceitar_nome_com_tamanho_dentro_do_limite() {
        assertThat(ValidacaoUtil.validarTamanho("Contmatic", "Nome", 3, 50), is("Contmatic"));
    }

    @Test
    public void nao_deve_aceitar_nome_com_mais_de_50_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Nome' deve ter entre 3 e 50 caracteres");
        ValidacaoUtil.validarTamanho("a".repeat(51), "Nome", 3, 50);
    }

    @Test
    public void deve_aceitar_objeto_preenchido() {
        assertThat(ValidacaoUtil.validarObrigatorio(10, "Numero"), is(10));
    }

    @Test
    public void nao_deve_aceitar_objeto_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_OBRIGATORIO_NUMERO);
        ValidacaoUtil.validarObrigatorio(null, "Numero");
    }

    @Test
    public void deve_aceitar_numero_maior_que_zero() {
        assertThat(ValidacaoUtil.validarNumeroPositivo(1, "Numero"), is(1));
    }

    @Test
    public void nao_deve_aceitar_numero_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_OBRIGATORIO_NUMERO);
        ValidacaoUtil.validarNumeroPositivo(null, "Numero");
    }

    @Test
    public void nao_deve_aceitar_numero_zero() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_NUMERO_INVALIDO);
        ValidacaoUtil.validarNumeroPositivo(0, "Numero");
    }

    @Test
    public void nao_deve_aceitar_numero_negativo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_NUMERO_INVALIDO);
        ValidacaoUtil.validarNumeroPositivo(-5, "Numero");
    }

    @Test
    public void deve_aceitar_texto_no_formato_esperado() {
        assertThat(ValidacaoUtil.validarFormato("80010-000", REGEX_CEP, "CEP"), is("80010-000"));
    }

    @Test
    public void nao_deve_aceitar_texto_fora_do_formato_esperado() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'CEP' está inválido. Confira o que foi digitado");
        ValidacaoUtil.validarFormato("8001-0000", REGEX_CEP, "CEP");
    }

    @Test
    public void nao_deve_aceitar_formato_com_texto_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'CEP' é de preenchimento obrigatório");
        ValidacaoUtil.validarFormato(null, REGEX_CEP, "CEP");
    }
}