package eapli.base.infrastructure.bootstrapers.cenarios;

import eapli.base.atividademanagement.application.CriarTarefasAutomaticasDefaultController;
import eapli.base.atividademanagement.application.CriarTarefasManuaisDefaultController;
import eapli.base.atividademanagement.domain.*;
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
import java.util.LinkedHashSet;
import java.util.Set;

public class ServicoDoisBootstrapper implements Action {

    private final CriarTarefasManuaisDefaultController atividadesController = new CriarTarefasManuaisDefaultController();
    private final CriarTarefasAutomaticasDefaultController atividadesAutocontroller = new CriarTarefasAutomaticasDefaultController();
    private final ListServicoService svc = new ListServicoService();
    private final ListColaboradorService listColaboradorService = new ListColaboradorService();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();
    private final ListEquipaController controllerEquipas = new ListEquipaController();

    // Servico Completo 2 - Autorizacao para Aplicação de Desconto

    @Override
    public boolean execute() {

        NiveisCriticidadeDTO ncDTO3 = niveisCriticidadeRepository.findByEtiqueta("Medio").get().toDTO();

        ColaboradorDTO colaborador1 = listColaboradorService.findColaboradorByUser("sarahSilva");

        Iterable<EquipaDTO> listEquipas = controllerEquipas.listEquipas();
        Set<EquipaDTO> setEquipa1 = new LinkedHashSet<>();
        setEquipa1.add(listEquipas.iterator().next());

        Set<EquipaDTO> setEquipa2 = new LinkedHashSet<>();
        setEquipa2.add(listEquipas.iterator().next());

        // Catalogo criticidade medio
        Catalogo catalogo = // catalogo de Descontos e Vendas - De acordo com os cenarios
                registerCatalog( "Descontos e Vendas", "Aplicacao de Descontos", "Reportar e solicitar descontos aplicados a vendas","SEPIA",
                        colaborador1, ncDTO3, setEquipa1, setEquipa2);

        //Definicao das Keywords para serem usadas nos servicos
        Set<PalavraChave> kwAutzAppDesconto = new LinkedHashSet<>();

        kwAutzAppDesconto.add(PalavraChave.valueOf("Autorizacao para Aplicacao de Desconto"));
        kwAutzAppDesconto.add(PalavraChave.valueOf("Aplicacao de Desconto"));
        kwAutzAppDesconto.add(PalavraChave.valueOf("Desconto"));

        LinkedHashSet<ColaboradorDTO> aprovadores  = new LinkedHashSet<>();
        aprovadores.add(colaborador1);

        Formulario formSRV02 = createFormularioPedido();

        registerServico(catalogo,"SRV02", "Autorizacao para Aplicacao de Desconto", "Aplicacao de Desconto",
                "Autorizacao para Aplicacao de Desconto", "BRANCO_FANTASMA", kwAutzAppDesconto, formSRV02,
                aprovadores, createAtividadeAprovacao(), createAtividadeExecucao());

        registerPedido(colaborador1, svc.findServicoById("SRV02").get().toDTO());

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

    private Catalogo registerCatalog(String titulo, String descricaoBreve, String descricaoCompleta, String icone, ColaboradorDTO colaboradorDTO, NiveisCriticidadeDTO ncDTO, Set<EquipaDTO> criterios, Set<EquipaDTO> equipasExecucao) {
        final RegisterCatalogController controller = new RegisterCatalogController();

        try {
            Catalogo catalogo = controller.registerCatalog(titulo, descricaoBreve, descricaoCompleta, icone, colaboradorDTO, ncDTO, criterios, equipasExecucao);
            System.out.println("Catalogo criado: " + catalogo.titulo());
            return catalogo;
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Catalogo NAO criado: " + titulo + " " + e);
        }
        return null;
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

    private Set<Atributo> createAtributosAprovacao() {

        Atributo decisao = new Atributo("Decisao", "Decisao", "Decisao", "String", "[a-zA-Z0-9]*");
        Atributo fundamentacao = new Atributo("Fundamentacao", "Fundamentacao", "Fundamentacao", "String", "[a-zA-Z0-9]*");
        Atributo percentagemDesconto = new Atributo("Percentagem de desconto", "Percentagem de desconto", "Percentagem de desconto", "Numerico", "[a-zA-Z0-9]*");
        Atributo valorDesconto = new Atributo("Valor de desconto", "Valor de desconto", "Valor de desconto", "Numerico", "[a-zA-Z0-9]*");

        Set<Atributo> atributosFormSRV02 = new HashSet<>();
        atributosFormSRV02.add(decisao);
        atributosFormSRV02.add(fundamentacao);
        atributosFormSRV02.add(percentagemDesconto);
        atributosFormSRV02.add(valorDesconto);


        return atributosFormSRV02;
    }


    private Formulario createFormularioPedido() {

        Atributo codInternoCliente = new Atributo("Codigo Interno", "Codigo Interno Cliente", "Codigo Interno Cliente", "String", "[a-zA-Z0-9]*");
        Atributo nomeCliente = new Atributo("Nome", "Nome do Cliente", "Nome completo cliente", "String", "[a-zA-Z0-9]*");
        Atributo tipoDesconto = new Atributo("Tipo de Desconto", "Tipo de Desconto", "Definir o tipo de desconto", "String", "[a-zA-Z0-9]*");
        Atributo recorrencia = new Atributo("Recorrencia", "Recorrencia", "Recorrencia da Aplicacao do desconto", "String", "[a-zA-Z0-9]*");
        Atributo percentagemDesconto = new Atributo("Percentagem Desconto", "Percentagem de Desconto", "Percentagem de desconto", "Numerico", "[a-zA-Z0-9]*");
        Atributo valorDesconto = new Atributo("Valor Desconto", "Valor do Desconto", "Valor do desconto aplicar", "Numerico", "[a-zA-Z0-9]*");
        Atributo faturaId = new Atributo("Identificacao da Fatura", "Identificacao da Fatura", "Identificacao da fatura", "String", "[a-zA-Z0-9]*");
        Atributo dataLimite= new Atributo("Data limite", "Data Limite", "Data Limite", "Data", "[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
        Atributo fundamentacaoPedido = new Atributo("Fundamentacao", "Fundamentacao do Desconto", "Fundamentar o desconto aplicar", "String", "[a-zA-Z0-9]*");
        ScriptValidacao script = new ScriptValidacao("println(\"Validação 1\"); assert(campo1_PREENCHIMENTO >= campo1_PREENCHIMENTO); println(\"Validação 2\"); match (campo3_PREENCHIMENTO $ campo3_VALIDADOR) match(campo3_PREENCHIMENTO $ campo3_VALIDADOR); println(\"Validaçao 3\"); if(campo3_PREENCHIMENTO == \"Justificada\") do match(campo4_PREENCHIMENTO $ campo4_VALIDADOR); end");

        Set<Atributo> atributosFormSRV02 = new LinkedHashSet<>();
        atributosFormSRV02.add(codInternoCliente);
        atributosFormSRV02.add(nomeCliente);
        atributosFormSRV02.add(tipoDesconto);
        atributosFormSRV02.add(recorrencia);
        atributosFormSRV02.add(percentagemDesconto);
        atributosFormSRV02.add(valorDesconto);
        atributosFormSRV02.add(faturaId);
        atributosFormSRV02.add(dataLimite);
        atributosFormSRV02.add(fundamentacaoPedido);



        return new Formulario("Formulario Pedido do Servico SRV02", atributosFormSRV02, script);
    }

    private Atividade createAtividadeAprovacao(){
        String scriptAprovacao = "println(\"Validacao 1\"); match(campo2_PREENCHIMENTO $ campo2_VALIDADOR);";

        AtividadeManual atividadeManual = atividadesController.criarTarefaManualDefault("Atividade de Aprovacao do Servico SRV02",
                "Formulario de Aprovacao do Servico SRV02", createAtributosAprovacao(), scriptAprovacao);

        return atividadeManual;

    }

    private Atividade createAtividadeExecucao(){

        Script script = new Script("sendemail(\"lapr4_1+4@protonmail.com\", \"Desconto a Aplicar\", \"O seu desconto a aplicar sera de 10%\");");

        AtividadeAutomatica atividadeAutomatica = atividadesAutocontroller.criarTarefaAutomaticaDefault("Atividade de Execucao Automatica do Servico SRV02",
                script);

        return atividadeAutomatica;

    }
}
