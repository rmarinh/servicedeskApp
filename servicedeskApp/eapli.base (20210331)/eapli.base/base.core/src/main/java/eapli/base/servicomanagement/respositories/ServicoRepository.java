package eapli.base.servicomanagement.respositories;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.servicomanagement.domain.servico.CodigoUnico;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface ServicoRepository
        extends DomainRepository<CodigoUnico, Servico> {

    /**
     * returns the client user (utente) whose username is given
     *
     * @param name
     *            the username to search for
     * @return
     */
    //Optional<Servico> findByPalavr(CodigoUnico codigoUnico);

    /**
     * returns the client user (utente) with the given mecanographic number
     *
     * @param codigoUnico
     * @return
     */
    default Optional<Servico> findByCodigoUnico(CodigoUnico codigoUnico) {
        return ofIdentity(codigoUnico);
    }
    public Iterable<Servico> findServicosCompletos();
    public Iterable<Servico> findServicosInCompletos();
    public Iterable<Servico> findServicoByCatalogo(Catalogo catalogo);
    Optional<Servico> findById(String codUnico);
}
