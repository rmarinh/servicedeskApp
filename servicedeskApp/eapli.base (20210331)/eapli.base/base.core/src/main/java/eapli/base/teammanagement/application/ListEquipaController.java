package eapli.base.teammanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.framework.application.UseCaseController;

/**
 * Created on 29/03/2016.
 */
@UseCaseController
public class ListEquipaController {

    private final ListEquipaService svc = new ListEquipaService();

    public Iterable<EquipaDTO> listEquipas() {
        return this.svc.findAll();
    }

    public Iterable<Colaborador> listColabEquipas(Long number) {
        return this.svc.findAllColaboradoresEquipa(number);
    }

}
