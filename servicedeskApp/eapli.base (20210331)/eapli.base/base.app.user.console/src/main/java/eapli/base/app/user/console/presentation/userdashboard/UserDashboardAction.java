package eapli.base.app.user.console.presentation.userdashboard;

import eapli.framework.actions.Action;

/**
 * Menu action for adding a new catalogo to the application.
 */
public class UserDashboardAction implements Action {

    @Override
    public boolean execute() {
        return new UserDashboardActionUI().show();
    }
}
