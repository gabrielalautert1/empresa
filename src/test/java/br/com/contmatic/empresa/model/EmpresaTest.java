package br.com.contmatic.empresa.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
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
    
    private static final LocalDateTime DATA_HORA_CRIACAO =
            LocalDateTime.of(2025, 1, 2, 3, 0);

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
        new Empresa("11222333000182", RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        excecao.expect(IllegalArgumentException.class);
        new Empresa(null, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }
    
    @Test
    public void nao_deve_aceitar_cnpj_vazio() {
        excecao.expect(IllegalArgumentException.class);
        new Empresa("", RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_razao_social_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' é de preenchimento obrigatório");
        new Empresa(CNPJ, null, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }
    
    @Test
    public void nao_deve_aceitar_razao_social_vazia() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' é de preenchimento obrigatório");
        new Empresa(CNPJ, "", DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_razao_social_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Razão social' deve ter entre 3 e 100 caracteres");
        new Empresa(CNPJ, "ab", DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_data_abertura_futura() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' não pode ser uma data futura");
        new Empresa(CNPJ, RAZAO_SOCIAL, LocalDate.now().plusDays(1), CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test

    public void nao_deve_aceitar_data_abertura_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data de abertura' é de preenchimento obrigatório");
        new Empresa(CNPJ, RAZAO_SOCIAL, null, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_capital_social_zero() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' deve ser um valor maior que zero");
        new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, BigDecimal.ZERO, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_capital_social_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Capital social' é de preenchimento obrigatório");
        new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, null, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
    }
    
    @Test
    public void deve_alterar_cnpj() {
        empresa.setCnpj(CNPJ_OUTRO);
        assertThat(empresa.getCnpj(), is(CNPJ_OUTRO));
    }

    @Test
    public void nao_deve_alterar_para_cnpj_invalido() {
        excecao.expect(IllegalArgumentException.class);
        empresa.setCnpj("11222333000182");
    }

    @Test
    public void nao_deve_alterar_para_cnpj_nulo() {
        excecao.expect(IllegalArgumentException.class);
        empresa.setCnpj(null);
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
    public void deve_ser_igual_a_si_mesma() {
        Empresa mesma = empresa;
        assertThat(empresa.equals(mesma), is(true));
    }

    @Test
    public void deve_ser_igual_com_mesmo_cnpj() {
        Empresa outra = new Empresa(CNPJ, "Outra Razao", DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
        assertThat(empresa.equals(outra), is(true));
    }

    @Test
    public void nao_deve_ser_igual_com_cnpj_diferente() {
        Empresa outra = new Empresa(CNPJ_OUTRO, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
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
        Empresa outra = new Empresa(CNPJ, "Outra Razao", DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
        assertThat(empresa.hashCode(), is(outra.hashCode()));
    }

    @Test
    public void deve_gerar_to_string_completo() {
    	String esperado = "Empresa [cnpj=11222333000181, razaoSocial=Contmatic Phoenix, "
    	        + "dataAbertura=2020-01-15, capitalSocial=1000.50, ativa=true, "
    	        + "enderecos=[], telefones=[], auditoria=Auditoria [usuarioCriacao=gabrielaLautert, "
    	        + "dataHoraCriacao=2025-01-02T03:00, usuarioAlteracao=null, dataHoraAlteracao=null]]";
        assertThat(empresa.toString(), is(esperado));
    }
    

    @Test(timeout = 1000)
    public void deve_criar_empresa_dentro_do_tempo_limite() {
        Empresa nova = new Empresa(CNPJ, RAZAO_SOCIAL, DATA_ABERTURA, CAPITAL_SOCIAL, DATA_HORA_CRIACAO, USUARIO_CRIACAO);
        assertThat(nova.getCnpj(), is(CNPJ));
    }

    @Ignore("Regra de endereço duplicado ainda não foi definida")
    @Test
    public void nao_deve_aceitar_endereco_duplicado() {
        excecao.expect(IllegalArgumentException.class);
        empresa.adicionarEndereco(endereco);
        empresa.adicionarEndereco(endereco);
    }
    
    @Test
    public void deve_iniciar_sem_telefones() {
        assertThat(empresa.getTelefones().isEmpty(), is(true));
    }

    @Test
    public void deve_adicionar_telefone() {
        empresa.adicionarTelefone(telefone);
        assertThat(empresa.getTelefones().size(), is(1));
        assertThat(empresa.getTelefones().contains(telefone), is(true));
    }

    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Telefone' é de preenchimento obrigatório");
        empresa.adicionarTelefone(null);
    }

    @Test
    public void nao_deve_duplicar_telefone() {
        empresa.adicionarTelefone(telefone);
        empresa.adicionarTelefone(new Telefone(41, "999998888", DATA_HORA_CRIACAO, USUARIO_CRIACAO));
        assertThat(empresa.getTelefones().size(), is(1));
    }

    @Test
    public void nao_deve_permitir_alterar_o_conjunto_de_telefones_pelo_getter() {
        excecao.expect(UnsupportedOperationException.class);
        empresa.getTelefones().add(telefone);        
    }
    
    @Test
    public void deve_receber_dados_de_auditoria_ao_ser_criado() {
        assertThat(empresa.getUsuarioCriacao(), is(USUARIO_CRIACAO));
        assertThat(empresa.getDataHoraCriacao(), is(DATA_HORA_CRIACAO));
    }
}