package br.com.contmatic.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EnderecoTest {

    private static final String LOGRADOURO = "Rua das Flores";
    private static final Integer NUMERO = 100;
    private static final String CEP = "80010-000";
    private static final String CIDADE = "Curitiba";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private Endereco endereco;

    @Before
    public void criarEndereco() {
        endereco = new Endereco(LOGRADOURO, NUMERO, CEP, CIDADE);
    }

    @Test
    public void deve_criar_endereco_com_dados_validos() {
        assertThat(endereco.getLogradouro(), is(LOGRADOURO));
        assertThat(endereco.getNumero(), is(NUMERO));
        assertThat(endereco.getCep(), is(CEP));
        assertThat(endereco.getCidade(), is(CIDADE));
    }

    @Test
    public void nao_deve_aceitar_logradouro_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Logradouro' é de preenchimento obrigatório");
        endereco.setLogradouro(null);
    }

    @Test
    public void nao_deve_aceitar_logradouro_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Logradouro' deve ter entre 3 e 100 caracteres");
        endereco.setLogradouro("Ru");
    }

    @Test
    public void nao_deve_aceitar_numero_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' é de preenchimento obrigatório");
        endereco.setNumero(null);
    }

    @Test
    public void nao_deve_aceitar_numero_zero() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Número' deve ser um número maior que zero");
        endereco.setNumero(0);
    }

    @Test
    public void nao_deve_aceitar_cep_fora_do_formato() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'CEP' está inválido. Confira o que foi digitado");
        endereco.setCep("8001-0000");
    }

    @Test
    public void nao_deve_aceitar_cidade_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Cidade' é de preenchimento obrigatório");
        endereco.setCidade(null);
    }

    @Test
    public void nao_deve_aceitar_cidade_com_menos_de_2_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Cidade' deve ter entre 2 e 60 caracteres");
        endereco.setCidade("A");
    }

    @Test
    public void deve_ser_igual_ao_mesmo_objeto() {
        Endereco mesmoEndereco = endereco;
        assertThat(endereco.equals(mesmoEndereco), is(true));
    }

    @Test
    public void deve_ser_igual_a_endereco_com_mesmo_cep_e_numero() {
        Endereco outroEndereco = new Endereco("Avenida Brasil", NUMERO, CEP, "Londrina");
        assertThat(endereco.equals(outroEndereco), is(true));
        assertThat(endereco.hashCode(), is(outroEndereco.hashCode()));
    }

    @Test
    public void nao_deve_ser_igual_a_nulo() {
        assertThat(endereco.equals(null), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_objeto_de_outro_tipo() {
        assertThat(endereco.equals(new Object()), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_endereco_com_cep_diferente() {
        Endereco outroEndereco = new Endereco(LOGRADOURO, NUMERO, "80010-001", CIDADE);
        assertThat(endereco.equals(outroEndereco), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_endereco_com_numero_diferente() {
        Endereco outroEndereco = new Endereco(LOGRADOURO, 101, CEP, CIDADE);
        assertThat(endereco.equals(outroEndereco), is(false));
    }

    @Test
    public void deve_gerar_hashcode_diferente_para_enderecos_diferentes() {
        Endereco outroEndereco = new Endereco(LOGRADOURO, 101, CEP, CIDADE);
        assertThat(endereco.hashCode(), is(not(outroEndereco.hashCode())));
    }

    @Test
    public void deve_apresentar_os_dados_no_to_string() {
        assertThat(endereco.toString(),
                is("Endereco [logradouro=Rua das Flores, numero=100, cep=80010-000, cidade=Curitiba]"));
    }
}