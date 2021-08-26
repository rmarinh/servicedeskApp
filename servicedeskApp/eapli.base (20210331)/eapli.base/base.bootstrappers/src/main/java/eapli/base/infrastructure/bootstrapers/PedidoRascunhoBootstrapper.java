package eapli.base.infrastructure.bootstrapers;


import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.formulario.Atributo;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoRascunhoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.base.pedidomanagement.domain.UrgenciaPedido;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author Rui Marinho
 */
public class PedidoRascunhoBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);
    private final ListPedidoController listPedidos = new ListPedidoController();
    private final ListServicoService svc = new ListServicoService();
    private final ListColaboradorService svcColaborador = new ListColaboradorService();
    private final ListColaboradoresController ctrlColaborador = new ListColaboradoresController();

    @Override
    public boolean execute() {
        final String ficheiroXMLExemplo = "<products>\n" +
                "<product>\n" +
                "<id>XYJ123</id>\n" +
                "<name>Product One</name>\n" +
                "<description>This is the description for product one.</description>\n" +
                "<price>19.99</price>\n" +
                "</product>\n" +
                "<product>\n" +
                "<id>XYJ234</id>\n" +
                "<name>Product Two</name>\n" +
                "<description>This is the description for product two.</description>\n" +
                "<price>19.99</price>\n" +
                "</product>\n" +
                "</products>";

        byte ficheeiroXML[] = ficheiroXMLExemplo.getBytes();

        ColaboradorDTO colaboradorautor = new ColaboradorDTO("ruiMarinho", 12345678L, "firstnameum", "01/01/1990",  "localum", "911111111");
        ColaboradorDTO colaboradorbenificiario  = new ColaboradorDTO("antonioZambujeira", 12345678L, "Antonio", "01/01/1990",  "Lisboa", "931111111");

        Iterable<ServicoDTO> it = svc.servicosCompletos();
        Iterator ir = it.iterator();
        ServicoDTO servico = (ServicoDTO) ir.next();
//        ServicoDTO servico2 = (ServicoDTO) ir.next();
        Calendar dataLimiteResolucao = Calendar.getInstance();
        UrgenciaPedido urgenciaREDUZIDA =  UrgenciaPedido.REDUZIDA;
        UrgenciaPedido urgenciaMODERADA =  UrgenciaPedido.MODERADA;
        UrgenciaPedido urgenciaURGENTE =  UrgenciaPedido.URGENTE;

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            dataLimiteResolucao.setTime(sdf.parse("Mon AUG 14 16:02:37 GMT 2011"));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }

        registerPedidoRascunho(colaboradorautor, colaboradorbenificiario, servico, urgenciaREDUZIDA, dataLimiteResolucao, ficheeiroXML);
        registerPedidoRascunho(colaboradorbenificiario, colaboradorautor, servico, urgenciaREDUZIDA, dataLimiteResolucao, ficheeiroXML);
        registerPedidoRascunho(colaboradorautor, colaboradorbenificiario, servico, urgenciaREDUZIDA, dataLimiteResolucao, ficheeiroXML);
        registerPedidoRascunho(colaboradorbenificiario, colaboradorautor, servico, urgenciaREDUZIDA, dataLimiteResolucao, ficheeiroXML);
        preencherFormularioPedido();

        /* registerPedidoRascunho(colaborador1, svc.findServicoById("SRV07").get().toDTO());

        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV08").get().toDTO());

        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV07").get().toDTO());

        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV08").get().toDTO());
        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV07").get().toDTO());

        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV08").get().toDTO());
        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV07").get().toDTO());

        registerPedidoRascunho(colaborador1, svc.findServicoById("SRV08").get().toDTO());

*/
        return false;
    }

    private void registerPedidoRascunho(ColaboradorDTO autorColabDTO, ColaboradorDTO benificiarioColabDTO , ServicoDTO servicoDTO, UrgenciaPedido urgenciaPedido,
                                        Calendar dataLimiteResolucao, byte[] ficheirosEmAnexo) {
        final RegistarPedidoRascunhoController controller = new RegistarPedidoRascunhoController();

        try {
            PedidoRascunho pedidoRascunho = controller.registerPedidoRascunho(autorColabDTO,benificiarioColabDTO,
                    servicoDTO,urgenciaPedido,
                    dataLimiteResolucao,ficheirosEmAnexo);
            controller.submeterPedido(pedidoRascunho);
            System.out.println("Pedido Rascunho criado: " + pedidoRascunho.identity());
            LOGGER.debug("»»» %s", pedidoRascunho);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("PedidoRascunho NAO criado: " + e);
        }

    }

    private void preencherFormularioPedido(){
        final RegistarPedidoRascunhoController controller = new RegistarPedidoRascunhoController();
        ColaboradorDTO colaboradorbenificiario  = new ColaboradorDTO("antonioZambujeira", 12345678L, "Antonio", "01/01/1990",  "Lisboa", "931111111");
        ListColaboradorService listColab = new ListColaboradorService();
        PedidoRascunho pedidoRascunho = controller.meusPedidosRascunho(colaboradorbenificiario).iterator().next();
        Set<Atributo> atributos = pedidoRascunho.atributosFormulario();
        Set<Atributo> atributosRespondidos = new HashSet<>();
        int i = 0;
        for (Atributo a : atributos){
            String preenchimento= "preenchimento" + i;
            Atributo aResp = new Atributo(a.nome(),a.titulo(), a.descricao(), a.tipoDados(), a.validador(), preenchimento);
            atributosRespondidos.add(aResp);
            i++;
        }
        controller.preencherFormulario(atributosRespondidos, pedidoRascunho);
    }


}
