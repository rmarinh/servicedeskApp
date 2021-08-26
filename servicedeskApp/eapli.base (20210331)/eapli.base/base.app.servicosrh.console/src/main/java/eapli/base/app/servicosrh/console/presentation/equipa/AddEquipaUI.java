package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.base.app.backoffice.console.presentation.authz.ColaboradorPrinterDTO;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.application.AddRemoveColaboradorFromEquipaController;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.application.ListEquipaTypeController;
import eapli.base.teammanagement.application.RegistarEquipaController;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AddEquipaUI extends AbstractUI {

    private final RegistarEquipaController theController = new RegistarEquipaController();
    private final ListEquipaTypeController theControllerEquipaType = new ListEquipaTypeController();
    private final ListColaboradoresController theControllerColaboradores = new ListColaboradoresController();
    private final AddRemoveColaboradorFromEquipaController theControllerAddUser = new AddRemoveColaboradorFromEquipaController();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipa();

    @Override
    protected boolean doShow() {

        final Iterable<EquipaTypeDTO> tipos = this.theControllerEquipaType.listTiposEquipa();
        final Iterable<ColaboradorDTO> colaboradores = this.theControllerColaboradores.listColaboradores();

        System.out.println("Escolha um tipo de equipa.");

        final SelectWidget<EquipaTypeDTO> selectorTipos = new SelectWidget<>("Tipos de Equipa:", tipos,
                new EquipaTypePrinter());
        selectorTipos.show();

        final EquipaTypeDTO tipo = selectorTipos.selectedElement();

        final String acronimo = Console.readLine("Acronimo");

        final String cor = Console.readLine("Cor");

        final String designacao = Console.readLine("Designação");

        System.out.println("Atribua responsáveis à equipa " + acronimo + ":");

        final SelectWidget<ColaboradorDTO> selectorColaboradores = new SelectWidget<>("Colaboradores:", colaboradores,
                new ColaboradorPrinterDTO());
        selectorColaboradores.show();

        final Set<ColaboradorDTO> responsaveis = new HashSet<>();

        while (selectorColaboradores.selectedOption() != 0) {
            if (responsaveis.contains(selectorColaboradores.selectedElement())) {
                System.out.println("O colaborador selecionado já é um responsável da equipa.");
            } else {
                responsaveis.add(selectorColaboradores.selectedElement());
            }
            selectorColaboradores.show();
        }

        try {
            this.theController.registerEquipa(acronimo, designacao, cor, tipo, responsaveis);

            Optional<Equipa> equipa = equipaRepository.findByAcronimo(acronimo);
            EquipaDTO equipaDTO = equipa.get().toDTO();

            for (ColaboradorDTO c : responsaveis) {
                this.theControllerAddUser.addColaborador(equipaDTO, c);
            }

        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Equipa já existente.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Adicionar Equipa";
    }
}
