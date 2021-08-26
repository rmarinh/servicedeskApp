package eapli.base.colaboradormanagement.application;


import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.ColaboradorBuilder;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.Calendars;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Set;

public class AddColaboradorController {

    private final UserManagementService userService = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext txCtx = PersistenceContext.repositories()
            .newTransactionalContext();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext
            .repositories().colaboradores();

    private Colaborador addColaborador(final String username, final String password,
                                       final String firstName, final String lastName,
                                       final String email, final String dataNascimento,
                                       final String localResidencia, final String numeroContacto, final Set<Role> roles,
                                       final Calendar createdOn, final Colaborador responsavel) throws ParseException {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        SystemUser newUser = userService.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);

        final ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();

        colaboradorBuilder.withUsername(username).withName(firstName, lastName).
                withEmail(email).withDataNascimento(dataNascimento).
                withLocalResidencia(localResidencia).
                withNumeroContacto(numeroContacto).
                createdOn(createdOn).
                withPassword(password).withSystemUser(newUser).
                withResponsavelHierarquico(responsavel);

        Colaborador colaborador = colaboradorBuilder.build();

        return this.colaboradorRepository.save(colaborador);
    }

    public Colaborador addColaborador(final String username, final String password,
                                      final String firstName, final String lastName, final String email, final String date,
                                      final String localResidencia, final String numeroContacto, Set<Role> roles,
                                      final ColaboradorDTO responsavelDTO) throws ParseException {

        //Optional<Colaborador> responsavel = colaboradorRepository.findByUsername(responsavelDTO.username);

        return addColaborador(username, password, firstName, lastName, email, date,
                localResidencia, numeroContacto, roles, Calendars.now(), null);
    }

}

