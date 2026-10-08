package br.com.contmatic.model.util;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CpfUtilInvalidoTest {

    private static final String MSG_INVALIDO = "O campo 'CPF' está inválido. Confira os números digitados";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private final String cpf;

    public CpfUtilInvalidoTest(String cpf) {
        this.cpf = cpf;
    }

    @Parameters(name = "{0}")
    public static List<String> cpfsInvalidos() {
        return List.of("5299822472", "5299822472A", "11111111111", "52998224726");
    }

    @Test
    public void nao_deve_aceitar_cpf_invalido() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_INVALIDO);
        CpfUtil.validar(cpf);
    }
}