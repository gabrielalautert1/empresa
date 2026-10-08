package br.com.contmatic.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AuditoriaTest {

    private static final String USUARIO_CRIACAO = "maria.silva";
    private static final LocalDateTime DATA_HORA_CRIACAO = LocalDateTime.of(2026, 10, 8, 14, 30);
    private static final String USUARIO_ALTERACAO = "joao.souza";
    private static final LocalDateTime DATA_HORA_ALTERACAO = LocalDateTime.of(2026, 10, 9, 9, 15);

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private Auditoria auditoria;

    @Before
    public void criarAuditoria() {
        auditoria = new Auditoria(USUARIO_CRIACAO, DATA_HORA_CRIACAO);
    }

    @Test
    public void deve_criar_auditoria_com_dados_de_criacao() {
        assertThat(auditoria.getUsuarioCriacao(), is(USUARIO_CRIACAO));
        assertThat(auditoria.getDataHoraCriacao(), is(DATA_HORA_CRIACAO));
        assertThat(auditoria.getUsuarioAlteracao(), is(nullValue()));
        assertThat(auditoria.getDataHoraAlteracao(), is(nullValue()));
    }

    @Test
    public void nao_deve_aceitar_usuario_de_criacao_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Usuário de criação' é de preenchimento obrigatório");
        new Auditoria(null, DATA_HORA_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_usuario_de_criacao_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Usuário de criação' deve ter entre 3 e 50 caracteres");
        new Auditoria("ab", DATA_HORA_CRIACAO);
    }

    @Test
    public void nao_deve_aceitar_data_hora_de_criacao_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data e hora de criação' é de preenchimento obrigatório");
        new Auditoria(USUARIO_CRIACAO, null);
    }

    @Test
    public void deve_registrar_os_dados_de_alteracao() {
        auditoria.setUsuarioAlteracao(USUARIO_ALTERACAO);
        auditoria.setDataHoraAlteracao(DATA_HORA_ALTERACAO);
        assertThat(auditoria.getUsuarioAlteracao(), is(USUARIO_ALTERACAO));
        assertThat(auditoria.getDataHoraAlteracao(), is(DATA_HORA_ALTERACAO));
    }

    @Test
    public void nao_deve_aceitar_usuario_de_alteracao_nulo() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Usuário de alteração' é de preenchimento obrigatório");
        auditoria.setUsuarioAlteracao(null);
    }

    @Test
    public void nao_deve_aceitar_usuario_de_alteracao_com_menos_de_3_caracteres() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Usuário de alteração' deve ter entre 3 e 50 caracteres");
        auditoria.setUsuarioAlteracao("ab");
    }

    @Test
    public void nao_deve_aceitar_data_hora_de_alteracao_nula() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("O campo 'Data e hora de alteração' é de preenchimento obrigatório");
        auditoria.setDataHoraAlteracao(null);
    }

    @Test
    public void deve_ser_igual_ao_mesmo_objeto() {
        Auditoria mesmaAuditoria = auditoria;
        assertThat(auditoria.equals(mesmaAuditoria), is(true));
    }

    @Test
    public void deve_ser_igual_a_auditoria_com_mesmos_dados_de_criacao() {
        Auditoria outraAuditoria = new Auditoria(USUARIO_CRIACAO, DATA_HORA_CRIACAO);
        outraAuditoria.setUsuarioAlteracao(USUARIO_ALTERACAO);
        assertThat(auditoria.equals(outraAuditoria), is(true));
        assertThat(auditoria.hashCode(), is(outraAuditoria.hashCode()));
    }

    @Test
    public void nao_deve_ser_igual_a_nulo() {
        assertThat(auditoria.equals(null), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_objeto_de_outro_tipo() {
        assertThat(auditoria.equals(new Object()), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_auditoria_com_usuario_de_criacao_diferente() {
        Auditoria outraAuditoria = new Auditoria(USUARIO_ALTERACAO, DATA_HORA_CRIACAO);
        assertThat(auditoria.equals(outraAuditoria), is(false));
    }

    @Test
    public void nao_deve_ser_igual_a_auditoria_com_data_hora_de_criacao_diferente() {
        Auditoria outraAuditoria = new Auditoria(USUARIO_CRIACAO, DATA_HORA_ALTERACAO);
        assertThat(auditoria.equals(outraAuditoria), is(false));
    }

    @Test
    public void deve_gerar_hashcode_diferente_para_auditorias_diferentes() {
        Auditoria outraAuditoria = new Auditoria(USUARIO_ALTERACAO, DATA_HORA_CRIACAO);
        assertThat(auditoria.hashCode(), is(not(outraAuditoria.hashCode())));
    }

    @Test
    public void deve_apresentar_os_dados_no_to_string() {
        assertThat(auditoria.toString(),
                is("Auditoria [usuarioCriacao=maria.silva, dataHoraCriacao=2026-10-08T14:30, "
                        + "usuarioAlteracao=null, dataHoraAlteracao=null]"));
    }
}