package eapli.base.slaservicemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

/**
 * List Niveis de criticidade Controller.
 * <p>
 * O controller lista todos ou apenas um especifico nivel de criticidade no sistema.
 *
 * @author Sara S. Silva 1181892@isep.ipp.pt
 */
public class ListNiveisCriticidadeController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();

    /**
     * @return Iteravel com os niveis de criticidade
     */
    public Iterable<NiveisCriticidade> AllNiveisCriticidade() {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_HELPDESK, BaseRoles.RECURSOS_HUMANO);

        return this.niveisCriticidadeRepository.findAll();
    }

    /**
     * @param id identificador
     * @return Nivel de criticidade cujo id é igual ao parametro passado
     */
    public Optional<NiveisCriticidade> nivelCriticidadeById(Long id) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_HELPDESK, BaseRoles.RECURSOS_HUMANO);

        return this.niveisCriticidadeRepository.findById(id);
    }
}
