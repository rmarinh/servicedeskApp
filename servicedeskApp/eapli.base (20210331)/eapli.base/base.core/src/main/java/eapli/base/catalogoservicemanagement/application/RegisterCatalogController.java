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

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Register Catalog Controller.
 * <p>
 * This controller class register new catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
@UseCaseController
public class RegisterCatalogController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipa();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();

    private Catalogo registerCatalog(String titulo, String descricaoDreve, String descricaoCompleta,
                                     String icone, ColaboradorDTO colaboradorDTO, NiveisCriticidadeDTO ncDTO,
                                     Set<EquipaDTO> listCriterios, Set<EquipaDTO> listEquipasResolucao, Calendar createdOn) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_HELPDESK, BaseRoles.POWER_USER, BaseRoles.RECURSOS_HUMANO);

        Colaborador responsavel = colaboradorRepository.findByUsername(colaboradorDTO.username).get();
        NiveisCriticidade nivelCriticidade = niveisCriticidadeRepository.findByEtiqueta(ncDTO.etiqueta).get();

        Set<Equipa> criterios = new HashSet<>();
        if (!listCriterios.isEmpty()) {
            listCriterios.forEach(dto -> {
                criterios.add(equipaRepository.findById(dto.id).get());
            });
        }

        Set<Equipa> equipasResolucao = new HashSet<>();
        if (!listEquipasResolucao.isEmpty()) {
            listEquipasResolucao.forEach(dto -> {
                equipasResolucao.add(equipaRepository.findById(dto.id).get());
            });
        }

        Catalogo catalogo = new Catalogo(new Titulo(titulo), new DescricaoBreve(descricaoDreve),
                new DescricaoCompleta(descricaoCompleta), new Icone(icone),
                responsavel, nivelCriticidade, criterios, equipasResolucao);

        CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
        return catalogoRepository.save(catalogo);
    }

    public Catalogo registerCatalog(String titulo, String descricaoDreve, String descricaoCompleta,
                                    String icone, ColaboradorDTO colaboradorDTO, NiveisCriticidadeDTO ncDTO,
                                    Set<EquipaDTO> criterios, Set<EquipaDTO> listEquipasResolucao) {
        return registerCatalog(titulo, descricaoDreve, descricaoCompleta, icone, colaboradorDTO, ncDTO, criterios, listEquipasResolucao, Calendars.now());
    }
}
