package eapli.base.infrastructure.bootstrapers;

import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.application.AddRemoveColaboradorFromEquipaController;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.application.RegistarEquipaController;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.EquipaType;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class EquipaBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {

        final EquipaTypeRepository equipaTypeRepository = PersistenceContext.repositories().equipaTypes();
        final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();

        EquipaType equipaType1 = equipaTypeRepository.findByDescricao("Recursos Humanos").get();
        EquipaType equipaType2 = equipaTypeRepository.findByDescricao("Vendas").get();

        Colaborador quimBairro = colaboradorRepository.findByUsername("quimBairro").get();
        Colaborador antonioZambujeira = colaboradorRepository.findByUsername("antonioZambujeira").get();

        Set<ColaboradorDTO>colabResponsaveisEquipaVendas  = new HashSet<>();
        colabResponsaveisEquipaVendas.add(quimBairro.toDTO());
        Set<ColaboradorDTO>colabResponsaveisEquipaRH  = new HashSet<>();
        colabResponsaveisEquipaRH.add(antonioZambujeira.toDTO());

        registerEquipa("DV", "Equipa de Vendas", "Azul", equipaType2.toDTO(), colabResponsaveisEquipaVendas);
        registerEquipa("RH", "Equipa de Recursos Humanos", "Verde", equipaType1.toDTO(), colabResponsaveisEquipaRH);

        adicionarColaboradoresAEquipa();

        return false;
    }

    private void registerEquipa(String acronimo, String designacao, String cor, EquipaTypeDTO tipo, Set<ColaboradorDTO> colaboradoresResponsaveis) {
        final RegistarEquipaController controller = new RegistarEquipaController();
        try {
            Equipa equipa = controller.registerEquipa(acronimo, designacao, cor, tipo, colaboradoresResponsaveis);
            System.out.println("Equipa criada: " + equipa.designacao());
            LOGGER.debug("»»» %s", equipa);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

            System.out.println("Falhou a criacao da equipa " + acronimo);
            System.out.println(e.getMessage());
        }
    }

    private void adicionarColaboradoresAEquipa(){

        AddRemoveColaboradorFromEquipaController addRemoveCTRL = new AddRemoveColaboradorFromEquipaController();

        ListEquipaController listEquipaCtrl =  new ListEquipaController();
        Iterator<EquipaDTO> itEquipas = listEquipaCtrl.listEquipas().iterator();
        List<EquipaDTO> equipasList = new ArrayList<>();
        itEquipas.forEachRemaining(equipasList::add);

        ListColaboradoresController listColabCtrl =  new ListColaboradoresController();
        Iterator<ColaboradorDTO> itColabs  = listColabCtrl.listColaboradores().iterator();


        for(int i = 0; i<equipasList.size(); i++){

            for(int z =0; z<2; z++){
                if(itColabs.hasNext()){
                    addRemoveCTRL.addColaborador(equipasList.get(i), itColabs.next());
                }
            }

        }






    }
}
