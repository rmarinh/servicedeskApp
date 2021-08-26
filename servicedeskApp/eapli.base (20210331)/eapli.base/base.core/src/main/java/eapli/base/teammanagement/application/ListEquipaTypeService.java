package eapli.base.teammanagement.application;

import eapli.base.teammanagement.domain.EquipaType;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 */
@ApplicationService
class ListEquipaTypeService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EquipaTypeRepository equipaTypeRepository = PersistenceContext.repositories().equipaTypes();

    /**
     *
     * @return
     */
    public Iterable<EquipaTypeDTO> allTiposEquipa() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO);
        final Iterable<EquipaType> tipos = this.equipaTypeRepository.findAll();

        final List<EquipaTypeDTO> ret = new ArrayList<>();
        tipos.forEach(e-> ret.add(e.toDTO()));

        return ret;
    }

    /**
     *
     * @return
     */
    public Iterable<EquipaTypeDTO> activeTiposEquipa() {
        //authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.POWER_USER, CafeteriaRoles.MENU_MANAGER);

        final Iterable<EquipaType> tipos = this.equipaTypeRepository.activeEquipaTypes();

        final List<EquipaTypeDTO> ret = new ArrayList<>();
        tipos.forEach(e-> ret.add(e.toDTO()));

        return ret;
    }
}