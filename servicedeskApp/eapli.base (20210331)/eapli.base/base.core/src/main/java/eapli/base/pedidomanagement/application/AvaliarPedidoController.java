package eapli.base.pedidomanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.AvaliacaoPedido;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.repositories.AvaliacaoPedidoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * @author Rafael Soares
 */
@UseCaseController
public class AvaliarPedidoController {

    private final AvaliacaoPedidoRepository avaliacaoPedidoRepository = PersistenceContext.repositories().avaliacao();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public AvaliacaoPedido avaliarPedido(Pedido pedido, int cotacao, String comentario) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);

        AvaliacaoPedido avaliacao = new AvaliacaoPedido(pedido, cotacao, comentario);

        return avaliacaoPedidoRepository.save(avaliacao);
    }
}
