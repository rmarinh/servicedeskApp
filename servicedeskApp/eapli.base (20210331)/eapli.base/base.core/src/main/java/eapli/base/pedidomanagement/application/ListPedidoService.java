package eapli.base.pedidomanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 */
@ApplicationService
class ListPedidoService {

    private final PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();

    /**
     * @return
     */
    public List<PedidoDTO> allPedidosCriadosPorColaborador(Colaborador colaboradorId) {


        final Iterable<Pedido> pedidos = this.pedidoRepository.findAllPedidosCreatedByColaborador(colaboradorId);

        final List<PedidoDTO> ret = new ArrayList<>();
        pedidos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public List<PedidoDTO> allPedidosConcluidoIntervalo(String dataFrom, String dataTo) {
        final Iterable<Pedido> pedidos = this.pedidoRepository.findPedidosConcluidosNoIntervalo(dataFrom, dataTo);

        final List<PedidoDTO> ret = new ArrayList<>();
        pedidos.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }


}