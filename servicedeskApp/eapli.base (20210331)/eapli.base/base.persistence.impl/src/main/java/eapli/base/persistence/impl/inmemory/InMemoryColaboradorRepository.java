package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
public class InMemoryColaboradorRepository
        extends InMemoryDomainRepository<Colaborador, Long>
        implements ColaboradorRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Colaborador> findByUsername(String name) {
        return matchOne(e -> e.user().username().equals(name));
    }

    @Override
    public Optional<Colaborador> findByNumeroMecanografico(Long number) {
        return Optional.of(data().get(number));
    }

    @Override
    public Iterable<Colaborador> findAllActive() {
        return match(e -> e.user().isActive());
    }

    @Override
    public List<Colaborador> findAllColaboradoresByEquipa(Long equipaId) {
        return null;
    }
}
