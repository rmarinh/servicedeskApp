package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.pedidomanagement.domain.*;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.*;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaPedidoRepository
        extends JpaAutoTxRepository<Pedido, Long, Long>
        implements PedidoRepository {

    public JpaPedidoRepository(TransactionalContext autoTx) {
        super(autoTx, "codigoUnico");
    }

    public JpaPedidoRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "pk");
    }

    @Override
    public Iterable<Pedido> findAllPedidos() {

        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e", Pedido.class);

        return query.getResultList();
    }

    @Override
    public int totalPedidos() {
        return (int) count();
    }

    @Override
    public int totalPedidosSubmetidos() {
        final TypedQuery<Long> query = createQuery("SELECT COUNT(e) FROM Pedido e WHERE e.status = :status", Long.class);
        query.setParameter("status", PedidoStatus.SUBMETIDO);

        return query.getSingleResult().intValue();
    }

    @Override
    public int totalPedidosRejeitados() {
        final TypedQuery<Long> query = createQuery("SELECT COUNT(e) FROM Pedido e WHERE e.status = :status", Long.class);
        query.setParameter("status", PedidoStatus.REJEITADO);

        return query.getSingleResult().intValue();
    }

    @Override
    public int totalPedidosConcluídos() {
        final TypedQuery<Long> query = createQuery("SELECT COUNT(e) FROM Pedido e WHERE e.status = :status", Long.class);
        query.setParameter("status", PedidoStatus.CONCLUIDO);

        return query.getSingleResult().intValue();
    }

    @Override
    public int totalPedidosEmAprovacao() {
        final TypedQuery<Long> query = createQuery("SELECT COUNT(e) FROM Pedido e WHERE e.status = :status", Long.class);
        query.setParameter("status", PedidoStatus.EM_APROVACAO);

        return query.getSingleResult().intValue();
    }

    @Override
    public int totalPedidosEmExecucao() {
        final TypedQuery<Long> query = createQuery("SELECT COUNT(e) FROM Pedido e WHERE e.status = :status", Long.class);
        query.setParameter("status", PedidoStatus.EM_EXECUCAO);

        return query.getSingleResult().intValue();
    }

    @Override
    public int totalPedidosAprovados() {
        final TypedQuery<Long> query = createQuery("SELECT COUNT(e) FROM Pedido e WHERE e.status = :status", Long.class);
        query.setParameter("status", PedidoStatus.APROVADO);

        return query.getSingleResult().intValue();
    }

    @Override
    public List<Pedido> findFluxosAprovacaoAutomaticas() {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.servico.fluxoAprovacao.tipoAtividade = :tipo", Pedido.class);

        query.setParameter("status", PedidoStatus.SUBMETIDO);
        query.setParameter("tipo", AtividadeAutomatica.class.getSimpleName());

        return query.getResultList();
    }

    @Override
    public List<Pedido> findFluxosExecucaoAutomaticas() {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.servico.fluxoResolucao.tipoAtividade = :tipo", Pedido.class);

        query.setParameter("status", PedidoStatus.APROVADO);
        query.setParameter("tipo", AtividadeAutomatica.class.getSimpleName());

        return query.getResultList();
    }

    @Override
    public List<Pedido> findPedidosPendentesAssignedToColaborador(Long numeroMecanografico) {

        final Map<String, Object> params = new HashMap<>();
        params.put("status", PedidoStatus.EM_APROVACAO);
        List<Pedido> match = match("e.status=:status", params);

        List<Pedido> novaList = new ArrayList<>();
        if (!match.isEmpty()) {
            for (Pedido pedido : match) {
                for (AbstractPedidoEvent event : pedido.history()) {
                    if (event instanceof EmAprovacaoPedidoEvent) {
                        if (((EmAprovacaoPedidoEvent) event).who().mecanographicNumber().equals(numeroMecanografico)) {
                            novaList.add(pedido);
                        }
                    }
                }
            }
        }

        final Map<String, Object> params2 = new HashMap<>();
        params2.put("status", PedidoStatus.EM_EXECUCAO);
        List<Pedido> match2 = match("e.status=:status", params2);

        if (!match2.isEmpty()) {
            for (Pedido pedido : match2) {
                for (AbstractPedidoEvent event : pedido.history()) {
                    if (event instanceof EmExecucaoPedidoEvent) {
                        if (((EmExecucaoPedidoEvent) event).who().mecanographicNumber().equals(numeroMecanografico)) {
                            novaList.add(pedido);
                        }
                    }
                }
            }
        }
        return novaList;
    }

    @Override
    public List<Pedido> findPedidosPendentes() {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE (e.status = :statusAprovado OR e.status = :statusSubmetido) AND e.servico.fluxoResolucao.tipoAtividade = :tipo", Pedido.class);

        query.setParameter("statusAprovado", PedidoStatus.APROVADO);
        query.setParameter("statusSubmetido", PedidoStatus.SUBMETIDO);
        query.setParameter("tipo", AtividadeManual.class.getSimpleName());

        return query.getResultList();
    }

    @Override
    public List<Pedido> findPedidosConcluidosNoIntervalo(String dataFrom, String dataTo) {
        final String selectAbstractPedidoEvent ="SELECT DISTINCT a.pedido.pk FROM AbstractPedidoEvent a WHERE a.createdOn >= '"
                + dataFrom + " 00:00:00' AND a.createdOn <= '" + dataTo +" 23:59:59'";

        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.pk IN (" + selectAbstractPedidoEvent + ")" , Pedido.class);

        query.setParameter("status", PedidoStatus.CONCLUIDO);
        return query.getResultList();
    }

    @Override
    public List<Pedido> findPedidosPendentesManuais() {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.servico.fluxoResolucao.tipoAtividade = :tipo", Pedido.class);

        query.setParameter("status", PedidoStatus.APROVADO);
        query.setParameter("tipo", AtividadeManual.class.getSimpleName());

        return query.getResultList();
    }

    @Override
    public List<Pedido> findPedidosSubmetidosManuais() {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.servico.fluxoResolucao.tipoAtividade = :tipo", Pedido.class);

        query.setParameter("status", PedidoStatus.SUBMETIDO);
        query.setParameter("tipo", AtividadeManual.class.getSimpleName());

        return query.getResultList();
    }

    @Override
    public int getAvaliacaoPedido(Long identifier) {
        final TypedQuery<Integer> query = createQuery("SELECT a.cotacao FROM AvaliacaoPedido a " +
                "WHERE a.pedido.pk = :id", Integer.class);

        query.setParameter("id", identifier);
        return query.getSingleResult();
    }

    @Override
    public List<Pedido> findPedidosSubmetidos() {
        return match("status='SUBMETIDO'");
    }

    @Override
    public List<Pedido> findPedidosConcluidos() { return match("status='CONCLUIDO'"); }

    @Override
    public List<Pedido> findPedidosConcluidosCreatedByColaborador(Long numeroMecanografico) {
        final Map<String, Object> params = new HashMap<>();
        params.put("status", PedidoStatus.CONCLUIDO);
        List<Pedido> match = match("e.status=:status", params);

        List<Pedido> novaList = new ArrayList<>();
        if (!match.isEmpty()) {
            for (Pedido pedido : match) {
                if (pedido.colaborador().mecanographicNumber() == numeroMecanografico) {
                    novaList.add(pedido);
                }
            }
        }
        return novaList;
    }

    @Override
    public List<Pedido> findPedidosPorAprovarAssignedToColaborador(Long mecanographicNumber) {
        final Map<String, Object> params = new HashMap<>();
        params.put("status", PedidoStatus.EM_APROVACAO);
        List<Pedido> match = match("e.status=:status", params);

        List<Pedido> novaList = new ArrayList<>();
        if (!match.isEmpty()) {
            for (Pedido pedido : match) {
                for (AbstractPedidoEvent event : pedido.history()) {
                    if (event instanceof EmAprovacaoPedidoEvent) {
                        if (((EmAprovacaoPedidoEvent) event).who().mecanographicNumber().equals(mecanographicNumber)) {
                            novaList.add(pedido);
                        }
                    }
                }
            }
        }

        return novaList;
    }

    @Override
    public List<Pedido> findPedidosPorExecutarAssignedToColaborador(Long mecanographicNumber) {
        final Map<String, Object> params2 = new HashMap<>();
        params2.put("status", PedidoStatus.EM_EXECUCAO);
        List<Pedido> match = match("e.status=:status", params2);

        List<Pedido> novaList = new ArrayList<>();
        if (!match.isEmpty()) {
            for (Pedido pedido : match) {
                for (AbstractPedidoEvent event : pedido.history()) {
                    if (event instanceof EmExecucaoPedidoEvent) {
                        if (((EmExecucaoPedidoEvent) event).who().mecanographicNumber().equals(mecanographicNumber)) {
                            novaList.add(pedido);
                        }
                    }
                }
            }
        }
        return novaList;
    }

    @Override
    public List<Pedido> findPedidosConluidosAssignedToColaborador(Long numeroMecanografico) {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.colaborador.numeroMecanografico = :numeroMecanografico", Pedido.class);

        query.setParameter("status", PedidoStatus.CONCLUIDO);
        query.setParameter("numeroMecanografico", numeroMecanografico);

        return query.getResultList();
    }

    @Override
    public List<Pedido> findPedidosEmCursoCreatedByColaborador(Long numeroMecanografico) {
        final TypedQuery<Pedido> query = createQuery("SELECT e FROM Pedido e " +
                "WHERE e.status = :status AND e.colaborador.numeroMecanografico = :numeroMecanografico ORDER BY e.createdOn DESC", Pedido.class);

        query.setParameter("status", PedidoStatus.EM_EXECUCAO);
        query.setParameter("numeroMecanografico", numeroMecanografico);

        return query.getResultList();
    }

    @Override
    public List<Pedido> findAllPedidosCreatedByColaborador(Colaborador colaborador) {
        final Map<String, Object> params = new HashMap<>();
        params.put("colaborador", colaborador);

        return match("e.colaborador=:colaborador", params);
    }

    @Override
    public Optional<Pedido> findById(Long identifier) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", identifier);

        return matchOne("e.pk=:id", params);
    }
}

