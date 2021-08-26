/**
 *
 */
package eapli.base.infrastructure.persistence;

import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.colaboradormanagement.repositories.SignupRequestRepository;
import eapli.base.pedidomanagement.repositories.AvaliacaoPedidoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    CatalogoRepository catalogo(TransactionalContext tx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CatalogoRepository catalogo();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    EquipaTypeRepository equipaTypes();

    EquipaTypeRepository equipaTypes(TransactionalContext tx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    EquipaRepository equipa();

    EquipaRepository equipa(TransactionalContext tx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ServicoRepository servicos();

    ServicoRepository servicos(TransactionalContext tx);

    PedidoRepository pedidos();

    PedidoRepository pedidos(TransactionalContext tx);


    NiveisCriticidadeRepository niveisCriticidade();

    NiveisCriticidadeRepository niveisCriticidade(TransactionalContext tx);

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    ColaboradorRepository colaboradores(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ColaboradorRepository colaboradores();

    AtividadeRepository atividade(TransactionalContext tx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    AtividadeRepository atividade();


    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    PedidoRascunhoRepository pedidosRascunho();

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    AvaliacaoPedidoRepository avaliacao();

    AvaliacaoPedidoRepository avaliacao(TransactionalContext tx);


}
