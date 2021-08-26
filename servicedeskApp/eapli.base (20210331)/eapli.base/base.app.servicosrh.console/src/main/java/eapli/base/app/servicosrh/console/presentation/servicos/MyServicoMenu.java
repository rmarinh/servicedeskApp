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
package eapli.base.app.servicosrh.console.presentation.servicos;

import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class MyServicoMenu extends Menu {

    private static final String MENU_TITLE = "Menu Serviços >";

    private static final int EXIT_OPTION = 0;

    // MY USER
    private static final int SERVICO_REGISTAR_NOVO = 1;
    private static final int SERVICO_CONTINUAR_ESPECIFICACAO = 2;
    private static final int SERVICO_LISTAR_COMPLETOS = 3;
    private static final int SERVICO_LISTAR_INCOMPLETOS = 4;
    private static final int LOGIN_OPTION = 9;


    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public MyServicoMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildServicoMenu(onlyWithThis);
    }

    private void buildServicoMenu(final Role onlyWithThis) {
        if(authz.hasSession()){
            addItem(SERVICO_REGISTAR_NOVO, "Registar novo Servico", new AddServicoAction());
            addItem(SERVICO_CONTINUAR_ESPECIFICACAO, "Continuar Especificacao de Servico", new CompleteServicoAction());
            addItem(SERVICO_LISTAR_COMPLETOS, "Listar Servicos Completos", new ListServicosCompletosAction());
            addItem(SERVICO_LISTAR_INCOMPLETOS, "Listar Servicos Incompletos", new ListServicosIncompletosAction());
        }else{
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(onlyWithThis)::show));
        }
        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));

    }
}
