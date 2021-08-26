package eapli.base.pedidomanagement.application;

import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.catalogoservicemanagement.domain.*;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.formulario.Atributo;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.AprovadoPedidoEvent;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.base.formulario.Formulario;
import eapli.base.servicomanagement.domain.servico.PalavraChave;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.hibernate.annotations.Formula;

import java.util.*;

/**
 * @author Rui Marinho
 */
@UseCaseController
public class RegistarPedidoController {

    private final PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servicos();
    private final PedidoRascunhoRepository pedidoRascunhoRepository = PersistenceContext.repositories().pedidosRascunho();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Pedido registerPedido(ColaboradorDTO colaboradorDTO, ServicoDTO servicoDTO) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);
        Colaborador responsavel = colaboradorRepository.findByUsername(colaboradorDTO.username).get();
        Servico servico = servicoRepository.findById(servicoDTO.codUnico).get();

        Pedido pedido = new Pedido(responsavel, servico);
        return pedidoRepository.save(pedido);
    }

    public Pedido registerPedido(ColaboradorDTO colaboradorDTO, ServicoDTO servicoDTO, PedidoRascunho pedidoRascunho) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);
        Colaborador responsavel = colaboradorRepository.findByUsername(colaboradorDTO.username).get();
        Servico servico = servicoRepository.findById(servicoDTO.codUnico).get();

        Pedido pedido = new Pedido(responsavel, servico,pedidoRascunho.formularioPedido(), pedidoRascunho.ficheirosEmAnexo());
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public boolean emAprovacaoPedido(Pedido pedido) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        Username user = authz.session().get().authenticatedUser().username();

        Colaborador colaborador = colaboradorRepository.findByUsername(user.toString()).get();

        pedido.emAprovacaoPedidoEvent(colaborador);

        this.updatePedido(pedido);
        return true;
    }

    public Set<Atributo> atributosFormularioAprovacao (PedidoDTO pedidoDTO){
        Pedido pedido = pedidoRepository.findById(pedidoDTO.pk).get();
        Set<Atributo> atributos = pedido.formularioAprovacao().atributos();
        if (atributos == null){
            return new LinkedHashSet<Atributo>();
        }else{
            return atributos;
        }

    }

    public boolean aprovarPedido(Pedido pedido) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        Username user = authz.session().get().authenticatedUser().username();

        Colaborador colaborador = colaboradorRepository.findByUsername(user.toString()).get();

        pedido.aprovarPedido(colaborador);

        this.updatePedido(pedido);
        return true;
    }



    public boolean aprovarPedido(Pedido pedido, Set<Atributo> atributosFormularioAprovacao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        Username user = authz.session().get().authenticatedUser().username();
        Colaborador colaborador = colaboradorRepository.findByUsername(user.toString()).get();
        Formulario form = new Formulario(pedido.formularioAprovacao().nome(), atributosFormularioAprovacao, pedido.formularioAprovacao().scriptValidacao());
        AprovadoPedidoEvent event = pedido.aprovarPedido(colaborador, Formulario.formularioToXML(form));
        if(event == null){
            return false;
        }else {
            this.updatePedido(pedido);
            return true;
        }
    }

    public boolean executorPedido(Pedido pedido) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        Username user = authz.session().get().authenticatedUser().identity();

        Colaborador colaborador = colaboradorRepository.findByUsername(user.toString()).get();
        pedido.executarPedidoEvent(colaborador);

        this.updatePedido(pedido);
        return true;
    }

    public boolean concluirPedido(Pedido pedido) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        pedido.concluirPedidoEvent();

        this.updatePedido(pedido);
        return true;
    }

    public boolean concluirPedido(Pedido pedido, Set<Atributo> atributosFormularioExecucao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        String nome = pedido.formularioExecucao().nome();
        Formulario tmp = new Formulario(nome, atributosFormularioExecucao, pedido.formularioExecucao().scriptValidacao());
        pedido.concluirPedidoEvent( Formulario.formularioToXML(tmp));

        this.updatePedido(pedido);
        return true;
    }
    public Set<Atributo> atributosFormularioExecucao (PedidoDTO pedidoDTO){
        Pedido pedido = pedidoRepository.findById(pedidoDTO.pk).get();
        return pedido.formularioAprovacao().atributos();
    }


    public void recusarPedido(Pedido pedido) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER,
                BaseRoles.USER,BaseRoles.GESTOR_HELPDESK);
        Username user = authz.session().get().authenticatedUser().identity();

        Colaborador colaborador = colaboradorRepository.findByUsername(user.toString()).get();
        pedido.recusarPedido(colaborador);

        this.updatePedido(pedido);
    }
}
