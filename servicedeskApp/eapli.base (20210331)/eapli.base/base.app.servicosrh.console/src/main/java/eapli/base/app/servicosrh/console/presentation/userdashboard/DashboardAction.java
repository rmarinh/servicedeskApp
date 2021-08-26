package eapli.base.app.servicosrh.console.presentation.userdashboard;

import eapli.framework.actions.Action;

public class DashboardAction implements Action {

    @Override
    public boolean execute() {
        return new DasboardActionUI().show();
    }
}
