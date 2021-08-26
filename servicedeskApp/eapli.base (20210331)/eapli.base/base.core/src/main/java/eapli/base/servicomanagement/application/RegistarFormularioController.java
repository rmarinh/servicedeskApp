package eapli.base.servicomanagement.application;

import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.formulario.ScriptValidacao;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Set;

public class RegistarFormularioController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Formulario registerFormulario(String nome, Set<Atributo> atributos){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        Formulario form = new Formulario(nome, atributos, ScriptValidacao.valueOf("teste"));

        return form;
    }
}
