package eapli.base.persistence.impl.inmemory;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class InMemoryEquipaRepository
        extends InMemoryDomainRepository<Equipa, Long>
        implements EquipaRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Colaborador> findAllColaboradoresEquipa(Long equipaID) {
        return null;
    }

    @Override
    public Optional<Equipa> findByAcronimo(String acronimo) {
        return Optional.empty();
    }

    @Override
    public Iterable<Equipa> findAllEquipaByColaborador(Colaborador colab) {
        return null;
    }

    @Override
    public List<Equipa> findAllEquipaByCatalogo(Long catalogoId) {
        return null;
    }
}
