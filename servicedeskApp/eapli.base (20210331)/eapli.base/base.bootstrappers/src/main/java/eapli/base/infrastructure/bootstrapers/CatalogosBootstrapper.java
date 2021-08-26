package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogoservicemanagement.application.RegisterCatalogController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.slaservicemanagement.application.ListNiveisCriticidadeController;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.application.ListEquipaTypeController;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.dto.EquipaDTO;

import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.framework.actions.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class CatalogosBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();

    @Override
    public boolean execute() {

        ColaboradorDTO colaborador1 = new ColaboradorDTO("quimBairro", 12345678L, "Quim", "01/01/1990",  "Porto", "931111111");
        ColaboradorDTO colaborador2 = new ColaboradorDTO("antonioZambujeira", 12345678L, "Antonio", "01/01/1990",  "Lisboa", "931111111");

        NiveisCriticidadeDTO ncDTO4 = niveisCriticidadeRepository.findByEtiqueta("Medio Alto").get().toDTO();
        NiveisCriticidadeDTO ncDTO5 = niveisCriticidadeRepository.findByEtiqueta("Alto").get().toDTO();

        final ListEquipaController controllerEquipas = new ListEquipaController();
        Iterable<EquipaDTO> listEquipas = controllerEquipas.listEquipas();
        Set<EquipaDTO> setEquipa1 = new HashSet<>();
        setEquipa1.add(listEquipas.iterator().next());

        Set<EquipaDTO> setEquipa2 = new HashSet<>();
        setEquipa2.add(listEquipas.iterator().next());


        // catalogo de Marcação de Ausências - Para efeitos de teste
        registerCatalog( "Servicos tecnicos", "Servicos tecnicos", "Adquirir/reportar serviços técnicos","OLIVA_ESCURA", colaborador2, ncDTO4, setEquipa1, setEquipa2);
        // catalogo de Descontos e Vendas - Para efeitos de teste
        registerCatalog( "Apoio Psicologico", "Servicos de psicologia", "Consultas de psicologia","SEPIA", colaborador1, ncDTO5, setEquipa1, setEquipa2);

        return false;
    }

    private void registerCatalog(String titulo, String descricaoBreve, String descricaoCompleta, String icone, ColaboradorDTO colaboradorDTO, NiveisCriticidadeDTO ncDTO, Set<EquipaDTO> criterios, Set<EquipaDTO> equipasExecucao) {
        final RegisterCatalogController controller = new RegisterCatalogController();

        try {
            Catalogo catalogo = controller.registerCatalog(titulo, descricaoBreve, descricaoCompleta, icone, colaboradorDTO, ncDTO, criterios, equipasExecucao);
            System.out.println("Catalogo criado: " + catalogo.titulo());
            LOGGER.debug("»»» %s", catalogo);
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Catalogo NAO criado: " + titulo + " " + e);
        }
    }
}
