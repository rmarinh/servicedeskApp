package eapli.base.pedidomanagement.application;

import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

/**
 * List Catalog Controller.
 * <p>
 * This controller class list all or a specific catalog in our system.
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class ListPedidoController {

    private final PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();
    private final ListColaboradorService listColaboradorService = new ListColaboradorService();
    private final ListPedidoService listPedidoService = new ListPedidoService();

    public Optional<Pedido> findPedidoById(Long id) {
        return this.pedidoRepository.findById(id);
    }

    public List<Pedido> allPedidosSubmetidosManuais() {
        return this.pedidoRepository.findPedidosSubmetidosManuais();
    }

    public List<Pedido> allPedidosPorAprovarAutomaticos() {
        return this.pedidoRepository.findFluxosAprovacaoAutomaticas();
    }

    public List<Pedido> allPedidosPorExecutarAutomaticos() {
        return this.pedidoRepository.findFluxosExecucaoAutomaticas();
    }

    public List<Pedido> allPedidosPendentesAssignedToColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.pedidoRepository.findPedidosPendentesAssignedToColaborador(colab.mecanographicNumber());
    }

    //Para motor de fluxo de atividades, sem autenticacao
    public List<Pedido> allPedidosPendentesAssignedToColaboradorAutomatico(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsernameAutomatico(user).get();
        return this.pedidoRepository.findPedidosPendentesAssignedToColaborador(colab.mecanographicNumber());
    }

    public List<Pedido> allPedidosPendentes() {
        return this.pedidoRepository.findPedidosPendentes();
    }

    public List<Pedido> allPedidosConcluidos() {
        return this.pedidoRepository.findPedidosConcluidos();
    }

    public List<Pedido> allPedidosSubmetidos() {
        return this.pedidoRepository.findPedidosSubmetidos();
    }

    public List<Pedido> allPedidosConcluidosCriadosPorColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.pedidoRepository.findPedidosConcluidosCreatedByColaborador(colab.mecanographicNumber());
    }

    public List<Pedido> allPedidosEmCursoCriadosPorColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.pedidoRepository.findPedidosEmCursoCreatedByColaborador(colab.mecanographicNumber());
    }

    public List<Pedido> allPedidosPorAprovarPorColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.pedidoRepository.findPedidosPorAprovarAssignedToColaborador(colab.mecanographicNumber());
    }

    public List<Pedido> allPedidosPorExecutarPorColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.pedidoRepository.findPedidosPorExecutarAssignedToColaborador(colab.mecanographicNumber());
    }

    public List<Pedido> allPedidosConcluidosPorColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.pedidoRepository.findPedidosConluidosAssignedToColaborador(colab.mecanographicNumber());
    }

    public List<PedidoDTO> allPedidosCriadosPorColaborador(Username user) {
        Colaborador colab = listColaboradorService.findColaboradorByUsername(user).get();
        return this.listPedidoService.allPedidosCriadosPorColaborador(colab);
    }

    public Iterable<Pedido> allPedidos() {
        return this.pedidoRepository.findAll();
    }

    public int getAvaliacaoPedido(Long pedidoID) {
        return this.pedidoRepository.getAvaliacaoPedido(pedidoID);
    }

    public List<Pedido> allPedidosPendentesManuais() {
        return this.pedidoRepository.findPedidosPendentesManuais();
    }

    public List<PedidoDTO> allPedidosConcluidoIntervalo(String dataFrom, String dataTo) {
        return this.listPedidoService.allPedidosConcluidoIntervalo(dataFrom, dataTo);
    }
}
