package eapli.base.catalogoservicemanagement.application;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.domain.dto.CatalogoDTO;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * List Catalog Controller.
 * <p>
 * This controller class list all or a specific catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class ListCatalogoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servicos();

    public Iterable<Catalogo> activeCatalogos() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.USER, BaseRoles.RECURSOS_HUMANO);
        return this.catalogoRepository.findAll();
    }

    public Iterable<Servico> servicosByCatalogo(Catalogo idCatalogo) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.USER, BaseRoles.RECURSOS_HUMANO);
        return this.servicoRepository.findServicoByCatalogo(idCatalogo);
    }

    public Optional<Catalogo> catalogoByTitulo(String titulo) {
        return this.catalogoRepository.findByTitulo(titulo);
    }

    public Optional<Catalogo> catalogoByIdentificador(Long identificador) {
        return this.catalogoRepository.findById(identificador);
    }

    public Iterable<Catalogo> catalogosByEquipa(Equipa equipa) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.USER, BaseRoles.RECURSOS_HUMANO);
        return this.catalogoRepository.findAllByEquipa(equipa);
    }

    public Iterable<CatalogoDTO> catalogoByUser(Username username) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.USER, BaseRoles.RECURSOS_HUMANO);
        // Obter colaborador
        ListColaboradorService colaboradorService = new ListColaboradorService();
        Optional<Colaborador> colaborador = colaboradorService.findColaboradorByUsername(username.toString());

        // Obter equipas
        EquipaRepository equipaRepository = PersistenceContext.repositories().equipa();
        Iterator<Equipa> it = equipaRepository.findAll().iterator();

        List<CatalogoDTO> listCatalogo = new ArrayList<>();

        while (it.hasNext()) {
            Equipa equipa = it.next();
            if (equipa.colaboradores().contains(colaborador.get())) {
                Iterator<Catalogo> itCatalogo = this.catalogoRepository.findAll().iterator();
                while (itCatalogo.hasNext()) {
                    Catalogo catalogo = itCatalogo.next();
                    if (catalogo.criterios().contains(equipa)) {
                        listCatalogo.add(catalogo.toDTO());
                    }
                }
            }
        }

        return listCatalogo;
    }
}
