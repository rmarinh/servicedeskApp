package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.domain.Titulo;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
public class JpaCatalogoRepository
        extends JpaAutoTxRepository<Catalogo, Long, Long>
        implements CatalogoRepository {

    public JpaCatalogoRepository(TransactionalContext autoTx) {
        super(autoTx, "identificador");
    }

    public JpaCatalogoRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identificador");
    }

    @Override
    public Iterable<Catalogo> findAll() {
        return super.findAll();
    }

    @Override
    public Iterable<Catalogo> findAllByEquipa(Equipa equipa) {
        final Map<String, Object> params = new HashMap<>();
        params.put("equipa", equipa);
        return match("e.equipa=:equipa", params);
    }

    public Optional<Catalogo> findByTitulo(String titulo) {
        final Map<String, Object> params = new HashMap<>();
        params.put("titulo", Titulo.valueOf(titulo));
        return matchOne("e.titulo=:titulo", params);
    }

    @Override
    public Iterable<Catalogo> findCatalogosGrantedToColaborador(Colaborador colaborador) {
        return  null;
    }

    @Override
    public Optional<Catalogo> findById(Long identificador) {
        final Map<String, Object> params = new HashMap<>();
        params.put("identificador", identificador);
        return matchOne("e.identificador=:identificador", params);
    }
}
