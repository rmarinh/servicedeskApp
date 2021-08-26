package eapli.base.persistence.impl.inmemory;


import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.servicomanagement.domain.servico.CodigoUnico;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class InMemoryServicoRepository
        extends InMemoryDomainRepository<Servico, CodigoUnico>
        implements ServicoRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Servico> findServicosCompletos() {
        return null;
    }

    @Override
    public Iterable<Servico> findServicosInCompletos() {
        return null;
    }

    @Override
    public Iterable<Servico> findServicoByCatalogo(Catalogo idCatalogo) { return null; }

    @Override
    public Optional<Servico> findById(String codUnico) {
        return Optional.empty();
    }
}
