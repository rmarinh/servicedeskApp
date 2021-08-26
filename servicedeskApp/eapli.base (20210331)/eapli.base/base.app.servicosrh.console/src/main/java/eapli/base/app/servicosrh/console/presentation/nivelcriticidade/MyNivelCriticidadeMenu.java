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
package eapli.base.app.servicosrh.console.presentation.nivelcriticidade;

import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class MyNivelCriticidadeMenu extends Menu {

    private static final String MENU_TITLE = "Menu Niveis de criticidade >";

    private static final int EXIT_OPTION = 0;

    // MY USER
    private static final int LISTAR_NIVEIS_OPTION = 1;
    private static final int CRIAR_NIVEIS_OPTION = 2;
    private static final int LOGIN_OPTION = 9;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public MyNivelCriticidadeMenu() {
        super(MENU_TITLE);
        buildMyUserMenu(null);
    }

    public MyNivelCriticidadeMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildMyUserMenu(onlyWithThis);
    }

    private void buildMyUserMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(MenuItem.of(LISTAR_NIVEIS_OPTION, "Listar níveis de criticidade", new ListNiveisCriticidadeAction()));
            addItem(MenuItem.of(CRIAR_NIVEIS_OPTION, "Criar níveis de criticidade", new AddNiveisCriticidadeAction()));
        } else {
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(onlyWithThis)::show));
        }
        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));
    }
}
