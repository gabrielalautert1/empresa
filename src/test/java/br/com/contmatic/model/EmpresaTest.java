package br.com.contmatic.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

    private static Auditoria auditoria;

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private Empresa empresa;
    private Endereco endereco;

    @BeforeClass
    public static void prepararClasse() {
        auditoria = new Auditoria("maria.silva", LocalDateTime.of(2026, 10, 8, 14, 30));
    }

    @AfterClass
    public static void finalizarClasse() {
        auditoria = null;
    }

    @Before
    public void preparar() {
        empresa = new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL);
        endereco = new Endereco("Rua das Flores", 123, "80000-000", "Curitiba");
    }

    @After
    public void limpar() {
        empresa = null;
        endereco = null;
    }

    @Test
    public void deve_criar_empresa_com_dados_validos() {
        assertThat(empresa.getCnpj(), is(CNPJ));
        assertThat(empresa.getRazaoSocial(), is(RAZAO_SOCIAL));
        assertThat(empresa.getDataAbertura(), is(DATA_ABERTURA));
        assertThat(empresa.getCapitalSocial(), is(CAPITAL_SOCIAL));
    }

    @Test
    public void deve_iniciar_ativa() {
        assertThat(empresa.getAtiva(), is(Boolean.TRUE));
    }

    @Test
    public void deve_iniciar_sem_enderecos() {
        assertThat(empresa.getEnderecos().isEmpty(), is(true));
    }

    @Test
    public void nao_deve_aceitar_cnpj_invalido() {
        excecao.expect(IllegalArgumentException.class);
        new Empresa("11222333000182", RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL);
    }

    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        excecao.expect(IllegalArgumentException.class);
        new Empresa(null, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL);
    }

    @Test
    public void nao_deve_aceitar_razao_social_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' é de preenchimento obrigatório");
        new Empresa(CNPJ, null, DATA_ABERTURA, CAPITAL_SOCIAL);
    }

    @Test
    public void nao_deve_aceitar_razao_social_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' deve ter entre 3 e 100 caracteres");
        new Empresa(CNPJ, "ab", DATA_ABERTURA, CAPITAL_SOCIAL);
    }

    @Test
    public void nao_deve_aceitar_data_abertura_futura() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' não pode ser uma data futura");
        new Empresa(CNPJ, RAZAO_SOCIAL, LocalDate.now().plusDays(1), CAPITAL_SOCIAL);
    }

    @Test
    public void nao_deve_aceitar_data_abertura_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' é de preenchimento obrigatório");
        new Empresa(CNPJ, RAZAO_SOCIAL, null, CAPITAL_SOCIAL);
    }

    @Test
    public void nao_deve_aceitar_capital_social_zero() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' deve ser um valor maior que zero");
        new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, BigDecimal.ZERO);
    }

    @Test
    public void nao_deve_aceitar_capital_social_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' é de preenchimento obrigatório");
        new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, null);
    }

    @Test
    public void deve_alterar_razao_social() {
        empresa.setRazaoSocial("Nova Razao");
        assertThat(empresa.getRazaoSocial(), is("Nova Razao"));
    }

    @Test
    public void deve_alterar_data_abertura() {
        LocalDate nova = LocalDate.of(2019, 5, 10);
        empresa.setDataAbertura(nova);
        assertThat(empresa.getDataAbertura(), is(nova));
    }

    @Test
    public void deve_alterar_capital_social() {
        BigDecimal novo = new BigDecimal("5000");
        empresa.setCapitalSocial(novo);
        assertThat(empresa.getCapitalSocial(), is(novo));
    }

    @Test
    public void deve_alterar_situacao() {
        empresa.setAtiva(Boolean.FALSE);
        assertThat(empresa.getAtiva(), is(Boolean.FALSE));
    }

    @Test
    public void nao_deve_aceitar_situacao_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Situação' é de preenchimento obrigatório");
        empresa.setAtiva(null);
    }

    @Test
    public void deve_adicionar_endereco() {
        empresa.adicionarEndereco(endereco);
        assertThat(empresa.getEnderecos().size(), is(1));
        assertThat(empresa.getEnderecos().get(0), is(endereco));
    }

    @Test
    public void nao_deve_aceitar_endereco_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Endereço' é de preenchimento obrigatório");
        empresa.adicionarEndereco(null);
    }

    @Test
    public void nao_deve_permitir_alterar_a_lista_de_enderecos_pelo_getter() {
        excecao.expect(UnsupportedOperationException.class);
        empresa.getEnderecos().add(endereco);
    }

    @Test
    public void deve_registrar_auditoria() {
        assertThat(empresa.getAuditoria(), is(nullValue()));
        empresa.setAuditoria(auditoria);
        assertThat(empresa.getAuditoria(), is(auditoria));
    }

    @Test
    public void nao_deve_aceitar_auditoria_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Auditoria' é de preenchimento obrigatório");
        empresa.setAuditoria(null);
    }

    @Test
    public void deve_ser_igual_a_si_mesma() {
        Empresa mesma = empresa;
        assertThat(empresa.equals(mesma), is(true));
    }

    @Test
    public void deve_ser_igual_com_mesmo_cnpj() {
        Empresa outra = new Empresa(CNPJ, "Outra Razao", DATA_ABERTURA, CAPITAL_SOCIAL);
        assertThat(empresa.equals(outra), is(true));
    }

    @Test
    public void nao_deve_ser_igual_com_cnpj_diferente() {
        Empresa outra = new Empresa(CNPJ_OUTRO, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL);
        assertThat(empresa.equals(outra), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_nulo() {
        assertThat(empresa.equals(null), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_outro_tipo() {
        Object outroTipo = "texto";
        assertThat(empresa.equals(outroTipo), is(false));
    }

    @Test
    public void deve_ter_mesmo_hashcode_com_mesmo_cnpj() {
        Empresa outra = new Empresa(CNPJ, "Outra Razao", DATA_ABERTURA, CAPITAL_SOCIAL);
        assertThat(empresa.hashCode(), is(outra.hashCode()));
    }

    @Test
    public void deve_gerar_to_string_completo() {
        String esperado = "Empresa [cnpj=11222333000181, razaoSocial=Contmatic Phoenix, "
                + "dataAbertura=2020-01-15, capitalSocial=1000.50, ativa=true, enderecos=[], auditoria=null]";
        assertThat(empresa.toString(), is(esperado));
    }
    

    @Test(timeout = 1000)
    public void deve_criar_empresa_dentro_do_tempo_limite() {
        Empresa nova = new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL);
        assertThat(nova.getCnpj(), is(CNPJ));
    }

    @Ignore("Regra de endereço duplicado ainda não foi definida")
    @Test
    public void nao_deve_aceitar_endereco_duplicado() {
        excecao.expect(IllegalArgumentException.class);
        empresa.adicionarEndereco(endereco);
        empresa.adicionarEndereco(endereco);
    }
}