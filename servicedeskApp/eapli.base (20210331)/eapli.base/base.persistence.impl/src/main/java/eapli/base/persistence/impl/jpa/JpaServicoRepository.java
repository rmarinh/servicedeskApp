package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.domain.servico.CodigoUnico;

import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.*;
import java.util.*;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaServicoRepository
        extends JpaAutoTxRepository<Servico, CodigoUnico, CodigoUnico>
        implements ServicoRepository {

    public JpaServicoRepository(TransactionalContext autoTx) {
        super(autoTx, "codigoUnico");
    }

    public JpaServicoRepository(String codigoUnico) {
        super(codigoUnico, Application.settings().getExtendedPersistenceProperties(), "codigoUnico");
    }

    public EntityManager em;

    @Override
    public Iterable<Servico> findServicoByCatalogo(Catalogo idCatalogo) {
        return match("CATALOGO_IDENTIFICADOR=:idCatalogo", "idCatalogo", idCatalogo);
    }

    @Override
    public Iterable<Servico> findServicosCompletos() {
        return match("ESTADOSERVICO = 'COMPLETO'");
    }

    @Override
    public Iterable<Servico> findServicosInCompletos() {
        return match("ESTADOSERVICO = 'INCOMPLETO'");
    }


    @Override
    public Optional<Servico> findById(String codUnico) {
        final Map<String, Object> params = new HashMap<>();
        params.put("codigoUnico", new CodigoUnico(codUnico));
        return matchOne("e.codigoUnico=:codigoUnico", params);
    }
}