package eapli.base.pedidomanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.dto.ColaboradorDTOParser;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.formulario.Atributo;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.base.pedidomanagement.domain.UrgenciaPedido;
import eapli.base.formulario.Formulario;
import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRascunhoRepository;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Rui Marinho
 */
@UseCaseController
public class RegistarPedidoRascunhoController {

    private final PedidoRascunhoRepository pedidoRascunhoRepository = PersistenceContext.repositories().pedidosRascunho();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servicos();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    public PedidoRascunho registerPedidoRascunho(ColaboradorDTO autorColabDTO, ColaboradorDTO benificiarioColabDTO , ServicoDTO servicoDTO, UrgenciaPedido urgenciaPedido,
                                                 Calendar dataLimiteResolucao, byte[] ficheirosEmAnexo) {

        //Check if user Has permissions
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RECURSOS_HUMANO, BaseRoles.POWER_USER, BaseRoles.USER);
        //Check if servico is completed
        Servico servico = servicoRepository.findById(servicoDTO.codUnico).get();

        Colaborador autor = colaboradorRepository.findByUsername(autorColabDTO.username).get();
        Colaborador benificiario = colaboradorRepository.findByUsername(benificiarioColabDTO.username).get();
        String formularioPedido = servico.formularioXML();
        PedidoRascunho pedidoRascunho = new PedidoRascunho(autor, benificiario, servico,
                urgenciaPedido, dataLimiteResolucao, ficheirosEmAnexo, formularioPedido);
        return pedidoRascunhoRepository.save(pedidoRascunho);
    }
    public PedidoRascunho updatePedidoRascunho(PedidoRascunho pedidoRascunho) {
        pedidoRascunhoRepository.remove(pedidoRascunho);
        return pedidoRascunhoRepository.save(pedidoRascunho);
    }

    public Iterable<Atributo> atributosFormulario (PedidoRascunho pedidoRascunho){
        return pedidoRascunho.formulario().atributos();
    }

    public Set<Atributo> atributosFormulario (PedidoRascunhoDTO pedidoRascunho){
        PedidoRascunho rascunho = pedidoRascunhoRepository.findPedidoRascunhoById(pedidoRascunho.pk);
        return rascunho.formulario().atributos();
    }

    public boolean preencherFormulario(Set<Atributo> atributos,PedidoRascunho pedidoRascunho){
        if(pedidoRascunho.preencherFormulario(atributos)){
            pedidoRascunho.submeterPedido();
            updatePedidoRascunho(pedidoRascunho);
            return true;
        }
        return false;
    }

    public void preencherFormulario(Set<Atributo> atributos, PedidoRascunhoDTO pedidoRascunho){
        ListPedidoRascunhoController pedidoRascunhoController = new ListPedidoRascunhoController();
        PedidoRascunho rascunho = pedidoRascunhoController.getPedidoRascunhoByID(pedidoRascunho.pk);
        rascunho.preencherFormulario(atributos);
        rascunho.submeterPedido();
        updatePedidoRascunho(rascunho);
    }

    public Iterable<PedidoRascunho> meusPedidosRascunho(Long numeroMecanografico){
        return this.pedidoRascunhoRepository.meusPedidosRascunho(numeroMecanografico);
    }

    public Iterable<PedidoRascunho> meusPedidosRascunho(ColaboradorDTO colaboradorDTO){
        Colaborador autor = colaboradorRepository.findByUsername(colaboradorDTO.username).get();

        return this.pedidoRascunhoRepository.meusPedidosRascunho(autor.mecanographicNumber());
    }

    public Pedido submeterPedido(PedidoRascunho pedidoRascunho){
        RegistarPedidoController registarPedido  = new RegistarPedidoController();
        final ColaboradorDTO beneficiario = pedidoRascunho.benificiarioPedido().toDTO();
        final ServicoDTO servico = pedidoRascunho.servico().toDTO();

        //TODO - RM Colocar info necessaria do pedido
        return registarPedido.registerPedido(beneficiario, servico, pedidoRascunho);
    }

}
