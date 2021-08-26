package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.framework.visitor.Visitor;

public class EquipaPrinter implements Visitor<EquipaDTO> {

    @Override
    public void visit(final EquipaDTO visitee) {
        System.out.printf("%-10s%-30s%-30s%-30s", visitee.acronimo, visitee.designacao, visitee.tipo, visitee.cor);
    }
}
