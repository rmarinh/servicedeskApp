package eapli.base.teammanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Preconditions;

public class AddRemoveColaboradorFromEquipaController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListEquipaService svc = new ListEquipaService();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipa();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();

    public Iterable<EquipaDTO> allEquipas() {
        return this.svc.findAll();
    }

    public Equipa addColaborador(final EquipaDTO equipaDTO, final ColaboradorDTO colabDTO) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        Preconditions.nonNull(equipaDTO);
        Preconditions.nonNull(colabDTO);

        Equipa equipa = equipaRepository.findById(equipaDTO.id).get();

        Colaborador colaborador = colaboradorRepository.findByUsername(colabDTO.username).get();

        equipa.addColaborador(colaborador);

        return this.equipaRepository.save(equipa);
    }

    public Equipa removeColaborador(final EquipaDTO equipaDTO, final Colaborador colab) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO);

        Preconditions.nonNull(equipaDTO);
        Preconditions.nonNull(colab);

        Equipa equipa = equipaRepository.findById(equipaDTO.id).get();

        equipa.removeColaborador(colab);

        return this.equipaRepository.save(equipa);
    }

    public Equipa removeResponsavel(final EquipaDTO equipaDTO, final Colaborador colab) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO);

        Preconditions.nonNull(equipaDTO);
        Preconditions.nonNull(colab);

        Equipa equipa = equipaRepository.findById(equipaDTO.id).get();

        equipa.removerResponsavel(colab);

        return this.equipaRepository.save(equipa);
    }
}
