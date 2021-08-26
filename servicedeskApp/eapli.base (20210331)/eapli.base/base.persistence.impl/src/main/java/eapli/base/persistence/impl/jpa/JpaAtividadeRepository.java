package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.domain.DescricaoBreve;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaAtividadeRepository
        extends JpaAutoTxRepository<Atividade, Long, Long>
        implements AtividadeRepository {

    public JpaAtividadeRepository(TransactionalContext autoTx) {
        super(autoTx, "identificador");
    }

    public JpaAtividadeRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identificador");
    }
    @Override
    public Iterable<Atividade> allAtividadesAutomaticas() {

        Iterable<Atividade> match = match("ATIVIDADE_TYPE like 'Automatica'");

        return  match;
    }

    @Override
    public Optional<Atividade> atividadeByDescricao(String descricaoBreve) {
        final Map<String, Object> params = new HashMap<>();
        params.put("descricaoBreve", DescricaoBreve.valueOf(descricaoBreve));
        return matchOne("e.descricaoBreve=:descricaoBreve", params);
    }

    @Override
    public Iterable<Atividade> allAtividadesManuais() {

        Iterable<Atividade> match = match("ATIVIDADE_TYPE like 'Manual'");
        return  match;
    }
}
