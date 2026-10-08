package br.com.contmatic.model.util;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CnpjUtilInvalidoTest {

    private static final String MSG_INVALIDO = "O campo 'CNPJ' está inválido. Confira os números digitados";

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    private final String cnpj;

    public CnpjUtilInvalidoTest(String cnpj) {
        this.cnpj = cnpj;
    }

    @Parameters(name = "{0}")
    public static List<String> cnpjsInvalidos() {
        return List.of("1122233300018", "1122233300018A", "00000000000000", "11222333000182");
    }

    @Test
    public void nao_deve_aceitar_cnpj_invalido() {
        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage(MSG_INVALIDO);
        CnpjUtil.validar(cnpj);
    }
}