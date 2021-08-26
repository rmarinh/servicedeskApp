package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.colaboradormanagement.application.AddColaboradorController;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.usermanagement.application.AddUserController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.text.ParseException;
import java.util.*;

public class AddColaboradorUI extends AbstractUI {

    private final AddColaboradorController theController = new AddColaboradorController();
    private final AddUserController controllerUser = new AddUserController();
    private final ListColaboradoresController listColabRep = new ListColaboradoresController();

    @Override
    protected boolean doShow() {

        final String username = Console.readLine("Username");
        final String password = Console.readLine("Password");
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("E-Mail");
        final String dataNascimento = Console.readLine("Data Nascimento (dd/MM/yyyy)");
        final String localResidencia = Console.readLine("Local Residencia");
        final String numeroContacto = Console.readLine("Numero Contacto");

        ColaboradorDTO colab = null;
        final Iterable<ColaboradorDTO> iterable = this.listColabRep.listColaboradores();

        final SelectWidget<ColaboradorDTO> selectorColab = new SelectWidget<>("Responsavel Hierarquico:", iterable,
                new ColaboradorPrinterDTO());
        selectorColab.show();

        colab = selectorColab.selectedElement();

        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        try {
            this.theController.addColaborador(username, password, firstName, lastName, email, dataNascimento,
                    localResidencia, numeroContacto, roleTypes, colab);
        } catch (final IntegrityViolationException | ConcurrencyException | ParseException e) {
            System.out.println("That username is already in use.");
        }

        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : controllerUser.getRoleTypes()) {
            rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Add Colaborador";
    }


}
