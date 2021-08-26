package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoStatus;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaColaboradorRepository
        extends JpaAutoTxRepository<Colaborador, Long, Long>
        implements ColaboradorRepository {

    public JpaColaboradorRepository(TransactionalContext autoTx) {
        super(autoTx, "numeroMecanografico");
    }

    public JpaColaboradorRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "numeroMecanografico");
    }

    @Override
    public Optional<Colaborador> findByUsername(String name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", Username.valueOf(name));
        return matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public Optional<Colaborador> findByNumeroMecanografico(Long number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.numeroMeconografico=:number", params);
    }

    @Override
    public Iterable<Colaborador> findAllActive() {
        return match("e.systemUser.active = true");
    }

    @Override
    public List<Colaborador> findAllColaboradoresByEquipa(Long equipaId) {
        final TypedQuery<Colaborador> query = createQuery("SELECT e.colaboradores FROM Equipa e" +
                "WHERE c.identificador = :identificador", Colaborador.class);

        query.setParameter("identificador", equipaId);

        return query.getResultList();
    }

}
