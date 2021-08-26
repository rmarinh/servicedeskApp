package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.SignupRequest;
import eapli.base.colaboradormanagement.repositories.SignupRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaSignupRequestRepository extends JpaAutoTxRepository<SignupRequest, Username, Username>
        implements SignupRequestRepository {

    public JpaSignupRequestRepository(TransactionalContext autoTx) {
        super(autoTx, "username");
    }

    public JpaSignupRequestRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "username");
    }

    @Override
    public Iterable<SignupRequest> pendingSignupRequests() {
        return match(
                "e.approvalStatus=eapli.base.clientusermanagement.domain"
                        + ".ApprovalStatus.PENDING");
    }

    public List<Colaborador> metodoFixe() {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("Colaborador");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery(
                "SELECT s FROM Colaborador s");
        List<Colaborador> list = query.getResultList();
        return list;
    }
}