package eapli.base.teammanagement.repositories;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.EquipaType;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface EquipaTypeRepository extends DomainRepository<Long, EquipaType> {

    /**
     * Returns the active dish types.
     *
     * @return An iterable for DishType.
     */
    Iterable<EquipaType> activeEquipaTypes();

    public Optional<EquipaType> findById(Long id);

    public Optional<EquipaType> findByDescricao(String descricao);
}
