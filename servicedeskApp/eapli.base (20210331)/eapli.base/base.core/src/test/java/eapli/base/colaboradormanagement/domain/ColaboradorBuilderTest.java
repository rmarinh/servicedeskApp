package eapli.base.colaboradormanagement.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.model.Username;
import junit.framework.TestCase;

import static org.junit.Assert.assertTrue;

public class ColaboradorBuilderTest extends TestCase {

    Colaborador colaboradorResp = new Colaborador(NomeCompleto.valueOf("nome completo"),
            DataNascimento.valueOf("01/01/1990"), LocalResidencia.valueOf("local residencia"),
            NumeroContacto.valueOf("912223344"));

    Colaborador colaborador = new Colaborador(NomeCompleto.valueOf("nome completo"),
            DataNascimento.valueOf("01/01/1990"), LocalResidencia.valueOf("local residencia"),
            NumeroContacto.valueOf("912223344"));

    public void testWithData() {
        assertTrue(DataNascimento.valueOf("01/01/1990") != null);
    }

    public void testWithUsername() {
        assertTrue(Username.valueOf("username") != null);
    }

    public void testWithName() {
        assertTrue(NomeCompleto.valueOf("teste teste") != null);
    }

    public void testWithEmail() {
        assertTrue(EmailAddress.valueOf("teste@teste.com") != null);
    }

    public void testWithDataNascimento() {
        assertTrue(DataNascimento.valueOf("01/01/1990") != null);
    }

    public void testWithLocalResidencia() {
        assertTrue(LocalResidencia.valueOf("local") != null);
    }

    public void testWithNumeroContacto() {
        assertTrue(NumeroContacto.valueOf("912223344") != null);
    }

    public void testWithResponsavelHierarquico() {
        assertTrue(colaborador != null);
    }
}