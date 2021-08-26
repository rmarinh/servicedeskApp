package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.teammanagement.domain.Acronimo;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.*;
import java.util.*;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaEquipaRepository extends JpaAutoTxRepository<Equipa, Long, Long>
        implements EquipaRepository {

    public JpaEquipaRepository(TransactionalContext autoTx) {
        super(autoTx, "identificador");
    }

    public JpaEquipaRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identificador");
    }

    @Override
    public Iterable<Colaborador> findAllColaboradoresEquipa(Long equipaID) {
        Optional<Equipa> equipa = findById(equipaID);
        Set<Colaborador> colaborador = equipa.get().colaboradores();

        return colaborador;
    }

    @Override
    public Optional<Equipa> findById(Long number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.identificador=:number", params);
    }

    @Override
    public Optional<Equipa> findByAcronimo(String acronimo) {
        final Map<String, Object> params = new HashMap<>();
        params.put("acronimo", Acronimo.valueOf(acronimo));
        return matchOne("e.acronimo=:acronimo", params);
    }

    @Override
    public Iterable<Equipa> findAllEquipaByColaborador(Colaborador colab) {
        final TypedQuery<Equipa> query = createQuery("SELECT e.*" +
                "FROM Equipa e, EQUIPA_COLABORADORES ec" +
                "WHERE e.IDENTIFICADOR = ec.EQUIPA_IDENTIFICADOR" +
                "AND ec.COLABORADORES_NUMEROMECANOGRAFICO = 14", Equipa.class);

        return query.getResultList();
    }

    @Override
    public List<Equipa> findAllEquipaByCatalogo(Long catalogoId) {

        final TypedQuery<Catalogo> query = createQuery("SELECT c FROM Catalogo c " +
                "WHERE c.identificador = :identificador", Catalogo.class);

        query.setParameter("identificador", catalogoId);

        List<Catalogo> listCatalogo = query.getResultList();
        List<Equipa> listEquipa = new ArrayList<>();

        for (Catalogo c : listCatalogo) {
            listEquipa.addAll(c.equipasResolucao());
        }

        return listEquipa;
    }
}