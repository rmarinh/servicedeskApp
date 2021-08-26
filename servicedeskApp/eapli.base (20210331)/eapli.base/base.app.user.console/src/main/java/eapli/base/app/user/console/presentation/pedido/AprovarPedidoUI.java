package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.servicosrh.console.presentation.catalogo.AddCatalogoUI;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.formulario.Atributo;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoRascunhoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
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

public class AprovarPedidoUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RegistarPedidoController controller = new RegistarPedidoController();
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCatalogoUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("\nPor favor, selecione um pedido pendente de aprovacao:\n");

        // codio avancar
        ListPedidoController list = new ListPedidoController();
        Username user = authz.session().get().authenticatedUser().identity();
        ListColaboradorService service = new ListColaboradorService();
        Colaborador colab = service.findColaboradorByUsername(user).get();

        /*for (Pedido p : list.allPedidos()) {
            if (p.servico().codigoUnico().toString().equals("SRV01")) {
                p.emAprovacaoPedidoEvent(colab);
                controller.updatePedido(p);
            }
        }*/

        // Definir Pedido a aprovar
        final Pedido pedidoEscolhido = selectPedido();


        final boolean aprovacaoEscolhido = aprovarRecusarPedido(pedidoEscolhido);

        if (aprovacaoEscolhido) {

            Set<Atributo> atributosRespondidos = responderFormularioAprovacao(pedidoEscolhido.toDTO());
            boolean  respondidoComSucesso = controller.aprovarPedido(pedidoEscolhido, atributosRespondidos);
            while (respondidoComSucesso == false){
                atributosRespondidos = responderFormularioAprovacao(pedidoEscolhido.toDTO());
                respondidoComSucesso = controller.aprovarPedido(pedidoEscolhido, atributosRespondidos);
            }



        } else {
            controller.recusarPedido(pedidoEscolhido);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Aprovar um Pedido";
    }

    public Pedido selectPedido() {
        final ListPedidoController listaColaboradoresController = new ListPedidoController();
        Username user = authz.session().get().authenticatedUser().identity();

        final SelectWidget<Pedido> selectorPedido =
                new SelectWidget<>("Pedido:", listaColaboradoresController.allPedidosPorAprovarPorColaborador(user),
                        new PedidoPrinter());

        selectorPedido.show();
        Pedido pedidoEscolhido = selectorPedido.selectedElement();

        return pedidoEscolhido;
    }

    public boolean aprovarRecusarPedido(Pedido pedido) {
        boolean aprovacao = true;
        String escolha;
        //do {
        escolha = Console.readLine("Deseja aprovar o seguinte pedido:+\n"
                + pedido.toString() + "\n" + "1 - Aprovar\n0 - Recusar");
        // } while (escolha.equals("1") || escolha.equals("0"));

        switch (escolha) {
            case "1":
                aprovacao = true;
                responderFormularioAprovacao(pedido.toDTO());
                break;
            case "0":
                aprovacao = false;
                break;
            default:
                System.out.println("Opcao invalida!\n");
        }





        return aprovacao;
    }

    private Set<Atributo> responderFormularioAprovacao(PedidoDTO pedidoDTO) {

        Set<Atributo> atributos = controller.atributosFormularioAprovacao(pedidoDTO);
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
