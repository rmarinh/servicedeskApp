package eapli.base.app.servicosrh.console.presentation.atividades;

import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class MyAtividadeMenu extends Menu {

    private static final String MENU_TITLE = "Menu Atividades >";

    private static final int EXIT_OPTION = 0;

    // MY USER
    private static final int ATIVIDADE_REGISTAR_NOVA = 1;
    private static final int LISTAR_ATIVIDADES = 2;
    private static final int LOGIN_OPTION = 9;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public MyAtividadeMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildAtividadeMenu(onlyWithThis);
    }

    private void buildAtividadeMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(ATIVIDADE_REGISTAR_NOVA, "Registar nova Atividade", new AddAtividadeBibliotecaAction());
            addItem(LISTAR_ATIVIDADES, "Listar Atividades", new ListAtividadesBibliotecaAction());
        } else {
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(onlyWithThis)::show));
        }
        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));

    }
}
