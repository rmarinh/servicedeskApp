package eapli.base.servicomanagement.application;

import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.formulario.Formulario;
import eapli.base.servicomanagement.domain.servico.PalavraChave;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.domain.servico.ServicoBuilder;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicomanagement.respositories.ServicoRepository;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rui Marinho
 */
@UseCaseController
public class RegistarServicoController {
//Continuar anterior

// Definir Novo

// A medida que vai andando vai gravando , grava apenas o q tem


    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servicos();
    private final ServicoBuilder builder = new ServicoBuilder();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListCatalogoController catalogoController = new ListCatalogoController();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    public Servico registerServicoIncompleto(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve, String descricaoCompleta, String icone, Set<PalavraChave> keywords) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        // Making sure that this coverage with these features already exists
        //if (this.servicoRepository.findByIdentifierNumber(new ServicoIdentifier(identifier)).isPresent()) {
        //    throw new IntegrityViolationException("Sorry, servico already exist.");
        //«}

        builder.withCatalogo(catalogo);
        builder.withCodigoUnico(codigoUnico);
        builder.withTitulo(titulo);
        builder.withDescricaoBreve(descricaoBreve);
        builder.withDescricaoCompleta(descricaoCompleta);
        builder.withIcone(icone);
        builder.withPalavrasChaves(keywords);
        builder.withStatusIncompleted();

        Servico servico = builder.build();

        return servicoRepository.save(servico);
    }

    public Servico registerServicoIncompleto(Servico servico, Formulario formulario) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        // Making sure that this coverage with these features already exists
        //if (this.servicoRepository.findByIdentifierNumber(new ServicoIdentifier(identifier)).isPresent()) {
        //    throw new IntegrityViolationException("Sorry, servico already exist.");
        //«}

        builder.withCatalogo(servico.catalogo());
        builder.withCodigoUnico(servico.codigoUnico());
        builder.withTitulo(servico.toDTO().titulo);
        builder.withDescricaoBreve(servico.toDTO().descBreve);
        builder.withDescricaoCompleta(servico.toDTO().descCompleta);
        builder.withIcone(servico.toDTO().icone);
        builder.withPalavrasChaves(servico.palavraChaves());
        builder.withFormulario(formulario);
        builder.withStatusCompleted();

        Servico s = builder.build();

        servicoRepository.delete(servico);

        return servicoRepository.save(s);

    }

    // Testing Fluxo Atividade DEPRECATED
    public Servico registerServicoCompleto(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve,
                                             String descricaoCompleta, String icone, Set<PalavraChave> keywords, Formulario formulario,
                                           String cargoResponsavel, Atividade atividadeAprovacao, Atividade atividadeResolucao) {


        builder.withCatalogo(catalogo);
        builder.withCodigoUnico(codigoUnico);
        builder.withTitulo(titulo);
        builder.withDescricaoBreve(descricaoBreve);
        builder.withDescricaoCompleta(descricaoCompleta);
        builder.withIcone(icone);
        builder.withPalavrasChaves(keywords);
        builder.withFormulario(formulario);
        builder.withFluxoAprovacao(cargoResponsavel, atividadeAprovacao);
        builder.withFluxoResolucao(atividadeResolucao);
        builder.withStatusCompleted();

        Servico servico = builder.build();

        return servicoRepository.save(servico);
    }

    // Testing Fluxo Atividade
    public Servico registerServicoCompleto(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve,
                                           String descricaoCompleta, String icone, Set<PalavraChave> keywords, Formulario form,
                                           Set<ColaboradorDTO> aprovadores, Atividade atividadeAprovacao, Atividade atividadeResolucao) {
        HashSet<Colaborador> colabAprovadoresSet = new HashSet<>();
        for (ColaboradorDTO cDTO : aprovadores){
            colabAprovadoresSet.add(colaboradorRepository.findByUsername(cDTO.username).get());
        }

        builder.withCatalogo(catalogo);
        builder.withCodigoUnico(codigoUnico);
        builder.withTitulo(titulo);
        builder.withDescricaoBreve(descricaoBreve);
        builder.withDescricaoCompleta(descricaoCompleta);
        builder.withIcone(icone);
        builder.withPalavrasChaves(keywords);
        builder.withFormulario(form);
        builder.withFluxoAprovacao(colabAprovadoresSet, atividadeAprovacao);
        builder.withFluxoResolucao(atividadeResolucao);
        builder.withStatusCompleted();

        Servico servico = builder.build();

        return servicoRepository.save(servico);
    }

    public Servico registerServicoCompleto(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve, String descricaoCompleta, String icone, Formulario formulario) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER);

        builder.withCatalogo(catalogo);
        builder.withCodigoUnico(codigoUnico);
        builder.withTitulo(titulo);
        builder.withDescricaoBreve(descricaoBreve);
        builder.withDescricaoCompleta(descricaoCompleta);
        builder.withIcone(icone);
        builder.withFormulario(formulario);
        builder.withStatusCompleted();

        Servico servico = builder.build();

        return servicoRepository.save(servico);
    }
}
