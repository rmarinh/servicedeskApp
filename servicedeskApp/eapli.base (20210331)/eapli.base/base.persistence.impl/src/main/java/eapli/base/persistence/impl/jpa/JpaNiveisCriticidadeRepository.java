package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.catalogoservicemanagement.domain.Titulo;
import eapli.base.slaservicemanagement.domain.Etiqueta;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
public class JpaNiveisCriticidadeRepository
        extends JpaAutoTxRepository<NiveisCriticidade, Long, Long>
        implements NiveisCriticidadeRepository {

    public JpaNiveisCriticidadeRepository(TransactionalContext autoTx) {
        super(autoTx, "identificador");
    }

    public JpaNiveisCriticidadeRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identificador");
    }

    @Override
    public Optional<NiveisCriticidade> findByEtiqueta(String etiqueta) {
        final Map<String, Object> params = new HashMap<>();
        params.put("etiqueta", Etiqueta.valueOf(etiqueta));
        return matchOne("e.etiqueta=:etiqueta", params);
    }

    @Override
    public Optional<NiveisCriticidade> findById(Long identificador) {
        final Map<String, Object> params = new HashMap<>();
        params.put("identificador", identificador);
        return matchOne("e.identificador=:identificador", params);
    }
}
