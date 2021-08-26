package eapli.base.teammanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * Register Catalog Controller.
 *
 * This controller class register new catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@UseCaseController
public class RegistarEquipaTypeController {

    private final EquipaTypeRepository equipaTypeRepository = PersistenceContext.repositories().equipaTypes();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public EquipaType registerEquipaType(String designacao, String cor) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        EquipaType equipaType = new EquipaType(new Designacao(designacao), new Cor(cor));

        try {
            return equipaTypeRepository.save(equipaType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
