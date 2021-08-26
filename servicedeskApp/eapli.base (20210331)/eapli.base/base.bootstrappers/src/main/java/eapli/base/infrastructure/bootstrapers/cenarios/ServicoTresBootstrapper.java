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

public class ServicoTresBootstrapper implements Action {

    private final AtividadeRepository atividadeRepository = PersistenceContext.repositories().atividade();
    private final CriarTarefasAutomaticasDefaultController atividadesAutocontroller = new CriarTarefasAutomaticasDefaultController();
    private final ListServicoService svc = new ListServicoService();
    private final ListColaboradorService listColaboradorService = new ListColaboradorService();
    private final NiveisCriticidadeRepository niveisCriticidadeRepository = PersistenceContext.repositories().niveisCriticidade();
    private final ListEquipaController controllerEquipas = new ListEquipaController();

    // Servico Completo 3 - Comunicacao de Alteracao de Residência

    @Override
    public boolean execute() {
        ColaboradorDTO colaborador1 = listColaboradorService.findColaboradorByUser("ruiMarinho");

        NiveisCriticidadeDTO ncDTO1 = niveisCriticidadeRepository.findByEtiqueta("Baixo").get().toDTO();

        Iterable<EquipaDTO> listEquipas = controllerEquipas.listEquipas();
        Set<EquipaDTO> setEquipa1 = new LinkedHashSet<>();
        setEquipa1.add(listEquipas.iterator().next());

        Set<EquipaDTO> setEquipa2 = new LinkedHashSet<>();
        setEquipa2.add(listEquipas.iterator().next());

        // Catalogo criticidade baixa
        Catalogo catalogo = registerCatalog( "Alteracao Dados Pessoais", "Alteracao de Dados Pessoais",
                "Alteracao de dados peossais dos colaboradores", "BRANCO_FANTASMA",
                colaborador1, ncDTO1, setEquipa1, setEquipa2);

        //Definicao das Keywords para serem usadas nos servicos
        Set<PalavraChave> kwAlteracaoResidencia = new LinkedHashSet<>();

        kwAlteracaoResidencia.add(PalavraChave.valueOf("Comunicacao de Alteracao de Residencia"));
        kwAlteracaoResidencia.add(PalavraChave.valueOf("Alteracao de Residencia"));

        // Servico Completo 3 - Comunicacao de Alteracao de Residência
        registerServico(catalogo,"SRV03", "Alteracao de Residencia", "Servico Apoio Cliente",
                "Servico Apoio Cliente", "BRANCO_FANTASMA",
                kwAlteracaoResidencia, createFormularioPedido(),null, createAtividadeAprovacao(), createAtividadeExecucao());

        registerPedido(colaborador1, svc.findServicoById("SRV03").get().toDTO());

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

    private Formulario createFormularioPedido() {

        Atributo rua = new Atributo("Rua", "Rua", "Rua", "String", "[a-zA-Z0-9]*");
        Atributo numPorta = new Atributo("Numero da Porta", "Numero da Porta", "Numero da Porta", "Numerico", "[a-zA-Z0-9]*");
        Atributo codPostal = new Atributo("Codigo Postal", "Codigo Postal", "Definir Codigo Postal", "String", "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]");
        Atributo localidade = new Atributo("Localidade", "Localidade", "Localidade", "String", "[a-zA-Z0-9]*");
        Atributo distrito = new Atributo("Distrito", "Distrito", "Distrito", "String", "[a-zA-Z0-9]*");

        Set<Atributo> atributosFormSRV03 = new LinkedHashSet<>();
        atributosFormSRV03.add(rua);
        atributosFormSRV03.add(numPorta);
        atributosFormSRV03.add(codPostal);
        atributosFormSRV03.add(localidade);
        atributosFormSRV03.add(distrito);

        //ScriptValidacao script = new ScriptValidacao("[a-zA-Z0-9]*");
        ScriptValidacao script = new ScriptValidacao("println(\"Validação 1\"); match (campo1_PREENCHIMENTO $ campo1_VALIDADOR); match(campo2_PREENCHIMENTO $ campo2_VALIDADOR); match(campo3_PREENCHIMENTO $ campo3_VALIDADOR); match(campo4_PREENCHIMENTO $ campo4_VALIDADOR);  match(campo5_PREENCHIMENTO $ campo5_VALIDADOR);");

        return new Formulario("Formulario Pedido do Servico SRV03", atributosFormSRV03, script);
    }

    private Atividade createAtividadeExecucao(){

        Script script = new Script("println(\"Validação 1\"); match (campo1_PREENCHIMENTO $ campo1_VALIDADOR);");

        AtividadeAutomatica atividadeAutomatica = atividadesAutocontroller.criarTarefaAutomaticaDefault("Atividade de Execucao Automatica do Servico SRV03",
                script);

        return atividadeAutomatica;
    }

    private Atividade createAtividadeAprovacao(){

        Atividade atividadeAprovacaoAuto = atividadeRepository.atividadeByDescricao("Aprovacao Automatica").get();

        return atividadeAprovacaoAuto;
    }
}
