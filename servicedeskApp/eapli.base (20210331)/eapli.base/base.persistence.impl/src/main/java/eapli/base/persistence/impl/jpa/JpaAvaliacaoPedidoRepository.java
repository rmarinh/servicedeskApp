package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.pedidomanagement.domain.AvaliacaoPedido;
import eapli.base.pedidomanagement.repositories.AvaliacaoPedidoRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaAvaliacaoPedidoRepository
        extends JpaAutoTxRepository<AvaliacaoPedido, Long, Long>
        implements AvaliacaoPedidoRepository {

    public JpaAvaliacaoPedidoRepository(TransactionalContext autoTx) {
        super(autoTx, "identificador");
    }

    public JpaAvaliacaoPedidoRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identificador");
    }

}
