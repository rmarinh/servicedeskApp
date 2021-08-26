package eapli.base.atividademanagement.repositories;

import eapli.base.atividademanagement.domain.Atividade;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface AtividadeRepository
        extends DomainRepository<Long, Atividade> {

    /**
     * returns the client user (utente) whose username is given
     *
     * @param name
     *            the username to search for
     * @return
     */
    //Optional<Servico> findByPalavr(CodigoUnico codigoUnico);

    public Iterable<Atividade> allAtividadesAutomaticas();
    public Iterable<Atividade> allAtividadesManuais();
    public Optional<Atividade> atividadeByDescricao(String descricao);


}
