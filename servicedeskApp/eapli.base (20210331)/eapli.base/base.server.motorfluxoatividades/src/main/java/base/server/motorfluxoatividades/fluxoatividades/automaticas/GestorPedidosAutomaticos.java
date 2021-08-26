package base.server.motorfluxoatividades.fluxoatividades.automaticas;

import base.server.motorfluxoatividades.fluxoatividades.utils.NiveisPrioridade;
import base.server.motorfluxoatividades.fluxoatividades.utils.SortPedidoByNiveisCriticidade;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class GestorPedidosAutomaticos extends Thread {

    private static List<Pedido> listaPedidos;
    private static NiveisPrioridade niveisPrioridade = new NiveisPrioridade();

    public GestorPedidosAutomaticos() {
    }

    @Override
    public void run() {
        this.listaPedidos = new ArrayList<>();
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        AuthenticationService authenticationService = AuthzRegistry.authenticationService();
        authenticationService.authenticate("helpdesk", "passA1", BaseRoles.GESTOR_HELPDESK).isPresent();

        int ciclo = 0;
        while (true) {

            ciclo++;

            System.out.println("Sou automatico");

            try {
                Thread.sleep(5000);

                System.out.println("Ciclo de vida" + ciclo);

                // Get da base de dados (status Submetido e status aprovado)
                RegistarPedidoController update = new RegistarPedidoController();
                ListPedidoController controller = new ListPedidoController();
                ListColaboradorService service = new ListColaboradorService();

                // Get pedidos da BD com status SUBMETIDO
                listaPedidos = controller.allPedidosPorAprovarAutomaticos();
                Collections.sort(listaPedidos, new SortPedidoByNiveisCriticidade());

                System.out.println("Tamanho da lista de pedidos automáticos por aprovar: " + listaPedidos.size());

                for (Pedido pedido: listaPedidos) {

                    System.out.println("Prioridade Pedido " + pedido.identity() + " = " + calcularFatorPedido(pedido));

                    // Cria um pacote numero 3 e pede para ser aprovado
                    System.out.println("Vamos aprovar o pedido: " + pedido.identity());

                    MotorFluxoAtividadesClient mfc = new MotorFluxoAtividadesClient(pedido, 11);
                    mfc.run();

                    // Aprovar pedidos
                    pedido.emAprovacaoPedidoEvent(service.findColaboradorByUsername("SystemUser1").get());
                    pedido.aprovarPedido(service.findColaboradorByUsername("SystemUser1").get());
                    update.updatePedido(pedido);
                }

                Thread.sleep(5000);

                // Get pedidos da BD com status APROVADO
                listaPedidos = controller.allPedidosPorExecutarAutomaticos();
                Collections.sort(listaPedidos, new SortPedidoByNiveisCriticidade());

                System.out.println("Tamanho da lista de pedidos automáticos por executar: " + listaPedidos.size());


                for (Pedido pedido: listaPedidos) {
                    System.out.println("Prioridade Pedido " + pedido.identity() + " = " + calcularFatorPedido(pedido));

                    // cria um pacote numero 3 e pede para ser execucao
                    MotorFluxoAtividadesClient mfc = new MotorFluxoAtividadesClient(pedido, 21);
                    mfc.run();

                    System.out.println("Vamos Executar o pedido: " + pedido.identity());

                    // Executar pedidos
                    pedido.executarPedidoEvent(service.findColaboradorByUsername("SystemUser1").get());
                    pedido.concluirPedidoEvent();
                    update.updatePedido(pedido);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Método para testes para verificar a prioridade de cada pedido e se a lista está corretamente ordenada de forma decrescente.
    private int calcularFatorPedido(Pedido p){

        int tempoDecorrido = p.tempoDecorrido().intValue();
        int tempoMaximo =  Integer.parseInt(p.servico().nivelCrtiticidade().tempoMaximoAprovacao().toString());
        double tempoRestante = 0;
        double diferenca = 0;

        if (tempoDecorrido > tempoMaximo) {
            tempoRestante = (tempoDecorrido*100) / tempoMaximo;
        }else{
            diferenca = tempoMaximo-tempoDecorrido;
            tempoRestante = diferenca / tempoMaximo;
            tempoRestante = tempoRestante * 100;
        }

        tempoRestante= Math.round(tempoRestante);

        int nivelCriticidade = Integer.parseInt(p.servico().nivelCrtiticidade().escalaNumerica().toString());


        return niveisPrioridade.getPriority((int)tempoRestante, nivelCriticidade);
    }
}
