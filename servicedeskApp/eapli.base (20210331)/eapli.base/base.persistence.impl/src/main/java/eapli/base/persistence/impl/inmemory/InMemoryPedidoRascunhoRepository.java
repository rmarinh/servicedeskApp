package eapli.base.persistence.impl.inmemory;

import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class InMemoryPedidoRascunhoRepository
        extends InMemoryDomainRepository<PedidoRascunho, Long>
        implements PedidoRascunhoRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<PedidoRascunho> findAllPedidosRascunho() {
        return null;
    }

    @Override
    public Iterable<PedidoRascunho> meusPedidosRascunho(Long number) {
        return null;
    }

    @Override
    public Iterable<PedidoRascunho> meusPedidosRascunhoEmEstadoRascunho(Long number) {
        return null;
    }

    @Override
    public PedidoRascunho findPedidoRascunhoById(Long pk) {
        return null;
    }
}
