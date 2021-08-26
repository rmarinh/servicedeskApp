package eapli.base.infrastructure.bootstrapers.cenarios;

import eapli.base.atividademanagement.application.CriarTarefasAutomaticasDefaultController;
import eapli.base.atividademanagement.application.ListAtividadeService;
import eapli.base.atividademanagement.domain.*;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.application.RegisterCatalogController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.application.RegistarServicoController;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.formulario.ScriptValidacao;
import eapli.base.servicomanagement.domain.servico.PalavraChave;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.base.slaservicemanagement.repositories.NiveisCriticidadeRepository;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ServicoQuatroBootstrapper implements Action {

    final AtividadeRepository atividadeRepository = PersistenceContext.repositories().atividade();
    final CriarTarefasAutomaticasDefaultController atividadesAutocontroller = new CriarTarefasAutomaticasDefaultController();
    private final ListServicoService svc = new ListServicoService();
    private final ListColaboradorService listColaboradorService = new ListColaboradorService();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();
    private final ListEquipaController controllerEquipas = new ListEquipaController();
    final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();

    // Servico Completo 4 - Requerer cotação para venda por grosso

    @Override
    public boolean execute() {

        ColaboradorDTO colaborador1 = listColaboradorService.findColaboradorByUser("carlosMoutinho");
        NiveisCriticidadeDTO ncDTO3 = niveisCriticidadeRepository.findByEtiqueta("Medio").get().toDTO();

        Iterable<EquipaDTO> listEquipas = controllerEquipas.listEquipas();
        Set<EquipaDTO> setEquipa1 = new LinkedHashSet<>();
        setEquipa1.add(listEquipas.iterator().next());

        Set<EquipaDTO> setEquipa2 = new LinkedHashSet<>();
        setEquipa2.add(listEquipas.iterator().next());

        // Catalogo criticidade medio
        Catalogo catalogo = catalogoRepository.findByTitulo("Descontos e Vendas").get();

        //Definicao das Keywords para serem usadas nos servicos
        Set<PalavraChave> kwCotacaoVendaPorGrosso = new LinkedHashSet<>();

        kwCotacaoVendaPorGrosso.add(PalavraChave.valueOf("Requerer cotacao para venda por grosso"));
        kwCotacaoVendaPorGrosso.add(PalavraChave.valueOf("Venda por grosso"));
        kwCotacaoVendaPorGrosso.add(PalavraChave.valueOf("Cotacao"));

        // Servico Completo 4 - Requerer cotação para venda por grosso
        registerServico(catalogo,"SRV04", "Cotacao para venda por grosso", "Servico Apoio Cliente",
                "Servico Apoio Cliente", "BRANCO_FANTASMA", kwCotacaoVendaPorGrosso, createFormularioPedido(),
                null, createAtividadeAprovacao(), createAtividadeExecucao());

        registerPedido(colaborador1, svc.findServicoById("SRV04").get().toDTO());

        return false;
    }

    private void registerServico(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve, String descricaoCompleta, String icone, Set<PalavraChave> kws, Formulario formulario, Set<ColaboradorDTO> aprovadores, Atividade atividadeAprovacao, Atividade atividadeResolucao ) {

        final RegistarServicoController controller = new RegistarServicoController();

        try {
            Servico servico = controller.registerServicoCompleto(catalogo, codigoUnico,  titulo,  descricaoBreve,  descricaoCompleta,  icone, kws,  formulario, "JOHN",atividadeAprovacao, atividadeResolucao);
            System.out.println("Servico criado: " + servico.descricaoBreve().toString());

        } catch (final IntegrityViolationException | ConcurrencyException e) {

            System.out.println("Falhou a criacao do " + codigoUnico);
            System.out.println(e.getMessage());
        }
    }

    private void registerPedido(ColaboradorDTO colaboradorDTO, ServicoDTO servico) {
        final RegistarPedidoController controller = new RegistarPedidoController();

        try {
            Pedido pedido = controller.registerPedido(colaboradorDTO, servico);
            System.out.println("Pedido criado: " + pedido.identity() + " Servico: " + servico.titulo);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("pedido NAO criado: " + e);
        }
    }

    private Formulario createFormularioPedido() {

        Atributo codProduto = new Atributo("Codigo do Produto", "Codigo do Produto", "Codigo do produto", "String", "[a-zA-Z0-9]*");
        Atributo quantidade = new Atributo("Quantidade", "Quantiddade", "Quantidade pretendida", "Numerico", "[0-9]*");
        Atributo tipoCliente = new Atributo("Tipo Cliente", "Tipo Cliente", "Definir tipo cliente", "String", "Nacional|Europeu|Resto do Mundo");

        Set<Atributo> atributosFormSRV04 = new LinkedHashSet<>();
        atributosFormSRV04.add(codProduto);
        atributosFormSRV04.add(quantidade);
        atributosFormSRV04.add(tipoCliente);
        ScriptValidacao script = new ScriptValidacao("println(\"Validação 1\");  match(campo1_PREENCHIMENTO $ campo1_VALIDADOR) match(campo2_PREENCHIMENTO $ campo2_VALIDADOR); match(campo3_PREENCHIMENTO $ campo3_VALIDADOR);");


        return new Formulario("Formulario Pedido do Servico SRV04", atributosFormSRV04, script);
    }

    private Atividade createAtividadeExecucao(){


        Script script = new Script("sendemail(\"lapr4_1@protonmail.com\", \"Requerer cotação para venda por grosso \", \"O seu valor de desconto sera de 10%\");");

        AtividadeAutomatica atividadeAutomatica = atividadesAutocontroller.criarTarefaAutomaticaDefault("Atividade de Execucao Automatica do Servico SRV04",
                script);

        return atividadeAutomatica;
    }

    private Atividade createAtividadeAprovacao(){

        Atividade atividadeAprovacaoAuto = atividadeRepository.atividadeByDescricao("Aprovacao Automatica").get();

        return atividadeAprovacaoAuto;
    }
}
