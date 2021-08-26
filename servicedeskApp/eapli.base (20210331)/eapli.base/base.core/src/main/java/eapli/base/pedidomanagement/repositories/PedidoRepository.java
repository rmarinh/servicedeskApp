package eapli.base.pedidomanagement.repositories;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.pedidomanagement.domain.AbstractPedidoEvent;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface PedidoRepository
        extends DomainRepository<Long, Pedido> {

    public Iterable<Pedido> findAllPedidos();

    public int totalPedidos();

    public int totalPedidosSubmetidos();

    public int totalPedidosRejeitados();

    public int totalPedidosConcluídos();

    public int totalPedidosEmAprovacao();

    public int totalPedidosEmExecucao();

    public int totalPedidosAprovados();

    public Optional<Pedido> findById(Long identifier);

    public List<Pedido> findFluxosAprovacaoAutomaticas();

    public List<Pedido> findFluxosExecucaoAutomaticas();

    public List<Pedido> findPedidosPendentesAssignedToColaborador(Long numeroMecanografico);

    public List<Pedido> findPedidosPendentes();

    public List<Pedido> findPedidosSubmetidos();

    public List<Pedido> findPedidosConcluidos();

    public List<Pedido> findPedidosConcluidosCreatedByColaborador(Long numeroMecanografico);

    public List<Pedido> findPedidosPorAprovarAssignedToColaborador(Long mecanographicNumber);

    public List<Pedido> findPedidosPorExecutarAssignedToColaborador(Long mecanographicNumber);

    public List<Pedido> findPedidosConluidosAssignedToColaborador(Long mecanographicNumber);

    public List<Pedido> findPedidosEmCursoCreatedByColaborador(Long mecanographicNumber);

    public List<Pedido> findAllPedidosCreatedByColaborador(Colaborador colaborador);

    public List<Pedido> findPedidosPendentesManuais();

    public int getAvaliacaoPedido(Long identifier);

    public List<Pedido> findPedidosConcluidosNoIntervalo(String dataFrom, String dataTo);

    public List<Pedido> findPedidosSubmetidosManuais();
}
