package eapli.base.app.user.console.presentation.pedido;

import eapli.base.formulario.Atributo;
import eapli.base.pedidomanagement.application.ListPedidoRascunhoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoRascunhoController;
import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.HashSet;
import java.util.Set;

public class PedidoRascunhoUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListServicoService theListServicoService = new ListServicoService();
    private final RegistarPedidoController controller = new RegistarPedidoController();
    private final Username user = authz.session().get().authenticatedUser().identity();

    @Override
    protected boolean doShow() {
        // Listar todos os meus rascunhos
        final PedidoRascunhoDTO rascunhoEscolhido = selectRascunho();

        // Preencher o formulario
        Set<Atributo> atributosRespondidos = responderFormulario(rascunhoEscolhido);

        // guardar pedido e alterar o estado do rascunho
        registerPedido(rascunhoEscolhido, atributosRespondidos);
        return false;
    }

    private PedidoRascunhoDTO selectRascunho() {
        final ListPedidoRascunhoController listRascunhosController = new ListPedidoRascunhoController();

        final SelectWidget<PedidoRascunhoDTO> selectorRascunho =
                new SelectWidget<PedidoRascunhoDTO>("Pedidos em Rascunhos: ", listRascunhosController.meusPedidosRascunhoEmEstadoRascunho(user),
                        new PedidoRascunhoPrinter());

        selectorRascunho.show();
        PedidoRascunhoDTO rascunhoEscolhido = selectorRascunho.selectedElement();

        return rascunhoEscolhido;
    }

    private Set<Atributo> responderFormulario(PedidoRascunhoDTO rascunho) {
        final RegistarPedidoRascunhoController controller = new RegistarPedidoRascunhoController();

        Set<Atributo> atributos = controller.atributosFormulario(rascunho);
        Set<Atributo> atributosRespondidos = new HashSet<>();

        for (Atributo a : atributos){
            System.out.println("Responda ao seguinte Atributo: ");
            System.out.println("Nome: " + a.nome());
            System.out.println("Titulo: " + a.titulo());
            System.out.println("Descricao: " + a.descricao());
            System.out.println("Tipo dados: " + a.tipoDados());
            System.out.println("Validador(): " + a.validador());

            String preenchimento = Console.readLine("Preenchimento: ");

            Atributo aResp = new Atributo(a.nome(), a.titulo(), a.descricao(),
                    a.tipoDados(), a.validador(), preenchimento);
            atributosRespondidos.add(aResp);
        }

        return atributosRespondidos;
    }

    private void registerPedido(PedidoRascunhoDTO rascunho, Set<Atributo> atributosRespondidos) {
        final RegistarPedidoRascunhoController controller = new RegistarPedidoRascunhoController();
        controller.preencherFormulario(atributosRespondidos, rascunho);
    }

    @Override
    public String headline() {
        return "Concluir um Pedido em rascunho";
    }
}
