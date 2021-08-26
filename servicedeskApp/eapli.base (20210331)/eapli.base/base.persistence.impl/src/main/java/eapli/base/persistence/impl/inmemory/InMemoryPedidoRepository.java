package eapli.base.persistence.impl.inmemory;


import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class InMemoryPedidoRepository
        extends InMemoryDomainRepository<Pedido, Long>
        implements PedidoRepository {

    static {
        InMemoryInitializer.init();
    }


    @Override
    public Iterable<Pedido> findAllPedidos() {
        return null;
    }

    @Override
    public int totalPedidos() {
        return 0;
    }

    @Override
    public int totalPedidosSubmetidos() {
        return 0;
    }

    @Override
    public int totalPedidosRejeitados() {
        return 0;
    }

    @Override
    public int totalPedidosConcluídos() {
        return 0;
    }

    @Override
    public int totalPedidosEmAprovacao() {
        return 0;
    }

    @Override
    public int totalPedidosEmExecucao() {
        return 0;
    }

    @Override
    public int totalPedidosAprovados() {
        return 0;
    }

    @Override
    public List<Pedido> findFluxosAprovacaoAutomaticas() {
        return null;
    }

    @Override
    public List<Pedido> findFluxosExecucaoAutomaticas() {
        return null;
    }

    @Override
    public List<Pedido> findPedidosPendentesAssignedToColaborador(Long numeroMecanografico) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosPendentes() {
        return null;
    }

    @Override
    public List<Pedido> findPedidosConcluidos() {
        return null;
    }

    @Override
    public List<Pedido> findPedidosSubmetidos() {
        return null;
    }

    @Override
    public List<Pedido> findPedidosConcluidosCreatedByColaborador(Long numeroMecanografico) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosPorAprovarAssignedToColaborador(Long mecanographicNumber) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosPorExecutarAssignedToColaborador(Long mecanographicNumber) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosConluidosAssignedToColaborador(Long mecanographicNumber) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosEmCursoCreatedByColaborador(Long mecanographicNumber) {
        return null;
    }

    @Override
    public List<Pedido> findAllPedidosCreatedByColaborador(Colaborador mecanographicNumber) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosPendentesManuais() {
        return null;
    }

    @Override
    public int getAvaliacaoPedido(Long identifier) {
        return 0;
    }

    @Override
    public List<Pedido> findPedidosConcluidosNoIntervalo(String dataFrom, String dataTo) {
        return null;
    }

    @Override
    public List<Pedido> findPedidosSubmetidosManuais() {
        return null;
    }
}

