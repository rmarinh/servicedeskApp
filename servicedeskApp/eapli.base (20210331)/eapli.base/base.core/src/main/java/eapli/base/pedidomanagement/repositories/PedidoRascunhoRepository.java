package eapli.base.pedidomanagement.repositories;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rui Marinho
 */
public interface PedidoRascunhoRepository
        extends DomainRepository<Long, PedidoRascunho> {

    public Iterable<PedidoRascunho> findAllPedidosRascunho();
    public Iterable<PedidoRascunho> meusPedidosRascunho(Long number);

    public Iterable<PedidoRascunho> meusPedidosRascunhoEmEstadoRascunho(Long number);

    public PedidoRascunho findPedidoRascunhoById(Long pk);
}
