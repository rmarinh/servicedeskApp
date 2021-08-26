package eapli.base.atividademanagement.application;

import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.atividademanagement.domain.DescricaoBreve;
import eapli.base.atividademanagement.domain.Script;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;
import java.util.List;

@UseCaseController
public class CriarTarefasAutomaticasDefaultController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AtividadeRepository atividadeRepository = PersistenceContext.repositories().atividade();

    private AtividadeAutomatica criarTarefaAutomaticaDefault(String descricaoDreve, Script script,Calendar createdOn) {

        AtividadeAutomatica atividadeAutomatica = new AtividadeAutomatica(new DescricaoBreve(descricaoDreve), script);
        return atividadeRepository.save(atividadeAutomatica);
    }

    public AtividadeAutomatica criarTarefaAutomaticaDefault(String descricaoDreve, Script script) {
        return criarTarefaAutomaticaDefault(descricaoDreve, script, Calendars.now());
    }
}
