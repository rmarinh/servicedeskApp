package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.backoffice.console.presentation.authz.ColaboradorPrinterDTO;
import eapli.base.app.servicosrh.console.presentation.catalogo.CatalogoDTOPrinter;
import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.dto.CatalogoDTO;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.formulario.Atributo;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoRascunhoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoRascunho;
import eapli.base.pedidomanagement.domain.UrgenciaPedido;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.dto.ServicoDTO;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import eapli.base.app.servicosrh.console.presentation.servicos.ServicoPrinter;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PedidoActionUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListServicoService theListServicoService = new ListServicoService();
    private final RegistarPedidoController controller = new RegistarPedidoController();
    private final Username user = authz.session().get().authenticatedUser().identity();

    @Override
    protected boolean doShow() {
        // Listar e escolher o Catalogo a utilizar
        final CatalogoDTO catalogoEscolhido = selectCatalogo();
        // Listar e escolher os Servicos a utilizar
        final ServicoDTO servicoEscolhido = selectServico(catalogoEscolhido);

        // dados para o pedido Rascunho
        final ColaboradorDTO colaboradorbenificiario = selectBeneficiario();
        final UrgenciaPedido urgenciaEscolhida = selectUrgencia();
        final String dataLimiteResolucaoStr = Console.readLine("Insira a data limite de resolucao (Formato: dd/mm/yyyy Ex:31/12/9999)");
        final String anexo = selectAnexo();

        // Criar um Pedido Rascunho
        PedidoRascunho rascunho = registerPedidoRascunho(colaboradorbenificiario,
                servicoEscolhido, urgenciaEscolhida,
                dataLimiteResolucaoStr, anexo.getBytes(StandardCharsets.UTF_8));

        if (rascunho == null) { return false; }

        // Perguntar se quer finalizar o rascunho
        final String concluirEscolha = Console.readLine("Pedido criado como rascunho\nDeseja concluir o pedido? (1 - Sim, 0 - Nao) ");

        if (concluirEscolha.equals("0")) {
            return false;
        }

        Set<Atributo> atributosRespondidos = responderFormulario(rascunho);
        boolean  respondidoComSucesso = registerPedido(rascunho, atributosRespondidos);
        while (respondidoComSucesso == false){
            atributosRespondidos = responderFormulario(rascunho);
            respondidoComSucesso = registerPedido(rascunho, atributosRespondidos);
        }
        return false;
    }

    private Set<Atributo> responderFormulario(PedidoRascunho rascunho) {
        Set<Atributo> atributos = rascunho.atributosFormulario();
        Set<Atributo> atributosRespondidos = new HashSet<>();

        for (Atributo a : atributos){
            System.out.println("Responda ao seguinte Atributo: ");

            System.out.println("Nome: " + a.nome());
            System.out.println("Titulo: " + a.titulo());
            System.out.println("Descricao: " + a.descricao());
            System.out.println("Tipo dados: " + a.tipoDados());
            System.out.println("Validador(): " + a.validador());

            String preenchimento = Console.readLine("Preenchimento: ");

            Atributo aResp = new Atributo(a.nome(), a.titulo(), a.descricao(),
                                          a.tipoDados(), a.validador(), preenchimento);
            atributosRespondidos.add(aResp);
        }

        return atributosRespondidos;
    }

    private String selectAnexo() {

        final String path = Console.readLine("Insira o caminho do ficheiro que deseja anexar: (Ex: C:\\caminho)");
        File file = new File(path);
        String fileContents = "";

        try (FileInputStream inputStream = new FileInputStream(file))
        {
            fileContents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            System.out.println(fileContents);
        }
        catch (IOException e) {
            System.out.println("Ficheiro Invalido");
        }

        return fileContents;
    }

    private UrgenciaPedido selectUrgencia() {
        final Iterable<UrgenciaPedido> listaUrgenciaDisponiveis =  Arrays.asList(UrgenciaPedido.values());
        String urgencia = new String();
        final SelectWidget<UrgenciaPedido> selectorUrgenciaPedido = new SelectWidget<>("Lista de Urgencias disponíveis:", listaUrgenciaDisponiveis,
                new UrgenciaPrinter());

        while (selectorUrgenciaPedido.selectedOption() < 1) {
            selectorUrgenciaPedido.show();
            urgencia = String.valueOf(selectorUrgenciaPedido.selectedElement());
            System.out.println("Urgencia selecionada: " + urgencia);
        }
        return UrgenciaPedido.valueOf(urgencia);
    }

    private ColaboradorDTO selectBeneficiario() {
        final String paraMim = Console.readLine("Deseja ser o beneficiario deste pedido:\n(Resposta: 1 - Sim | 0 - Nao)\n");

        ListColaboradorService colaboradorService = new ListColaboradorService();
        if (paraMim.equals("1")) {
            return colaboradorService.findColaboradorByUser(user.toString());
        }

        final ListColaboradoresController listaColaboradoresController = new ListColaboradoresController();

        final SelectWidget<ColaboradorDTO> selectorColaboradores =
                new SelectWidget<>("Colaborador Beneficiario:", listaColaboradoresController.listColaboradores(),
                        new ColaboradorPrinterDTO());

        selectorColaboradores.show();
        ColaboradorDTO colaboradorBeneficiario = selectorColaboradores.selectedElement();

        return colaboradorBeneficiario;
    }

    private CatalogoDTO selectCatalogo() {
        final ListCatalogoController listCatalogoController = new ListCatalogoController();

        final SelectWidget<CatalogoDTO> selectorCatalogos =
                new SelectWidget<CatalogoDTO>("Catalogos: ", listCatalogoController.catalogoByUser(user),
                        new CatalogoDTOPrinter());

        selectorCatalogos.show();
        CatalogoDTO catalogoEscolhido = selectorCatalogos.selectedElement();

        return catalogoEscolhido;
    }

    private ServicoDTO selectServico(CatalogoDTO catalogoEscolhido) {
        final ListServicoService listServicoService = new ListServicoService();

        final SelectWidget<ServicoDTO> selectorCatalogos =
                new SelectWidget<ServicoDTO>("Servicos: ", listServicoService.servicosByCatalogo(catalogoEscolhido),
                        new ServicoPrinter());

        selectorCatalogos.show();
        ServicoDTO servicoEscolhido = selectorCatalogos.selectedElement();

        return servicoEscolhido;
    }

    private PedidoRascunho registerPedidoRascunho(ColaboradorDTO benificiarioColabDTO,
                                           ServicoDTO servicoDTO, UrgenciaPedido urgenciaPedido,
                                           String dataLimiteResolucao, byte[] ficheirosEmAnexo) {
        final RegistarPedidoRascunhoController controller = new RegistarPedidoRascunhoController();

        // Obter colaborador
        ListColaboradorService colaboradorService = new ListColaboradorService();
        ColaboradorDTO autorColabDTO = colaboradorService.findColaboradorByUser(user.toString());
        Calendar cal = Calendar.getInstance();
        try {
            Date dateResolucao = new SimpleDateFormat("dd/mm/yyyy").parse(dataLimiteResolucao);
            cal.setTime(dateResolucao);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            PedidoRascunho pedidoRascunho =
                    controller.registerPedidoRascunho(autorColabDTO, benificiarioColabDTO,
                                                      servicoDTO, urgenciaPedido,
                                                      cal, ficheirosEmAnexo);

            System.out.println("Pedido Rascunho criado: " + pedidoRascunho.identity());
            return pedidoRascunho;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("Pedido Rascunho NAO criado: " + e);
            return null;
        }
    }

    private boolean registerPedido(PedidoRascunho rascunho, Set<Atributo> atributosRespondidos) {
        final RegistarPedidoRascunhoController controller = new RegistarPedidoRascunhoController();
        if(controller.preencherFormulario(atributosRespondidos, rascunho)){
            controller.submeterPedido(rascunho);
            return true;
        }
        return false;

    }

    @Override
    public String headline() {
        return "Fazer um novo Pedido";
    }
}
