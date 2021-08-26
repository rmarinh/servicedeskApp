package eapli.base.teammanagement.application;

import eapli.base.teammanagement.dto.EquipaTypeDTO;
import eapli.framework.application.UseCaseController;

/**
 * Created on 29/03/2016.
 */
@UseCaseController
public class ListEquipaTypeController {

    private final ListEquipaTypeService svc = new ListEquipaTypeService();

    public Iterable<EquipaTypeDTO> listTiposEquipa() {
        return this.svc.activeTiposEquipa();
    }


}
