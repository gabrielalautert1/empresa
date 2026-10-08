package br.com.contmatic.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import br.com.contmatic.model.util.CnpjUtilInvalidoTest;
import br.com.contmatic.model.util.CnpjUtilTest;
import br.com.contmatic.model.util.CpfUtilInvalidoTest;
import br.com.contmatic.model.util.CpfUtilTest;
import br.com.contmatic.model.util.ValidacaoUtilDataValorTest;
import br.com.contmatic.model.util.ValidacaoUtilTest;

public final class TestRunner {

    private static final Logger LOGGER = Logger.getLogger(TestRunner.class.getName());

    private TestRunner() {
    }

    public static void main(String[] args) {
        Result resultado = JUnitCore.runClasses(
                ValidacaoUtilTest.class,
                ValidacaoUtilDataValorTest.class,
                CnpjUtilTest.class,
                CnpjUtilInvalidoTest.class,
                CpfUtilTest.class,
                CpfUtilInvalidoTest.class,
                EnderecoTest.class,
                AuditoriaTest.class,
                EmpresaTest.class);

        for (Failure falha : resultado.getFailures()) {
            LOGGER.log(Level.SEVERE, "Falha: {0}", falha);
        }

        LOGGER.log(Level.INFO, "Testes executados: {0} | Falhas: {1} | Ignorados: {2} | Sucesso: {3}",
                new Object[] { resultado.getRunCount(), resultado.getFailureCount(),
                        resultado.getIgnoreCount(), resultado.wasSuccessful() });
    }
}