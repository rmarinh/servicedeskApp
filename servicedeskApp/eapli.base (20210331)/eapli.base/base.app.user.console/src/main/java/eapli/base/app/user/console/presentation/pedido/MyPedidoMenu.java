package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class MyPedidoMenu extends Menu {

    private static final String SEPARATOR_LABEL = "--------------";
    private static final String MENU_TITLE = "Menu Pedidos >";

    private static final int EXIT_OPTION = 0;

    // MY USER
    private static final int REGISTAR_NOVO_PEDIDO = 1;
    private static final int CONCLUIR_UM_RASCUNHO = 2;
    private static final int ASSIGNAR_PEDIDO_PENDENTE = 3;
    private static final int APROVAR_PEDIDO_PENDENTES = 4;
    private static final int RESOLVER_PEDIDO = 5;
    private static final int AVALIAR_PEDIDO_CONCLUIDO = 6;
    private static final int LISTAR_PEDIDOS_PENDENTES = 7;
    private static final int LISTAR_MEUS_PEDIDOS_PENDENTES = 8;
    private static final int LISTAR_MEUS_PEDIDOS_EM_CURSO = 9;
    private static final int HISTORICO_DOS_MEUS_PEDIDOS = 10;

    private static final int LOGIN_OPTION = 20;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public MyPedidoMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildPedidoMenu(onlyWithThis);
    }

    private void buildPedidoMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(REGISTAR_NOVO_PEDIDO, "Registar novo pedido", new PedidoAction());
            addItem(CONCLUIR_UM_RASCUNHO, "Concluir um pedido em rascunho", new PedidoRascunhoAction());
            addItem(ASSIGNAR_PEDIDO_PENDENTE, "Assignar pedido pendente", new AssignarPedidoPendenteAction());
            addItem(APROVAR_PEDIDO_PENDENTES, "Aprovar pedidos pendentes", new AprovarPedidoPendenteAction());
            addItem(RESOLVER_PEDIDO, "Resolver pedido pendente", new ResolverPedidoPendenteAction());
            addItem(AVALIAR_PEDIDO_CONCLUIDO, "Avaliar pedido concluido", new AvaliarPedidoConcluidoAction());
            addItem(LISTAR_PEDIDOS_PENDENTES, "Listar pedidos pendentes", new ListPedidosPendentesAction());
            addItem(LISTAR_MEUS_PEDIDOS_PENDENTES, "Listar pedidos pendentes assignados", new ListPedidosPendentesAssignedToColaboradorAction());
            addItem(LISTAR_MEUS_PEDIDOS_EM_CURSO, "Listar meus pedidos em curso", new ListMeusPedidosEmCursoAction());
            addItem(HISTORICO_DOS_MEUS_PEDIDOS, "Histórico dos meus pedidos", new ListMeusPedidosEmCursoAction());
        } else {
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(onlyWithThis)::show));
        }
        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));
    }
}
