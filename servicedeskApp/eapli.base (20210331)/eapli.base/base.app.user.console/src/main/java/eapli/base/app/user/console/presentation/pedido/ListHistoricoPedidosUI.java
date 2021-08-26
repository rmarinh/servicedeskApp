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

public class ListHistoricoPedidosUI extends AbstractUI {

    private ListPedidoController theController = new ListPedidoController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final Username user = authz.session().get().authenticatedUser().identity();
    private final PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();


    @Override
    protected boolean doShow() {
        final Iterable<PedidoDTO> pedidos = this.theController.allPedidosCriadosPorColaborador(user);

        for (PedidoDTO p : pedidos) {
            System.out.println("Histórico do Pedido " + p.pk + " do Serviço " + p.idServico);

            Pedido pedido = this.pedidoRepository.findById(p.pk).get();

            Iterable<AbstractPedidoEvent> pedidoEvents = pedido.history();

            for (AbstractPedidoEvent e : pedidoEvents){
                listHistorico(e);
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Historico dos Meus Pedidos";
    }

    private void listHistorico (AbstractPedidoEvent event){

        if(event instanceof SubmetidoPedidoEvent){
            System.out.println(" - Submetido em " + event.when().getTime());
        }else if(event instanceof EmAprovacaoPedidoEvent){
            System.out.println(" - Em Aprovação em " + event.when().getTime());
        }else if(event instanceof AprovadoPedidoEvent){
            System.out.println(" - Aprovado em " + event.when().getTime());
        }else if(event instanceof EmExecucaoPedidoEvent){
            System.out.println(" - Em execução em " + event.when().getTime());
        }else if(event instanceof ConcluidoPedidoEvent){
            System.out.println(" - Concluído em " + event.when().getTime());
        }else if(event instanceof RejeitarPedidoEvent){
            System.out.println(" - Rejeitado em " + event.when().getTime());
        }

        System.out.println("-------------------------------------");
    }

}
