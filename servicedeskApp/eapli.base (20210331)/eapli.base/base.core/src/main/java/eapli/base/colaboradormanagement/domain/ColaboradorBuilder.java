package eapli.base.colaboradormanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.time.util.Calendars;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A factory for User entities.
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
public class ColaboradorBuilder implements DomainFactory<Colaborador> {

    private SystemUser systemUser;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String dataNascimento;
    private String localResidencia;
    private String numeroContacto;
    private Colaborador responsavelHierarquico;
    ;
    private Calendar createdOn;

    public ColaboradorBuilder() {
    }

    public ColaboradorBuilder withData(final String username, final String rawPassword,
                                       final String email, final String number,
                                       final String firstName, final String LastName, final String dataNascimento,
                                       final String localResidencia, final String numeroContacto,
                                       final Colaborador responsavelHierarquico) {
        withUsername(username);
        withPassword(rawPassword);
        withEmail(email);
        withName(firstName, LastName);
        withEmail(email);
        withDataNascimento(dataNascimento);
        withLocalResidencia(localResidencia);
        withNumeroContacto(numeroContacto);
        withResponsavelHierarquico(responsavelHierarquico);
        return this;
    }

    public ColaboradorBuilder withSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public ColaboradorBuilder withUsername(final String username) {
        this.username = username;
        return this;
    }

    public ColaboradorBuilder withPassword(final String rawPassword) {
        this.password = rawPassword;
        return this;
    }

    public ColaboradorBuilder withName(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public ColaboradorBuilder withEmail(final String email) {
        this.email = email;
        return this;
    }

    public ColaboradorBuilder withDataNascimento(final String dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ColaboradorBuilder withLocalResidencia(final String localResidencia) {
        this.localResidencia = localResidencia;
        return this;
    }

    public ColaboradorBuilder withNumeroContacto(final String numeroContacto) {
        this.numeroContacto = numeroContacto;
        return this;
    }

    public ColaboradorBuilder withResponsavelHierarquico(Colaborador responsavelHierarquico) {
        this.responsavelHierarquico = responsavelHierarquico;
        return this;
    }

    public ColaboradorBuilder createdOn(final Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public Colaborador build() {

        if (createdOn != null) {
            createdOn = Calendars.now();
        }


        return new Colaborador(systemUser,
                NomeCompleto.valueOf(Name.valueOf(firstName, lastName).toString())
                , DataNascimento.valueOf(dataNascimento),
                LocalResidencia.valueOf(localResidencia), NumeroContacto.valueOf(numeroContacto),
                responsavelHierarquico);
    }
}
