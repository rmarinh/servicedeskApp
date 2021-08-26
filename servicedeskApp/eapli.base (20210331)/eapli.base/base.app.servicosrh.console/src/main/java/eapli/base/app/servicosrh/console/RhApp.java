/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.base.app.servicosrh.console;

import eapli.base.app.common.console.BaseApplication;
import eapli.base.app.common.console.presentation.authz.LoginAction;
import eapli.base.app.servicosrh.console.presentation.MainMenu;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.eventpubsub.EventDispatcher;

/**
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class RhApp extends BaseApplication {

    /**
     * avoid instantiation of this class.
     */
    private RhApp() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        new RhApp().run(args);
    }

    @Override
    protected void doMain(String[] args) {
        printTitleDrawing();

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        // login and go to main menu
        if (new LoginAction(BaseRoles.RECURSOS_HUMANO).execute()) {

            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }

        // exiting the application, closing all threads
        System.exit(0);
    }

    @Override
    protected String appTitle() {
        return "Aplicação de Serviços e Recursos Humanos";
    }

    @Override
    protected String appGoodbye() {
        return "Aplicação de Serviços e Recursos Humanos deseja-lhe um bom dia!";
    }

    @Override
    protected void doSetupEventHandlers(EventDispatcher dispatcher) {
    }

    public void printTitleDrawing() {
        System.out.println(" 8888888b.                                                                    888    888   ");
        System.out.println(" 888   Y88b                                                                   888    888     ");
        System.out.println(" 888    888                                                                   888    888      ");
        System.out.println(" 888   d88P .d88b.   .d8888b 888  888 888d888 .d8888b   .d88b.  .d8888b       8888888888 888  888 88888b.d88b.   8888b.  88888b.   .d88b.  .d8888b  ");
        System.out.println(" 8888888P\" d8P  Y8b d88P\"    888  888 888P\"   88K      d88\"\"88b 88K           888    888 888  888 888 \"888 \"88b     \"88b 888 \"88b d88\"\"88b 88K      ");
        System.out.println(" 888 T88b  88888888 888      888  888 888     \"Y8888b. 888  888 \"Y8888b.      888    888 888  888 888  888  888 .d888888 888  888 888  888 \"Y8888b. ");
        System.out.println(" 888  T88b Y8b.     Y88b.    Y88b 888 888          X88 Y88..88P      X88      888    888 Y88b 888 888  888  888 888  888 888  888 Y88..88P      X88 ");
        System.out.println(" 888   T88b \"Y8888   \"Y8888P  \"Y88888 888      88888P'  \"Y88P\"   88888P'      888    888  \"Y88888 888  888  888 \"Y888888 888  888  \"Y88P\"   88888P'");
    }
}
