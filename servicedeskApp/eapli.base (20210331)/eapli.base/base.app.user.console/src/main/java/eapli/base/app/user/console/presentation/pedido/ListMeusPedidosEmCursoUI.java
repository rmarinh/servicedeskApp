package eapli.base.app.user.console.presentation.pedido;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.*;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.visitor.Visitor;

import java.util.Collections;

public class ListMeusPedidosEmCursoUI extends AbstractUI {

    private ListPedidoController theController = new ListPedidoController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final Username user = authz.session().get().authenticatedUser().identity();
    private final PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();

    @Override
    protected boolean doShow() {

        Iterable<Pedido> pedidos = theController.allPedidosEmCursoCriadosPorColaborador(user);

        for (Pedido p : pedidos) {

            System.out.println("PEDIDO " + p.identity());
            System.out.println("-> Servico: " + p.servico().descricaoBreve());

            Pedido pedido = this.pedidoRepository.findById(p.identity()).get();

            Iterable<AbstractPedidoEvent> pedidoEvents = pedido.history();

            System.out.println("-> Histórico: ");

            for (AbstractPedidoEvent e : pedidoEvents){

                listHistorico(e);
            }
        }


        return false;
    }

    @Override
    public String headline() {
        return "Lista dos Meus Pedidos em Curso existentes em sistema:";
    }

    private void listHistorico (AbstractPedidoEvent event){

        if(event instanceof SubmetidoPedidoEvent){
            System.out.println("  - SUBMETIDO em " + event.when().getTime());
        }else if(event instanceof EmAprovacaoPedidoEvent){
            System.out.println("  - EM APROVAÇÃO em " + event.when().getTime());
        }else if(event instanceof AprovadoPedidoEvent){
            System.out.println("  - APROVADO em " + event.when().getTime());
        }else if(event instanceof EmExecucaoPedidoEvent){
            System.out.println("  - EM EXECUÇÃO em " + event.when().getTime());
        }else if(event instanceof ConcluidoPedidoEvent){
            System.out.println("  - CONCLUÍDO em " + event.when().getTime());
        }else if(event instanceof RejeitarPedidoEvent){
            System.out.println("  - REJEITADO em " + event.when().getTime());
        }

        System.out.println("  -------------------------------------");
    }
}
