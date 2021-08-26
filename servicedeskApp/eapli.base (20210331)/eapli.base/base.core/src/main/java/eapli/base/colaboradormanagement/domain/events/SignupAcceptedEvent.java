/**
 *
 */
package eapli.base.colaboradormanagement.domain.events;

import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.colaboradormanagement.domain.SignupRequest;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Rafael Soares 1181882@isep.ipp.pt
 *
 */
public class SignupAcceptedEvent extends DomainEventBase {

    private static final long serialVersionUID = 1L;

    private final SignupRequest theSignupRequest;

    public SignupAcceptedEvent(final SignupRequest theSignupRequest) {
        this.theSignupRequest = theSignupRequest;
    }

    public Username username() {
        return theSignupRequest.username();
    }

    public Password password() {
        return theSignupRequest.password();
    }

    public Name name() {
        return theSignupRequest.name();
    }

    public EmailAddress email() {
        return theSignupRequest.email();
    }

    public NumeroMecanografico numeroMecanografico() {
        return theSignupRequest.numeroMecanografico();
    }

    @Override
    public String toString() {
        return "SignupAccepted(" + username() + ")";
    }
}
