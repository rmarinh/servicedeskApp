package eapli.base.persistence.impl.inmemory;



import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;


/**
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 */
public class InMemoryNiveisCriticidadeRepository
        extends InMemoryDomainRepository<NiveisCriticidade, Long>
        implements NiveisCriticidadeRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<NiveisCriticidade> findByEtiqueta(String identifier) {
        return Optional.empty();
    }
}
