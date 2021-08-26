package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalRepository;

/**
 * a base class for all transactional repositories to use the same persistence
 * unit
 *
 * @param <T>
 * @param <K>
 *
 * @author Paulo Gandra de Sousa
 */
/* package */ class BaseJpaRepositoryBase<T, K, I>
        extends JpaTransactionalRepository<T, K, I> {

    BaseJpaRepositoryBase(String persistenceUnitName, String identityFieldName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(),
                identityFieldName);
    }

    BaseJpaRepositoryBase(String identityFieldName) {
        super(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties(), identityFieldName);
    }
}
