package eapli.base.slaservicemanagement.repositories;

import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 *
 *
 * This
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
public interface NiveisCriticidadeRepository extends DomainRepository<Long, NiveisCriticidade> {

    public Iterable<NiveisCriticidade> findAll();

    public Optional<NiveisCriticidade> findById(Long identifier);

    public Optional<NiveisCriticidade> findByEtiqueta(String identifier);
}
