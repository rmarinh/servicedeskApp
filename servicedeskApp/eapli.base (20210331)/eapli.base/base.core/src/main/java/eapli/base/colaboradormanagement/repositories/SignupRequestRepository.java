package eapli.base.colaboradormanagement.repositories;

import eapli.base.colaboradormanagement.domain.SignupRequest;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
public interface SignupRequestRepository extends DomainRepository<Username, SignupRequest> {

    Iterable<SignupRequest> pendingSignupRequests();
}
