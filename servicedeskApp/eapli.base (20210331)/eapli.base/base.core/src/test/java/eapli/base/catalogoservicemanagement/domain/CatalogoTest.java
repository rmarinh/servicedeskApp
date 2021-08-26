package eapli.base.catalogoservicemanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.ColaboradorBuilder;
import eapli.base.slaservicemanagement.domain.*;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.Calendars;
import junit.framework.TestCase;

import java.util.Set;

public class CatalogoTest extends TestCase {

    /*private final String titulo = "titulo";
    private final String descricaoBreve = "descricaoBreve";
    private final String descricaoCompleta = "descricaoCompleta";
    private final String corCatalogo = "ABOBORA";

    public static Catalogo dummyCatalogo(final String titulo,
                                         final String descricaoBreve,
                                         final String descricaoCompleta,
                                         final String corCatalogo) {

        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        colaboradorBuilder.withUsername("username").withName("firstName", "lastName").
                withEmail("email@isep.pt").withDataNascimento("dataNascimento").
                withLocalResidencia("localResidencia").
                withNumeroContacto("numeroContacto").
                createdOn(Calendars.now()).
                withPassword("PassA1").withSystemUser(null).
                withResponsavelHierarquico(null);

        Colaborador colaborador = colaboradorBuilder.build();

        return new Catalogo(new Titulo("Titulo"), new DescricaoBreve("Descricao Breve"),
        new DescricaoCompleta("Descricao Completa"), new Icone("Icone"), colaborador,
        new NiveisCriticidade(1L, 1L, new Etiqueta("Etiqueta"), new EscalaNumerica("10"), Cor.AZUL,
                new TempoMedioAprovacao("10"), new TempoMaximoAprovacao("10")),

        final Titulo titulo, final DescricaoBreve descricaoBreve,
        final DescricaoCompleta descricaoCompleta, final Icone icone,
        final Colaborador responsavel, final NiveisCriticidade nivelCriticidade,
        final Set<Equipa> criterios
    }

    private Catalogo getNewdummyCatalogo() {
        return dummyCatalogo("Titulo", "DescricaoBreve",
                "DescricaoCompleta", "ABOBORA");
    }

    private Catalogo getNewdummyCatalogoTwo() {
        return dummyCatalogo("Titulo", "DescricaoBreve",
                "DescricaoCompleta", "ABOBORA");
    }*/

    public void testTestEquals() {
    }

    public void testTestHashCode() {
    }

    public void testSameAs() {
    }

    public void testIdentificador() {
    }

    public void testTitulo() {
    }

    public void testDescricaoBreve() {
    }

    public void testDescricaoCompleta() {
    }

    public void testIcone() {
    }

    public void testResponsavel() {
    }

    public void testNivelCriticidade() {
    }

    public void testCriterios() {
    }

    public void testChangeNivelCriticidade() {
    }

    public void testIdentity() {
    }

    public void testTestToString() {
    }

    public void testToDTO() {
    }
}