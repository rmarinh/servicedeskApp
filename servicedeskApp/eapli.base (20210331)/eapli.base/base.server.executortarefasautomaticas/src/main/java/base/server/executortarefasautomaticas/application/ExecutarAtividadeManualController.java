package base.server.executortarefasautomaticas.application;

import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.framework.application.UseCaseController;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;

@UseCaseController
public class ExecutarAtividadeManualController {

    private boolean executarAtividadeManualController(AtividadeManual descricaoDreve, Calendar createdOn) {
        return false;
    }

    public boolean executarAtividadeManualController(AtividadeManual descricaoDreve) {
        return executarAtividadeManualController(descricaoDreve, Calendars.now());
    }
}
