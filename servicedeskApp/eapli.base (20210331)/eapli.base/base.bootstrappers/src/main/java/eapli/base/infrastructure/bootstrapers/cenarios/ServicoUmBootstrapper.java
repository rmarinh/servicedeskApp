package eapli.base.infrastructure.bootstrapers.cenarios;

import eapli.base.atividademanagement.application.CriarTarefasManuaisDefaultController;
import eapli.base.atividademanagement.application.ListAtividadeService;
import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.atividademanagement.domain.DescricaoBreve;
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


public class ServicoUmBootstrapper implements Action {

    private final CriarTarefasManuaisDefaultController atividadesController = new CriarTarefasManuaisDefaultController();
    private final ListServicoService svc = new ListServicoService();
    private final ListColaboradorService listColaboradorService = new ListColaboradorService();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();
    private final ListEquipaController controllerEquipas = new ListEquipaController();

    // Servico Completo 1 - Pedido de Ausência Futura

    @Override
    public boolean execute() {

        System.out.println("\n------- Cenario Completo 1 - Pedido de Ausência Futura -------");

        ColaboradorDTO colaborador1 = listColaboradorService.findColaboradorByUser("fabioSilva");
        ColaboradorDTO colaborador2 = listColaboradorService.findColaboradorByUser("rafaelSoares");

        NiveisCriticidadeDTO ncDTO2 = niveisCriticidadeRepository.findByEtiqueta("Medio Baixo").get().toDTO();
        Iterable<EquipaDTO> listEquipas = controllerEquipas.listEquipas();
        Set<EquipaDTO> setEquipa1 = new LinkedHashSet<>();
        setEquipa1.add(listEquipas.iterator().next());

        Set<EquipaDTO> setEquipa2 = new LinkedHashSet<>();
        setEquipa2.add(listEquipas.iterator().next());

        // Catalogo criticidade medio baixo
        Catalogo catalogo = // catalogo de Marcacao de Ausências - De acordo com os cenarios
                registerCatalog( "Marcacao de Ausencias", "Comunicar ausencia futura",
                        "Marcacao de ferias, ausencias hospitalares","OLIVA_ESCURA", colaborador2,
                        ncDTO2, setEquipa1, setEquipa2);

        //Definicao das Keywords para serem usadas nos servicos
        Set<PalavraChave> kwAusenciaFutura = new LinkedHashSet<>();

        kwAusenciaFutura.add(PalavraChave.valueOf("Ausencia Futura"));
        kwAusenciaFutura.add(PalavraChave.valueOf("Ausencia"));

        Formulario formSRV01 = createFormularioPedido();

        HashSet<ColaboradorDTO> aprovadores = new HashSet<>();
        aprovadores.add(colaborador1);
        aprovadores.add(colaborador2);

        registerServico(catalogo, "SRV01", "Ausencia Futura", "Pedido de Ausencia Futura",
                "Pedido de Ausencia Futura", "BRANCO_FANTASMA",
                kwAusenciaFutura, formSRV01, aprovadores, createAtividadeAprovacao(), createAtividadeExecucao());

        registerPedido(colaborador1, svc.findServicoById("SRV01").get().toDTO());

        return false;
    }

    private void registerServico(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve, String descricaoCompleta, String icone, Set<PalavraChave> kws, Formulario formulario, Set<ColaboradorDTO> aprovadores, Atividade atividadeAprovacao, Atividade atividadeResolucao) {

        final RegistarServicoController controller = new RegistarServicoController();

        try {
            Servico servico = controller.registerServicoCompleto(catalogo, codigoUnico, titulo, descricaoBreve, descricaoCompleta, icone, kws, formulario, "JOHN", atividadeAprovacao, atividadeResolucao);
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

        Atributo decisao = new Atributo("Decisao", "Decisao", "Aprovado ou Rejeitado", "String", "[a-zA-Z0-9 ]*");
        Atributo fundamentacao = new Atributo("Fundamentacao", "Fundamentacao da Decisao", "Fundamentar de forma suscinta a decisao de aprovacao", "String", "[a-zA-Z0-9 ]*");
        Set<Atributo> atributosFormAprovacao = new HashSet<>();
        atributosFormAprovacao.add(decisao);
        atributosFormAprovacao.add(fundamentacao);

        return atributosFormAprovacao;
    }

    private Formulario createFormularioPedido() {

        Atributo inicioAusencia = new Atributo("Inicio Ausencia", "Inicio do Periodo de Ausencia", "Data de Inicio", "Data", "[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
        Atributo fimAusencia = new Atributo("Fim Ausencia", "Fim do Periodo de Ausencia", "Data de Fim", "Data", "[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
        Atributo tipoAusencia = new Atributo("Tipo de Ausencia", "Tipo de Ausencia", "Definir o tipo de Ausencia", "String", "[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
        Atributo justificacao = new Atributo("Justificacao", "Justificacao", "Justificacao da Ausencia", "String", "Ferias|Justificada|Nao Justificada");

        Set<Atributo> atributosFormSRV01 = new HashSet<>();
        atributosFormSRV01.add(inicioAusencia);
        atributosFormSRV01.add(fimAusencia);
        atributosFormSRV01.add(tipoAusencia);
        atributosFormSRV01.add(justificacao);

        ScriptValidacao script = new ScriptValidacao("println(\"Validação 1\"); assert(campo1_PREENCHIMENTO >= campo1_PREENCHIMENTO); println(\"Validação 2\"); match (campo3_PREENCHIMENTO $ campo3_VALIDADOR); match(campo3_PREENCHIMENTO $ campo3_VALIDADOR); println(\"Validaçao 3\"); if(campo3_PREENCHIMENTO == \"Justificada\") do match(campo4_PREENCHIMENTO $ campo4_VALIDADOR); end");

        return new Formulario("Formulario Pedido do Servico SRV01", atributosFormSRV01, script);
    }

    private Set<Atributo> createAtributosExecucao() {

        Atributo diasFeriasGozadasAno = new Atributo("Ferias Gozadas Ano", "Dias de Ferias Restantes", "Dias de Ferias restantes", "Numerico", "[a-zA-Z0-9]*");
        Atributo diasFeriasGozadasPeriodo = new Atributo("Ferias Gozadas no periodo", "Dias de Ferias Gozadas no Periodo", "Dias de Ferias Gozadas no Periodo", "Numerico", "[a-zA-Z0-9]*");
        Atributo diasFeriasTotais = new Atributo("Dias de Ferias", "Dias de Ferias Totais", "Dias de Ferias Totais", "Numerico", "[a-zA-Z0-9]*");
        Atributo faltasJustificadasAno = new Atributo("Faltas Justificadas Ano", "Numero de Faltas Justificadas", "Numero de Faltas Justificadas ocorridas durante o ano", "Numerico", "[a-zA-Z0-9]*");
        Atributo faltasJustificadasPeriodo = new Atributo("Faltas Justificadas no periodo", "Numero de Faltas Justificadas no Periodo", "Numero de Faltas Justificadas no Periodo", "Numerico", "[a-zA-Z0-9]*");
        Atributo faltasJustificadasTotais = new Atributo("Faltas Justificadas", "Faltas Justificadas Totais", "Faltas Justificadas Totais", "Numerico", "[a-zA-Z0-9]*");
        Atributo faltasInjustificadasAno = new Atributo("Faltas Injustificadas Ano", "Numero de Faltas Injustificadas", "Numero de Faltas Injustificadas ocorridas durante o ano", "Numerico", "[a-zA-Z0-9]*");
        Atributo faltasInjustificadasPeriodo = new Atributo("Faltas Injustificadas no periodo", "Numero de Faltas Injustificadas no Periodo", "Numero de Faltas Injustificadas no Periodo", "Numerico", "[a-zA-Z0-9]*");
        Atributo faltasInjustificadasTotais = new Atributo("Faltas Injustificadas", "Faltas Injustificadas Totais", "Faltas Injustificadas Totais", "Numerico", "[a-zA-Z0-9]*");
        Atributo comentario = new Atributo("Comentario", "Comentarios", "Comentarios", "String", "[a-zA-Z0-9]*");


        Set<Atributo> atributosFormExecucao = new HashSet<>();
        atributosFormExecucao.add(diasFeriasGozadasAno);
        atributosFormExecucao.add(diasFeriasGozadasPeriodo);
        atributosFormExecucao.add(diasFeriasTotais);
        atributosFormExecucao.add(faltasJustificadasAno);
        atributosFormExecucao.add(faltasJustificadasPeriodo);
        atributosFormExecucao.add(faltasJustificadasTotais);
        atributosFormExecucao.add(faltasInjustificadasAno);
        atributosFormExecucao.add(faltasInjustificadasPeriodo);
        atributosFormExecucao.add(faltasInjustificadasTotais);
        atributosFormExecucao.add(comentario);

        return atributosFormExecucao;
    }

    private Atividade createAtividadeAprovacao(){

        String ScriptAprovacao = "println(\"Validacao 1\"); match(campo2_PREENCHIMENTO $ campo2_VALIDADOR);";
        
        AtividadeManual atividadeManual = atividadesController.criarTarefaManualDefault("Atividade de Aprovacao do Servico SRV01",
                "Formulario de Aprovacao do Servico SRV01", createAtributosAprovacao(),ScriptAprovacao );

        return atividadeManual;
    }

    private Atividade createAtividadeExecucao(){
        String ScriptExecucao = "println(\"Validacao 1\");" +
                " match(campo1_PREENCHIMENTO $ campo1_VALIDADOR);" + //Ferias Gozadas Ano
                " match(campo2_PREENCHIMENTO $ campo2_VALIDADOR); " + //"Ferias Gozadas no periodo"
                "match(campo3_PREENCHIMENTO $ campo3_VALIDADOR); " + //"Dias de Ferias"
                "match(campo4_PREENCHIMENTO $ campo4_VALIDADOR); " + //"Faltas Justificadas Ano"
                "match(campo5_PREENCHIMENTO $ campo5_VALIDADOR); " + //"Faltas Justificadas no periodo"
                "match(campo6_PREENCHIMENTO $ campo6_VALIDADOR); " + //"Faltas Justificadas"
                "match(campo7_PREENCHIMENTO $ campo7_VALIDADOR); " + //"Faltas Injustificadas Ano"
                "match(campo8_PREENCHIMENTO $ campo8_VALIDADOR); " + //"Faltas Injustificadas no periodo"
                "match(campo9_PREENCHIMENTO $ campo9_VALIDADOR); " + //"Faltas Injustificadas"
                "println(\"Validacao 2\");" +
                "assert(campo3_PREENCHIMENTO < campo1_PREENCHIMENTO );";

        AtividadeManual atividadeManual = atividadesController.criarTarefaManualDefault("Atividade de Execucao do Servico SRV01",
                "Formulario de Execucao do Servico SRV01", createAtributosExecucao(), ScriptExecucao);

        return atividadeManual;
    }

}
