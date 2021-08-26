package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.base.app.backoffice.console.presentation.authz.ColaboradorPrinter;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.teammanagement.application.AddRemoveColaboradorFromEquipaController;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;


public class RemoverColaboradorEquipaUI extends AbstractUI {

    private final AddRemoveColaboradorFromEquipaController theController = new AddRemoveColaboradorFromEquipaController();
    private final ListEquipaController theControllerEquipa = new ListEquipaController();
    private final ListColaboradoresController theControllerColaboradores = new ListColaboradoresController();

    @Override
    protected boolean doShow() {

        final Iterable<EquipaDTO> equipas = this.theControllerEquipa.listEquipas();

        System.out.println("Escolha uma equipa.");

        final SelectWidget<EquipaDTO> selectorEquipas = new SelectWidget<>("Equipas:", equipas,
                new EquipaPrinter());
        selectorEquipas.show();

        if (selectorEquipas.selectedOption() == 0) {
            return false;
        }

        final EquipaDTO equipa = selectorEquipas.selectedElement();

        System.out.println("Selecione o colaborador que pretende remover da equipa %s " + equipa.acronimo + ":");

        final Iterable<Colaborador> colaboradores = this.theControllerEquipa.listColabEquipas(equipa.id);

        final SelectWidget<Colaborador> selectorColaboradores = new SelectWidget<>("Colaboradores da equipa:",
                colaboradores, new ColaboradorPrinter());

        selectorColaboradores.show();

        theController.removeColaborador(equipa, selectorColaboradores.selectedElement());
        theController.removeResponsavel(equipa, selectorColaboradores.selectedElement());

        selectorColaboradores.show();

        return false;
    }


    @Override
    public String headline() {
        return "Remover Colaborador a Equipa";
    }


}
