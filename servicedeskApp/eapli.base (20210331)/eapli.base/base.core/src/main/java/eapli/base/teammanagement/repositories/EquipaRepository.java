package eapli.base.teammanagement.repositories;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.Acronimo;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;

/**
 * This
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public interface EquipaRepository extends DomainRepository<Long, Equipa> {

    public Iterable<Equipa> findAll();

    public Iterable<Colaborador> findAllColaboradoresEquipa(Long equipaID);

    public Optional<Equipa> findByAcronimo(String acronimo);

    public Iterable<Equipa> findAllEquipaByColaborador(Colaborador colab);

    /**
     * returns the equipa whose id is given
     *
     * @param id of Equipa to search for
     * @return
     */
    default Optional<Equipa> findById(Long id) {
        return ofIdentity(id);
    }

    public List<Equipa> findAllEquipaByCatalogo(Long catalogoId);
}
