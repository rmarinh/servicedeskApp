package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.repositories.SignupRequestRepository;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
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
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {


    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public CatalogoRepository catalogo(final TransactionalContext tx) {
        return new JpaCatalogoRepository(tx);
    }

    @Override
    public CatalogoRepository catalogo() {
        return new JpaCatalogoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AtividadeRepository atividade(final TransactionalContext tx) {
        return new JpaAtividadeRepository(tx);
    }

    @Override
    public AtividadeRepository atividade() {
        return new JpaAtividadeRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public EquipaRepository equipa(final TransactionalContext tx) {
        return new JpaEquipaRepository(tx);
    }

    @Override
    public EquipaRepository equipa() {
        return new JpaEquipaRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public NiveisCriticidadeRepository niveisCriticidade() {
        return new JpaNiveisCriticidadeRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public NiveisCriticidadeRepository niveisCriticidade(TransactionalContext tx) {
        return new JpaNiveisCriticidadeRepository(tx);
    }

    @Override
    public ServicoRepository servicos(final TransactionalContext tx) {
        return new JpaServicoRepository(tx);
    }

    @Override
    public ServicoRepository servicos() {
        return new JpaServicoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public PedidoRepository pedidos() {
        return new JpaPedidoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public PedidoRepository pedidos(final TransactionalContext tx) {
        return new JpaPedidoRepository(tx);
    }

    @Override
    public PedidoRascunhoRepository pedidosRascunho() {
        return new JpaPedidoRascunhoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public ColaboradorRepository colaboradores(TransactionalContext autoTx) {
        return new JpaColaboradorRepository(autoTx);
    }

    @Override
    public ColaboradorRepository colaboradores() {
        return new JpaColaboradorRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public EquipaTypeRepository equipaTypes() {
        return new JpaEquipaTypeRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public EquipaTypeRepository equipaTypes(final TransactionalContext tx) {
        return new JpaEquipaTypeRepository(tx);
    }


    @Override
    public AvaliacaoPedidoRepository avaliacao() {
        return new JpaAvaliacaoPedidoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AvaliacaoPedidoRepository avaliacao(TransactionalContext tx) {
        return new JpaAvaliacaoPedidoRepository(tx);
    }

}