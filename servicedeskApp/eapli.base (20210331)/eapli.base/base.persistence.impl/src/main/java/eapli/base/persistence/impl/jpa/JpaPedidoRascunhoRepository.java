package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.pedidomanagement.domain.*;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.servicomanagement.domain.servico.CodigoUnico;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaPedidoRascunhoRepository
        extends JpaAutoTxRepository<PedidoRascunho, Long, Long>
        implements PedidoRascunhoRepository {

    public JpaPedidoRascunhoRepository(TransactionalContext autoTx) {
        super(autoTx, "codigoUnico");
    }

    public JpaPedidoRascunhoRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "pk");
    }

    @Override
    public Iterable<PedidoRascunho> findAllPedidosRascunho() {
        return findAll();

    }

    @Override
    public Iterable<PedidoRascunho> meusPedidosRascunho(Long number) {
        return match("AUTOR_NUMEROMECANOGRAFICO=:numeromecanografico ","numeromecanografico", number);
    }

    @Override
    public Iterable<PedidoRascunho> meusPedidosRascunhoEmEstadoRascunho(Long number) {
        final TypedQuery<PedidoRascunho> query = createQuery("SELECT e FROM PedidoRascunho e " +
                "WHERE e.submetido = :submetido AND e.autor.numeroMecanografico = :numeromecanografico", PedidoRascunho.class);

        query.setParameter("submetido", false);
        query.setParameter("numeromecanografico", number);

        return query.getResultList();
    }

    @Override
    public PedidoRascunho findPedidoRascunhoById(Long pk) {
        final Map<String, Object> params = new HashMap<>();
        params.put("pk", pk);
        return matchOne("e.pk=:pk", params).get();
    }
}

