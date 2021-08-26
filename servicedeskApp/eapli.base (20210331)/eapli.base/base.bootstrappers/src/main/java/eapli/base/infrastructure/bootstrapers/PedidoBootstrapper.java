package eapli.base.infrastructure.bootstrapers;

import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @author Rui Marinho
 */
public class PedidoBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);
    private final ListPedidoController listPedidos = new ListPedidoController();
    private final ListServicoService svc = new ListServicoService();
    private final ListColaboradorService svcColaborador = new ListColaboradorService();

    @Override
    public boolean execute() {

        ColaboradorDTO colaborador1 = new ColaboradorDTO("userPedidoA", 12345678L, "firstnameum", "01/01/1990", "localum", "911111111");

        registerPedido(colaborador1, svc.findServicoById("SRV17").get().toDTO());
        registerPedido(colaborador1, svc.findServicoById("SRV20").get().toDTO());
        registerPedido(colaborador1, svc.findServicoById("SRV21").get().toDTO());

        // Para efeitos de teste do Executor de Fluxo de Atividades AUTOMATICAS
        registerPedido(colaborador1, svc.findServicoById("SRV23").get().toDTO());
        registerPedido(colaborador1, svc.findServicoById("SRV22").get().toDTO());
        registerPedido(colaborador1, svc.findServicoById("SRV21").get().toDTO());


        // Criar pedidos para listar os pedidos em curso de um colaborador
        ColaboradorDTO fabioSilvaColab = new ColaboradorDTO("fabioSilva",
                23l, "Fabio Silva", "01/01/1990",
                "Porto", "931111111");

        registerPedido(fabioSilvaColab, svc.findServicoById("SRV24").get().toDTO());

        try {
            avancarEstadoPedidoParaExecucao("SRV24");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void registerPedido(ColaboradorDTO colaboradorDTO, ServicoDTO servico) {
        final RegistarPedidoController controller = new RegistarPedidoController();

        try {
            Pedido pedido = controller.registerPedido(colaboradorDTO, servico);
            System.out.println("Pedido criado: " + pedido.identity() + " Serviço: " + servico.titulo);

            LOGGER.debug("»»» %s", pedido);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("pedido NAO criado: " + e);
        }
    }


    private void avancarEstadoPedidoParaExecucao(String servico) throws InterruptedException {

        Colaborador colab = svcColaborador.findColaboradorByUsername("fabioSilva").get();

        for (Pedido p : listPedidos.allPedidos()) {
            if (p.servico().codigoUnico().toString().equals(servico)) {

                p.emAprovacaoPedidoEvent(colab);
                p.aprovarPedido(colab);
                p.executarPedidoEvent(colab);

            }
        }
    }

}
