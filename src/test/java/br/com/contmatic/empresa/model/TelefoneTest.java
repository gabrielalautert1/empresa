package br.com.contmatic.empresa.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TelefoneTest {

    private static final Integer DDD = 41;

    private static final String NUMERO = "999998888";

    private static final LocalDateTime DATA_HORA_CRIACAO = LocalDateTime.of(2025, 1, 2, 3, 0);

    private static final String USUARIO_CRIACAO = "gabrielaLautert";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private Telefone telefone;

    @Before
    public void criar_objetos_para_testes() {
        telefone = new Telefone(DDD, NUMERO, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @After
    public void limpar() {
        telefone = null;
    }

    @Test
    public void deve_criar_telefone_com_dados_validos() {
        assertThat(telefone.getDdd(), is(DDD));
        assertThat(telefone.getNumero(), is(NUMERO));
    }

    @Test
    public void nao_deve_aceitar_ddd_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'DDD' é de preenchimento obrigatório");

        telefone.setDdd(null);
    }

    @Test
    public void nao_deve_aceitar_ddd_menor_que_11() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'DDD' deve ser um número entre 11 e 99");

        telefone.setDdd(10);
    }

    @Test
    public void nao_deve_aceitar_ddd_maior_que_99() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'DDD' deve ser um número entre 11 e 99");

        telefone.setDdd(100);
    }

    @Test
    public void nao_deve_aceitar_numero_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' é de preenchimento obrigatório");

        telefone.setNumero(null);
    }

    @Test
    public void nao_deve_aceitar_numero_com_letras() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' deve ter 8 ou 9 números");

        telefone.setNumero("99999ABCD");
    }

    @Test
    public void nao_deve_aceitar_numero_com_menos_de_8_digitos() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' deve ter 8 ou 9 números");

        telefone.setNumero("1234567");
    }

    @Test
    public void nao_deve_aceitar_numero_com_mais_de_9_digitos() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' deve ter 8 ou 9 números");

        telefone.setNumero("1234567890");
    }

    @Test
    public void deve_alterar_ddd() {
        telefone.setDdd(11);

        assertThat(telefone.getDdd(), is(11));
    }

    @Test
    public void deve_alterar_numero() {
        telefone.setNumero("33334444");

        assertThat(telefone.getNumero(), is("33334444"));
    }

    @Test
    public void deve_ser_igual_a_si_mesmo() {
        Telefone mesmo = telefone;

        assertThat(telefone.equals(mesmo), is(true));
    }

    @Test
    public void deve_ser_igual_com_mesmo_ddd_e_numero() {
        assertThat(telefone.equals(new Telefone(DDD, NUMERO, DATA_HORA_CRIACAO, USUARIO_CRIACAO)), is(true));
    }

    @Test
    public void nao_deve_ser_igual_com_ddd_diferente() {
        assertThat(telefone.equals(new Telefone(11, NUMERO, DATA_HORA_CRIACAO, USUARIO_CRIACAO)), is(false));
    }

    @Test
    public void nao_deve_ser_igual_com_numero_diferente() {
        assertThat(telefone.equals(new Telefone(DDD, "33334444", DATA_HORA_CRIACAO, USUARIO_CRIACAO)), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_nulo() {
        assertThat(telefone.equals(null), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_outro_tipo() {
        Object outroTipo = "texto";

        assertThat(telefone.equals(outroTipo), is(false));
    }

    @Test
    public void deve_ter_mesmo_hashcode_com_mesmo_ddd_e_numero() {
        assertThat(telefone.hashCode(), is(new Telefone(DDD, NUMERO, DATA_HORA_CRIACAO, USUARIO_CRIACAO).hashCode()));
    }

    @Test
    public void deve_gerar_to_string_completo() {
        assertThat(telefone.toString(), is("Telefone [ddd=41, numero=999998888]"));
    }

    @Test
    public void nao_deve_aceitar_numero_vazio() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' deve ter 8 ou 9 números");

        telefone.setNumero("");
    }
}