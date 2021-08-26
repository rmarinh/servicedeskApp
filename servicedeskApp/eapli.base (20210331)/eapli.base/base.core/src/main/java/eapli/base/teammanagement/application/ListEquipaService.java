package eapli.base.teammanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.EquipaType;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
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
public class ListEquipaService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipa();

    /**
     * @return
     */
    public Iterable<EquipaDTO> findAll() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);
        final Iterable<Equipa> equipas = this.equipaRepository.findAll();

        final List<EquipaDTO> ret = new ArrayList<>();
        equipas.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public Iterable<Colaborador> findAllColaboradoresEquipa(Long number) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO);
        final Iterable<Colaborador> colabs = this.equipaRepository.findAllColaboradoresEquipa(number);

        final List<Colaborador> ret = new ArrayList<>();
        colabs.forEach(e -> ret.add(e));

        return ret;
    }

    public List<Equipa> findAllEquipaByCatalogo(Long id) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO);
        return this.equipaRepository.findAllEquipaByCatalogo(id);
    }

    //Para motor de fluxo de atividades, sem autenticação
    public List<Equipa> findAllEquipaByCatalogoAutomatico(Long id) {
        return this.equipaRepository.findAllEquipaByCatalogo(id);
    }


}