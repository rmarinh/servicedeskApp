package eapli.base.app.backoffice.console.presentation.colaboradoruser;

import eapli.framework.actions.Action;

/**
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestAction implements Action {

    @Override
    public boolean execute() {
        return new AcceptRefuseSignupRequestUI().show();
    }
}
