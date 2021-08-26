package eapli.base.persistence.impl.inmemory;

import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.colaboradormanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidomanagement.repositories.AvaliacaoPedidoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {
    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public CatalogoRepository catalogo(final TransactionalContext tx) {
        return new InMemoryCatalogoRepository();
    }

    @Override
    public CatalogoRepository catalogo() {
        return catalogo(null);
    }

    @Override
    public EquipaRepository equipa(TransactionalContext tx) {
        return new InMemoryEquipaRepository();
    }

    @Override
    public ServicoRepository servicos() {
        return null;
    }

    @Override
    public ServicoRepository servicos(TransactionalContext tx) {
        return new InMemoryServicoRepository();
    }

    @Override
    public PedidoRepository pedidos() {
        return null;
    }

    @Override
    public PedidoRepository pedidos(TransactionalContext tx) {
        return new InMemoryPedidoRepository();
    }

    @Override
    public EquipaRepository equipa() {
        return equipa(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public EquipaTypeRepository equipaTypes() {
        return equipaTypes(null);
    }

    @Override
    public EquipaTypeRepository equipaTypes(TransactionalContext tx) {
        return new InMemoryEquipaTypeRepository();
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public ColaboradorRepository colaboradores(final TransactionalContext tx) {
        return new InMemoryColaboradorRepository();
    }

    @Override
    public ColaboradorRepository colaboradores() {
        return colaboradores(null);
    }

    @Override
    public AtividadeRepository atividade(TransactionalContext tx) {
        return null;
    }

    @Override
    public AtividadeRepository atividade() {
        return null;
    }

    @Override
    public PedidoRascunhoRepository pedidosRascunho() {
        return null;
    }

    @Override
    public NiveisCriticidadeRepository niveisCriticidade() {
        return niveisCriticidade(null);
    }

    @Override
    public NiveisCriticidadeRepository niveisCriticidade(final TransactionalContext tx) {
        return new InMemoryNiveisCriticidadeRepository();
    }

    @Override
    public AvaliacaoPedidoRepository avaliacao() {
        return null;
    }

    @Override
    public AvaliacaoPedidoRepository avaliacao(TransactionalContext tx) {
        return null;
    }
}

