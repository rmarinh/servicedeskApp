package eapli.base.persistence.impl.inmemory;

import eapli.base.teammanagement.domain.EquipaType;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class InMemoryEquipaTypeRepository
        extends InMemoryDomainRepository<EquipaType, Long>
        implements EquipaTypeRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<EquipaType> activeEquipaTypes() {
        return null;
    }

    @Override
    public Optional<EquipaType> findById(Long id) {
        return Optional.empty();
    }

    public Optional<EquipaType> findByDescricao(String descricao) {
        return null;
    }
}
