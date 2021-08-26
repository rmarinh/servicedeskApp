package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.servicosrh.console.presentation.catalogo.AddCatalogoUI;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.formulario.Atributo;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ExecutorPedidoUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RegistarPedidoController controller = new RegistarPedidoController();
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCatalogoUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("\nPor favor, selecione um pedido pendente ser realizado:\n");

        // Definir Pedido a aprovar
        final Pedido pedidoEscolhido = selectPedido();

        System.out.println(pedidoEscolhido);

        Set<Atributo> atributosRespondidos = responderFormularioResolucao(pedidoEscolhido.toDTO());
        boolean  respondidoComSucesso = controller.concluirPedido(pedidoEscolhido, atributosRespondidos);
        while (respondidoComSucesso == false){
            atributosRespondidos = responderFormularioResolucao(pedidoEscolhido.toDTO());
            respondidoComSucesso = controller.concluirPedido(pedidoEscolhido, atributosRespondidos);
        }

        // Concluir o pedido
        //controller.concluirPedido(pedidoEscolhido);



        return false;
    }

    @Override
    public String headline() {
        return "Resolver um Pedido";
    }

    public Pedido selectPedido() {
        final ListPedidoController listaColaboradoresController = new ListPedidoController();
        Username user = authz.session().get().authenticatedUser().identity();

        final SelectWidget<Pedido> selectorPedido =
                new SelectWidget<>("Pedido:", listaColaboradoresController.allPedidosPorExecutarPorColaborador(user),
                        new PedidoPrinter());

        selectorPedido.show();
        Pedido pedidoEscolhido = selectorPedido.selectedElement();

        return pedidoEscolhido;
    }

    private Set<Atributo> responderFormularioResolucao(PedidoDTO pedidoDTO) {

        Set<Atributo> atributos = controller.atributosFormularioExecucao(pedidoDTO);
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
}
