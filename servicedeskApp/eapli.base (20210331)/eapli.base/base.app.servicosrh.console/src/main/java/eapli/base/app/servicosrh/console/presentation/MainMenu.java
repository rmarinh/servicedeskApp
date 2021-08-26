/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.servicosrh.console.presentation;

import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.servicosrh.console.presentation.atividades.MyAtividadeMenu;
import eapli.base.app.servicosrh.console.presentation.catalogo.MyCatalogoMenu;
import eapli.base.app.servicosrh.console.presentation.equipa.MyEquipaMenu;
import eapli.base.app.servicosrh.console.presentation.nivelcriticidade.MyNivelCriticidadeMenu;
import eapli.base.app.servicosrh.console.presentation.pedidos.MyPedidoMenu;
import eapli.base.app.servicosrh.console.presentation.servicos.MyServicoMenu;
import eapli.base.app.servicosrh.console.presentation.userdashboard.DashboardAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {


    private static final String SEPARATOR_LABEL = "--------------";
    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int MY_CATALOGO_OPTION = 2;
    private static final int MY_NIVEISCRITICIDADE_OPTION = 3;
    private static final int MY_EQUIPA_OPTION = 4;
    private static final int MY_SERVICO_OPTION = 5;
    private static final int MY_ATIVIDADE_OPTION = 6;
    private static final int MY_DASHBOARD_OPTION = 7;
    private static final int MY_PEDIDO_OPTION = 8;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final Menu menu;
    private final MenuRenderer renderer;

    public MainMenu() {
        menu = buildMainMenu();
        renderer = getRenderer(menu);
    }

    private MenuRenderer getRenderer(final Menu menu) {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }

    @Override
    public boolean doShow() {
        return renderer.render();
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public String headline() {
        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu(BaseRoles.RECURSOS_HUMANO);
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            final Menu cashierMenu = new MyCatalogoMenu(BaseRoles.RECURSOS_HUMANO);
            mainMenu.addSubMenu(MY_CATALOGO_OPTION, cashierMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            final Menu GSH = new MyNivelCriticidadeMenu(BaseRoles.RECURSOS_HUMANO);
            mainMenu.addSubMenu(MY_NIVEISCRITICIDADE_OPTION, GSH);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            final Menu GSH = new MyEquipaMenu(BaseRoles.RECURSOS_HUMANO);
            mainMenu.addSubMenu(MY_EQUIPA_OPTION, GSH);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            final Menu GSH = new MyServicoMenu(BaseRoles.RECURSOS_HUMANO);
            mainMenu.addSubMenu(MY_SERVICO_OPTION, GSH);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            final Menu GSH = new MyAtividadeMenu(BaseRoles.RECURSOS_HUMANO);
            mainMenu.addSubMenu(MY_ATIVIDADE_OPTION, GSH);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            mainMenu.addItem(MY_DASHBOARD_OPTION, "Dashboard", new DashboardAction());
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.RECURSOS_HUMANO)) {
            final Menu GSH = new MyPedidoMenu(BaseRoles.RECURSOS_HUMANO);
            mainMenu.addSubMenu(MY_PEDIDO_OPTION, GSH);
        }
        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
}
