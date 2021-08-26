package eapli.base.persistence.impl.inmemory;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class InMemoryCatalogoRepository
        extends InMemoryDomainRepository<Catalogo, Long>
        implements CatalogoRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Catalogo> findAllByEquipa(Equipa equipa) {
        return null;
    }

    @Override
    public Optional<Catalogo> findByTitulo(String titulo) {
        return Optional.empty();
    }

    @Override
    public Iterable<Catalogo> findCatalogosGrantedToColaborador(Colaborador colaborador) {
        return null;
    }
}
