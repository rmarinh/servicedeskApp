package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.base.app.backoffice.console.presentation.authz.ColaboradorPrinterDTO;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.teammanagement.application.AddRemoveColaboradorFromEquipaController;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.HashSet;
import java.util.Set;

public class AddColaboradorToEquipaUI extends AbstractUI {

    private final AddRemoveColaboradorFromEquipaController theController = new AddRemoveColaboradorFromEquipaController();
    private final ListEquipaController theControllerEquipa = new ListEquipaController();
    private final ListColaboradoresController theControllerColaboradores = new ListColaboradoresController();

    @Override
    protected boolean doShow() {

        final Iterable<EquipaDTO> equipas = this.theControllerEquipa.listEquipas();
        final Iterable<ColaboradorDTO> colaboradores = this.theControllerColaboradores.listColaboradores();

        System.out.println("Escolha uma equipa.");

        final SelectWidget<EquipaDTO> selectorEquipas = new SelectWidget<>("Equipas:", equipas,
                new EquipaPrinter());
        selectorEquipas.show();

        if (selectorEquipas.selectedOption() == 0) {
            return false;
        }

        final EquipaDTO equipa = selectorEquipas.selectedElement();

        System.out.println("Selecione o colaborador que pretende Adicionar à equipa %s " + equipa.acronimo + ":");

        final SelectWidget<ColaboradorDTO> selectorColaboradores = new SelectWidget<>("Colaboradores:", colaboradores,
                new ColaboradorPrinterDTO());

        selectorColaboradores.show();

        final Set<Integer> stack = new HashSet<>();

        while (selectorColaboradores.selectedOption() != 0) {

            if (stack.contains(selectorColaboradores.selectedOption())) {
                System.out.println("O colaborador selecionado já faz parte da equipa.");
            } else {
                theController.addColaborador(equipa, selectorColaboradores.selectedElement());
                stack.add(selectorColaboradores.selectedOption());
            }
            selectorColaboradores.show();
        }

        return false;
    }

    @Override
    public String headline() {
        return "Adicionar Colaborador a Equipa";
    }

}





