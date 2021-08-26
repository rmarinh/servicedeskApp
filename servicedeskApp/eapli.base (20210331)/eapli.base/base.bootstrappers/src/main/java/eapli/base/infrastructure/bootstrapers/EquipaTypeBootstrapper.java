package eapli.base.infrastructure.bootstrapers;

import eapli.base.teammanagement.application.RegistarEquipaTypeController;
import eapli.base.teammanagement.domain.EquipaType;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class EquipaTypeBootstrapper implements Action {

   private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {
        // Tipo de equipa de teste 1
        registerEquipaType("Recursos Humanos", "Azul");
        // Tipo de equipa de teste 2
        registerEquipaType("Vendas", "Vermelho");
        // Tipo de equipa de teste 3
        registerEquipaType("Ténico", "Verde");
        return false;
    }

    private void registerEquipaType(String designacao, String cor) {
        final RegistarEquipaTypeController controller = new RegistarEquipaTypeController();
        try {
            EquipaType tipo = controller.registerEquipaType(designacao, cor);
            System.out.println(tipo);
            LOGGER.debug("»»» %s", tipo);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            // TODO: corrigir ou tratar o erro
            System.out.println("Falhou a criacao do tipo de equipa " + designacao);
            System.out.println(e.getMessage());
        }
    }
}
