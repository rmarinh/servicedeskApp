package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogoservicemanagement.application.RegisterCatalogController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.slaservicemanagement.application.RegistarNiveisCriticidadeController;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sara S. Silva 1181892@isep.ipp.pt
 */
public class NiveisCriticidadeBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {

        // Nivel de criticidade 1
        registerNiveisCriticidade( "AZUL", "1", "Baixo", "360", "240", "880", "660");
        // Nivel de criticidade 2
        registerNiveisCriticidade(  "VERDE", "2", "Medio Baixo", "180", "120", "440", "330");
        // Nivel de criticidade 3
        registerNiveisCriticidade(  "BRANCO", "3", "Medio", "90", "60", "360", "240");
        // Nivel de criticidade 4
        registerNiveisCriticidade( "PRETO", "4", "Medio Alto", "30", "20", "240", "150");
        // Nivel de criticidade 5
        registerNiveisCriticidade( "VERMELHO", "5", "Alto", "15", "10", "150", "90");

        return false;
    }

    private void registerNiveisCriticidade(String cor, String escalaNumerica, String etiqueta,
                                           String tempoMaximoAprovacao, String tempoMedioAprovacao, String tempoMaximoResolucao, String tempoMedioResolucao) {
        final RegistarNiveisCriticidadeController controller = new RegistarNiveisCriticidadeController();
        try {
            NiveisCriticidade nc = controller.registarNiveisCriticidade(cor, escalaNumerica, etiqueta, tempoMaximoAprovacao, tempoMedioAprovacao, tempoMaximoResolucao, tempoMedioResolucao);
            System.out.println("Nivel de criticidade criado: " + nc.toString());
            LOGGER.debug("»»» %s", nc);
        } catch (Exception e) {
            System.out.println("Nivel de criticidade não criado: " + e);
        }
    }
}
