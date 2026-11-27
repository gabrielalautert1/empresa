package br.com.contmatic.empresa.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {

    private static final String CNPJ = "11222333000181";

    private static final String CNPJ_OUTRO = "11444777000161";

    private static final String RAZAO_SOCIAL = "Contmatic Phoenix";

    private static final LocalDate DATA_ABERTURA = LocalDate.of(2020, 1, 15);

    private static final BigDecimal CAPITAL_SOCIAL = new BigDecimal("1000.50");

    private static final LocalDateTime DATA_HORA_CRIACAO = LocalDateTime.of(2025, 1, 2, 3, 0);

    private static final String USUARIO_CRIACAO = "gabrielaLautert";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private Empresa empresa;

    private Endereco endereco;

    private Telefone telefone;

    @Before
    public void criar_objetos_para_testes() {
        empresa = new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
        endereco = new Endereco("Rua das Flores", 123, "80000-000", "Curitiba", DATA_HORA_CRIACAO, USUARIO_CRIACAO);
        telefone = new Telefone(41, "999998888", DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @After
    public void limpar() {
        empresa = null;
        endereco = null;
        telefone = null;
    }

    @Test
    public void deve_criar_empresa_com_dados_validos() {
        assertThat(empresa.getCnpj(), is(CNPJ));
        assertThat(empresa.getRazaoSocial(), is(RAZAO_SOCIAL));
        assertThat(empresa.getDataAbertura(), is(DATA_ABERTURA));
        assertThat(empresa.getCapitalSocial(), is(CAPITAL_SOCIAL));
        assertThat(empresa.getAtiva(), is(true));
    }

    @Test
    public void nao_deve_aceitar_cnpj_invalido() {
        excecao.expect(IllegalArgumentException.class);

        new Empresa("123", RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        excecao.expect(IllegalArgumentException.class);

        new Empresa(null, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void deve_alterar_cnpj() {
        empresa.setCnpj(CNPJ_OUTRO);

        assertThat(empresa.getCnpj(), is(CNPJ_OUTRO));
    }

    @Test
    public void nao_deve_aceitar_razao_social_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' é de preenchimento obrigatório");

        empresa.setRazaoSocial(null);
    }

    @Test
    public void nao_deve_aceitar_razao_social_vazia() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' é de preenchimento obrigatório");

        empresa.setRazaoSocial("");
    }

    @Test
    public void nao_deve_aceitar_razao_social_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' deve ter entre 3 e 100 caracteres");

        empresa.setRazaoSocial("AB");
    }

    @Test
    public void deve_alterar_razao_social() {
        empresa.setRazaoSocial("Nova Empresa");

        assertThat(empresa.getRazaoSocial(), is("Nova Empresa"));
    }

    @Test
    public void nao_deve_aceitar_data_abertura_futura() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' não pode ser uma data futura");

        empresa.setDataAbertura(LocalDate.now().plusDays(1));
    }

    @Test
    public void nao_deve_aceitar_data_abertura_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' é de preenchimento obrigatório");

        empresa.setDataAbertura(null);
    }

    @Test
    public void deve_alterar_data_abertura() {
        LocalDate novaData = LocalDate.of(2021, 5, 10);

        empresa.setDataAbertura(novaData);

        assertThat(empresa.getDataAbertura(), is(novaData));
    }

    @Test
    public void nao_deve_aceitar_capital_social_zero() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' deve ser um valor maior que zero");

        empresa.setCapitalSocial(BigDecimal.ZERO);
    }

    @Test
    public void nao_deve_aceitar_capital_social_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' é de preenchimento obrigatório");

        empresa.setCapitalSocial(null);
    }

    @Test
    public void deve_alterar_capital_social() {
        BigDecimal novoCapital = new BigDecimal("2000.00");

        empresa.setCapitalSocial(novoCapital);

        assertThat(empresa.getCapitalSocial(), is(novoCapital));
    }

    @Test
    public void nao_deve_aceitar_situacao_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Situação' é de preenchimento obrigatório");

        empresa.setAtiva(null);
    }

    @Test
    public void deve_alterar_situacao() {
        empresa.setAtiva(false);

        assertThat(empresa.getAtiva(), is(false));
    }

    @Test
    public void deve_retornar_lista_de_enderecos_vazia() {
        assertThat(empresa.getEnderecos().isEmpty(), is(true));
    }

    @Test
    public void deve_adicionar_endereco() {
        empresa.adicionarEndereco(endereco);

        assertThat(empresa.getEnderecos().size(), is(1));
        assertThat(empresa.getEnderecos().get(0), is(endereco));
    }

    @Test
    public void nao_deve_adicionar_endereco_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Endereço' é de preenchimento obrigatório");

        empresa.adicionarEndereco(null);
    }

    @Test
    public void nao_deve_permitir_alterar_lista_de_enderecos() {
        excecao.expect(UnsupportedOperationException.class);

        empresa.getEnderecos().add(endereco);
    }

    @Test
    public void deve_retornar_conjunto_de_telefones_vazio() {
        assertThat(empresa.getTelefones().isEmpty(), is(true));
    }

    @Test
    public void deve_adicionar_telefone() {
        empresa.adicionarTelefone(telefone);

        assertThat(empresa.getTelefones().size(), is(1));
        assertThat(empresa.getTelefones().contains(telefone), is(true));
    }

    @Test
    public void nao_deve_adicionar_telefone_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Telefone' é de preenchimento obrigatório");

        empresa.adicionarTelefone(null);
    }

    @Test
    public void nao_deve_permitir_alterar_conjunto_de_telefones() {
        excecao.expect(UnsupportedOperationException.class);

        empresa.getTelefones().add(telefone);
    }

    @Test
    public void deve_ser_igual_a_si_mesma() {
        assertThat(empresa.equals(empresa), is(true));
    }

    @Test
    public void deve_ser_igual_a_empresa_com_mesmo_cnpj() {
        Empresa outraEmpresa = new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);

        assertThat(empresa.equals(outraEmpresa), is(true));
    }

    @Test
    public void nao_deve_ser_igual_a_nulo() {
        assertThat(empresa.equals(null), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_objeto_de_outro_tipo() {
        assertThat(empresa.equals(new Object()), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_empresa_com_cnpj_diferente() {
        Empresa outraEmpresa = new Empresa(CNPJ_OUTRO, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);

        assertThat(empresa.equals(outraEmpresa), is(false));
    }

    @Test
    public void deve_ter_mesmo_hashcode_para_empresas_com_mesmo_cnpj() {
        Empresa outraEmpresa = new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);

        assertThat(empresa.hashCode(), is(outraEmpresa.hashCode()));
    }

    @Test
    public void deve_gerar_to_string_com_dados_da_empresa() {
        String resultado = empresa.toString();

        assertThat(resultado, containsString("cnpj=" + CNPJ));
        assertThat(resultado, containsString("razaoSocial=" + RAZAO_SOCIAL));
        assertThat(resultado, containsString("dataAbertura=" + DATA_ABERTURA));
        assertThat(resultado, containsString("capitalSocial=" + CAPITAL_SOCIAL));
        assertThat(resultado, containsString("ativa=true"));
        assertThat(resultado, containsString("enderecos=[]"));
        assertThat(resultado, containsString("telefones=[]"));
        assertThat(resultado, containsString("auditoria="));
    }
}