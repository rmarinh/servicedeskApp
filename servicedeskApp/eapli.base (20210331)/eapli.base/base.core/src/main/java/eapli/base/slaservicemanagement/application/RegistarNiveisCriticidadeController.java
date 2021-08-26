package eapli.base.slaservicemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.slaservicemanagement.domain.*;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;

public class RegistarNiveisCriticidadeController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * @param cor                  cor
     * @param escalaNumerica       escala numerica
     * @param etiqueta             etiqueta
     * @param tempoMaximoAprovacao tempo Maximo
     * @param tempoMedioAprovacao  tempo medio
     * @param tempoMaximoResolucao tempo Maximo de resolucao
     * @param tempoMedioResolucao  tempo medio de resolucao
     * @param createdOn            data de criação
     * @return nivel de criticidade
     */
    public NiveisCriticidade registarNiveisCriticidade(String cor, String escalaNumerica, String etiqueta,
                                                       String tempoMaximoAprovacao, String tempoMedioAprovacao, String tempoMaximoResolucao, String tempoMedioResolucao, Calendar createdOn) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_HELPDESK, BaseRoles.POWER_USER, BaseRoles.RECURSOS_HUMANO);

        NiveisCriticidade nc = new NiveisCriticidade(Cor.valueOf(cor), new EscalaNumerica(escalaNumerica),
                new Etiqueta(etiqueta), new TempoMaximoAprovacao(tempoMaximoAprovacao), new TempoMedioAprovacao(tempoMedioAprovacao),
                new TempoMaximoResolucao(tempoMaximoResolucao), new TempoMedioResolucao(tempoMedioResolucao));

        NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();

        return niveisCriticidadeRepository.save(nc);
    }

    /**
     * @param cor                  cor
     * @param escalaNumerica       escala numerica
     * @param etiqueta             etiqueta
     * @param tempoMaximoAprovacao tempo Maximo de aprovacao
     * @param tempoMedioAprovacao  tempo medio de aprovacao
     * @param tempoMaximoResolucao tempo Maximo de resolucao
     * @param tempoMedioResolucao  tempo medio de resolucao
     * @return nivel de criticidade
     */
    public NiveisCriticidade registarNiveisCriticidade(String cor, String escalaNumerica,
                                                       String etiqueta, String tempoMaximoAprovacao,
                                                       String tempoMedioAprovacao, String tempoMaximoResolucao, String tempoMedioResolucao) {
        return registarNiveisCriticidade(cor, escalaNumerica, etiqueta, tempoMaximoAprovacao, tempoMedioAprovacao, tempoMaximoResolucao, tempoMedioResolucao, Calendars.now());
    }
}

