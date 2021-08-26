package eapli.base.app.user.console;

import eapli.base.app.user.console.presentation.FrontMenu;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

/**
 * Base User App.
 */
@SuppressWarnings("squid:S106")
public final class BaseUserApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private BaseUserApp() {
    }

    public static void main(final String[] args) {
        System.out.println("=============================================");
        System.out.println("“Aplicação Portal dos Utilizadores");
        System.out.println("(C) 2016, 2017, 2018, 2019, 2020, 2021");
        System.out.println("=============================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        printTitleDrawing();

        new FrontMenu().show();

        // exiting the application, closing all threads
        System.exit(0);
    }

    public static void printTitleDrawing() {
        System.out.println("  _____   ____  _____ _______       _                _    _ _______ _____ _      _____ ______         _____   ____  _____  ");
        System.out.println(" |  __ \\ / __ \\|  __ \\__   __|/\\   | |              | |  | |__   __|_   _| |    |_   _|___  /   /\\   |  __ \\ / __ \\|  __ \\ ");
        System.out.println(" | |__) | |  | | |__) | | |  /  \\  | |              | |  | |  | |    | | | |      | |    / /   /  \\  | |  | | |  | | |__) |");
        System.out.println(" |  ___/| |  | |  _  /  | | / /\\ \\ | |              | |  | |  | |    | | | |      | |   / /   / /\\ \\ | |  | | |  | |  _  / ");
        System.out.println(" | |    | |__| | | \\ \\  | |/ ____ \\| |____          | |__| |  | |   _| |_| |____ _| |_ / /__ / ____ \\| |__| | |__| | | \\ \\ ");
        System.out.println(" |_|     \\____/|_|  \\_\\ |_/_/    \\_\\______|          \\____/   |_|  |_____|______|_____/_____/_/    \\_\\_____/ \\____/|_|  \\_\\");
    }
}
