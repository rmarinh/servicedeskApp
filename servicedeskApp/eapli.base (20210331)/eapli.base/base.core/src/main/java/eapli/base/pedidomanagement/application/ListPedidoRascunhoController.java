package eapli.base.pedidomanagement.application;

import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;
import java.util.List;

/**
 * List Catalog Controller.
 * <p>
 * This controller class list all or a specific catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class ListPedidoRascunhoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final PedidoRascunhoRepository pedidoRepository = PersistenceContext.repositories().pedidosRascunho();
    private final ListColaboradorService listColaboradorService = new ListColaboradorService();

    public Iterable<PedidoRascunhoDTO> meusPedidosRascunhoEmEstadoRascunho(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        final Iterable<PedidoRascunho> rascunhos = this.pedidoRepository.meusPedidosRascunhoEmEstadoRascunho(colab.mecanographicNumber());

        final List<PedidoRascunhoDTO> ret = new ArrayList<>();
        rascunhos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public PedidoRascunho getPedidoRascunhoByID(Long pk) {
        return pedidoRepository.findPedidoRascunhoById(pk);
    }

}
