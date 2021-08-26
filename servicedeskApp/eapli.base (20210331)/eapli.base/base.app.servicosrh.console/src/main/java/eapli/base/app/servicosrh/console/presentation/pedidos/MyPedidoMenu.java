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
package eapli.base.app.servicosrh.console.presentation.pedidos;

import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.base.app.servicosrh.console.presentation.catalogo.AddCatalogoAction;
import eapli.base.app.servicosrh.console.presentation.catalogo.ChangeSlaCatalogoAction;
import eapli.base.app.servicosrh.console.presentation.catalogo.FindCatalogosAction;
import eapli.base.app.servicosrh.console.presentation.catalogo.ListCatalogosAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class MyPedidoMenu extends Menu {

    private static final String MENU_TITLE = "Menu Pedido >";

    private static final int EXIT_OPTION = 0;

    // MY PEDIDO
    private static final int LISTAR_PEDIDOS_CONCLUIDO_INCUMPRIMENTO_OPTION = 1;
    private static final int LISTAR_PEDIDOS_CONCLUIDO_CUMPRIMENTO_OPTION = 2;
    private static final int LOGIN_OPTION = 9;

    // EDITAR NIVEL CRITICIDADE
    private static final int DEFINIR_NIVEL_CRITICIDADE = 1;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public MyPedidoMenu() {
        super(MENU_TITLE);
        buildMyUserMenu(null);
    }

    public MyPedidoMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildMyUserMenu(onlyWithThis);
    }

    private void buildMyUserMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(MenuItem.of(LISTAR_PEDIDOS_CONCLUIDO_INCUMPRIMENTO_OPTION, "Pedidos Concluídos em Incumprimento", new ListPedidosConcluidosIncumprimentoAction()));
            addItem(MenuItem.of(LISTAR_PEDIDOS_CONCLUIDO_CUMPRIMENTO_OPTION, "Pedidos Concluídos em Cumprimento", new ListPedidosConcluidosICumprimentoAction()));

        } else {
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(onlyWithThis)::show));
        }
        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));
    }
}
