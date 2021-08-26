package eapli.base.atividademanagement.application;

import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.atividademanagement.domain.DescricaoBreve;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.domain.*;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.formulario.ScriptValidacao;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UseCaseController
public class CriarTarefasManuaisDefaultController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AtividadeRepository atividadeRepository = PersistenceContext.repositories().atividade();

    private AtividadeManual criarTarefaManualDefault(String descricaoDreve, String nomeFormulario, Set<Atributo> atributos, String script , Calendar createdOn) {
        Formulario form = new Formulario(nomeFormulario, atributos, ScriptValidacao.valueOf("teste"));

        AtividadeManual atividadeManual = new AtividadeManual(new DescricaoBreve(descricaoDreve), form);
        return atividadeRepository.save(atividadeManual);
    }

    public AtividadeManual criarTarefaManualDefault(String descricaoDreve, String nomeFormulario, Set<Atributo> atributos, String script) {
        return criarTarefaManualDefault(descricaoDreve, nomeFormulario, atributos,script, Calendars.now());
    }
}
