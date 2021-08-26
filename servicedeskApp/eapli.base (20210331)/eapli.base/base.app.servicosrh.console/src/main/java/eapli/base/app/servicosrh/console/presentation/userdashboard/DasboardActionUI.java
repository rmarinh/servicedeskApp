package eapli.base.app.servicosrh.console.presentation.userdashboard;

import base.server.motorfluxoatividades.dashboard.portalrh.PortalRhAjaxServer;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DasboardActionUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {


        Username user = authz.session().get().authenticatedUser().identity();

        try {
            Thread server = new Thread(new PortalRhAjaxServer(user));
            server.start();

            System.out.println("\nBem vindo a User Dashboard\n");

            Desktop desktop = Desktop.getDesktop();

            try {
                desktop.browse(new URI(
                        "https://127.0.0.1:443"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            while (server.isAlive()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline() {
        return "User Dashboard";
    }

}
