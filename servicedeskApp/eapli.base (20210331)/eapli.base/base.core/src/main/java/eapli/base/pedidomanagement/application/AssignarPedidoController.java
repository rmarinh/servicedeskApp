package eapli.base.pedidomanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.AvaliacaoPedido;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoStatus;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Rafael Soares
 */
@UseCaseController
public class AssignarPedidoController {

    private final PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RegistarPedidoController controller = new RegistarPedidoController();

    public void assignarPedidoSubmetido(Pedido pedido, Colaborador colaborador) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);

        pedido.emAprovacaoPedidoEvent(colaborador);
        controller.updatePedido(pedido);
    }

    public void assignarPedidoAprovado(Pedido pedido, Colaborador colaborador) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);

        pedido.executarPedidoEvent(colaborador);
        controller.updatePedido(pedido);
    }

    // Para motor de fluxo de atividades, sem autenticação de user
    public void assignarPedidoAprovadoAutomatico(Pedido pedido, Colaborador colaborador) {
        pedido.executarPedidoEvent(colaborador);
        controller.updatePedido(pedido);
    }
}
