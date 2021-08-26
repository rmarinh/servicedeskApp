package eapli.base.catalogoservicemanagement.application;

import eapli.base.catalogoservicemanagement.domain.*;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.util.Calendars;
import org.hibernate.criterion.Projections;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Change Nivel Criticidade Catalog Controller.
 * <p>
 * This controller class changes the current SLA for a new one in a existing catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
@UseCaseController
public class ChangeNivelCriticidadeCatalogController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();

    private Catalogo ChangeNivelCriticidade(Long idCatalogo, Long idNivelCriticidade, Calendar createdOn) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_HELPDESK, BaseRoles.POWER_USER, BaseRoles.RECURSOS_HUMANO);

        Catalogo catalogo = catalogoRepository.findById(idCatalogo).get();
        NiveisCriticidade nivelCriticidade = niveisCriticidadeRepository.findById(idNivelCriticidade).get();

        catalogo.changeNivelCriticidade(nivelCriticidade);

        return catalogoRepository.save(catalogo);
    }

    public Catalogo ChangeNivelCriticidade(Long idCatalogo, Long idNivelCriticidade) {
        return ChangeNivelCriticidade(idCatalogo, idNivelCriticidade, Calendars.now());
    }
}
