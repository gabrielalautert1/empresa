package br.com.contmatic.model.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidacaoUtilDataValorTest {

    private static final String CAMPO_DATA = "Data de abertura";
    private static final String CAMPO_VALOR = "Capital social";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    @Test
    public void deve_aceitar_data_de_hoje() {
        LocalDate hoje = LocalDate.now();
        assertThat(ValidacaoUtil.validarDataNaoFutura(hoje, CAMPO_DATA), is(hoje));
    }

    @Test
    public void deve_aceitar_data_passada() {
        LocalDate passada = LocalDate.of(2020, 1, 15);
        assertThat(ValidacaoUtil.validarDataNaoFutura(passada, CAMPO_DATA), is(passada));
    }

    @Test
    public void nao_deve_aceitar_data_futura() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' não pode ser uma data futura");
        ValidacaoUtil.validarDataNaoFutura(LocalDate.now().plusDays(1), CAMPO_DATA);
    }

    @Test
    public void nao_deve_aceitar_data_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' é de preenchimento obrigatório");
        ValidacaoUtil.validarDataNaoFutura(null, CAMPO_DATA);
    }

    @Test
    public void deve_aceitar_valor_maior_que_zero() {
        BigDecimal valor = new BigDecimal("1000.50");
        assertThat(ValidacaoUtil.validarValorMaiorQueZero(valor, CAMPO_VALOR), is(valor));
    }

    @Test
    public void nao_deve_aceitar_valor_zero() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' deve ser um valor maior que zero");
        ValidacaoUtil.validarValorMaiorQueZero(BigDecimal.ZERO, CAMPO_VALOR);
    }

    @Test
    public void nao_deve_aceitar_valor_negativo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' deve ser um valor maior que zero");
        ValidacaoUtil.validarValorMaiorQueZero(new BigDecimal("-1"), CAMPO_VALOR);
    }

    @Test
    public void nao_deve_aceitar_valor_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' é de preenchimento obrigatório");
        ValidacaoUtil.validarValorMaiorQueZero(null, CAMPO_VALOR);
    }
}