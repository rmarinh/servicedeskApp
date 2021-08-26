package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.base.teammanagement.application.RegistarEquipaController;
import eapli.base.teammanagement.application.RegistarEquipaTypeController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddEquipaTypeUI extends AbstractUI {

    private final RegistarEquipaTypeController theController = new RegistarEquipaTypeController();

    @Override
    protected boolean doShow() {

        final String cor = Console.readLine("Cor");
        final String designacao = Console.readLine("Designação");

        try {
            this.theController.registerEquipaType(designacao, cor);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Tipo de Equipa já existente.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Adicionar Tipo Equipa";
        }
    }
