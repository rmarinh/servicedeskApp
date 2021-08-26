package eapli.base.servicomanagement.application;

import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.domain.dto.CatalogoDTO;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicomanagement.domain.servico.CodigoUnico;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * List Catalog Controller.
 *
 * This controller class list all or a specific catalog in our system.
 *
 * @author Rui Marinho
 *
 */
public class ListServicoService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servicos();

    public Optional<Servico> findServicoById(String codUnico){
        return servicoRepository.findByCodigoUnico(CodigoUnico.valueOf(codUnico));
    }

    public Iterable<ServicoDTO> servicosByCatalogo(Catalogo idCatalogo) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        final Iterable<Servico> servicos = this.servicoRepository.findServicoByCatalogo(idCatalogo);

        final List<ServicoDTO> ret = new ArrayList<>();
        servicos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public Iterable<ServicoDTO> servicosByCatalogo(CatalogoDTO catalogoDTO) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);

        ListCatalogoController listCatalogoController = new ListCatalogoController();
        Catalogo catalogo = listCatalogoController.catalogoByIdentificador(catalogoDTO.identificador).get();

        final Iterable<Servico> servicos = this.servicoRepository.findServicoByCatalogo(catalogo);

        final List<ServicoDTO> ret = new ArrayList<>();
        servicos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public Iterable<ServicoDTO> servicosCompletos() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);
        final Iterable<Servico> servicos = this.servicoRepository.findServicosCompletos();

        final List<ServicoDTO> ret = new ArrayList<>();
        servicos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public Iterable<ServicoDTO> servicosIncompletos() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);
        final Iterable<Servico> servicos = this.servicoRepository.findServicosInCompletos();

        final List<ServicoDTO> ret = new ArrayList<>();
        servicos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }
}
