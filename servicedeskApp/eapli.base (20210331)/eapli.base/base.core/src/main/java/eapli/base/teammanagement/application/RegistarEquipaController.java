package eapli.base.teammanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Register Catalog Controller.
 *
 * This controller class register new catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@UseCaseController
public class RegistarEquipaController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipa();
    private final EquipaTypeRepository equipaTypeRepository = PersistenceContext.repositories().equipaTypes();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();

    public Equipa registerEquipa(final String acronimo, final String designacao, final String cor,
                                 final EquipaTypeDTO tipoDTO, final Set<ColaboradorDTO> responsaveis) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        Set<Colaborador> setResponsaveis = new HashSet<>();

        responsaveis.forEach(dto->{
            setResponsaveis.add(colaboradorRepository.findByUsername(dto.username).get());
        });

        Optional<EquipaType> tipo = equipaTypeRepository.findByDescricao(tipoDTO.designacao);

        Equipa equipa = new Equipa(new Acronimo(acronimo), new Designacao(designacao), new Cor(cor), tipo.get(), setResponsaveis);
        try {
            return equipaRepository.save(equipa);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
