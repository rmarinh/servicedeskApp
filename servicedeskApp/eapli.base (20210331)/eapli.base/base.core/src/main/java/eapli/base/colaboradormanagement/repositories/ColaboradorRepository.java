package eapli.base.colaboradormanagement.repositories;

import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
public interface ColaboradorRepository
        extends DomainRepository<Long, Colaborador> {

    /**
     * returns the colaborador whose username is given
     *
     * @param name the username to search for
     * @return
     */
    Optional<Colaborador> findByUsername(String name);

    /**
     * returns the colaborador with the given numero mecanografico
     *
     * @param number
     * @return
     */
    default Optional<Colaborador> findByNumeroMecanografico(Long number) {
        return ofIdentity(number);
    }

    public Iterable<Colaborador> findAllActive();

    public List<Colaborador> findAllColaboradoresByEquipa(Long equipaId);
}
