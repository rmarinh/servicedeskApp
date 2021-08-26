package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.domain.Titulo;
import eapli.base.teammanagement.domain.Designacao;
import eapli.base.teammanagement.domain.EquipaType;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaEquipaTypeRepository extends JpaAutoTxRepository<EquipaType, Long, Long>
        implements EquipaTypeRepository {

    public JpaEquipaTypeRepository(TransactionalContext autoTx) {
        super(autoTx, "identificador");
    }

    public JpaEquipaTypeRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identificador");
    }

    @Override
    public Iterable<EquipaType> activeEquipaTypes() {
        return findAll();
    }

    public Optional<EquipaType> findById(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }

    public Optional<EquipaType> findByDescricao(String descricao) {
        final Map<String, Object> params = new HashMap<>();
        params.put("descricao", Designacao.valueOf(descricao));
        return matchOne("e.descricao=:descricao", params);
    }
}