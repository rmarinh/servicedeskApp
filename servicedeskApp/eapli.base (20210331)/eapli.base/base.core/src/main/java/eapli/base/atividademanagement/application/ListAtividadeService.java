package eapli.base.atividademanagement.application;

import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.Optional;

/**
 * List Catalog Controller.
 *
 * This controller class list all or a specific catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
public class ListAtividadeService {

    //private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AtividadeRepository atividadeRepository = PersistenceContext.repositories().atividade();

    public Iterable<Atividade> atividades() {
        //authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return this.atividadeRepository.findAll();
    }

    public Iterable<Atividade> atividadesAutomaticas() {
        //authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return this.atividadeRepository.allAtividadesAutomaticas();
    }
    public Iterable<Atividade> atividadesManuais() {
        //authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return this.atividadeRepository.allAtividadesManuais();
    }

}
