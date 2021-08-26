package eapli.base.infrastructure.bootstrapers;

import eapli.base.atividademanagement.application.CriarTarefasManuaisDefaultController;
import eapli.base.atividademanagement.application.ListAtividadeService;
import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.atividademanagement.repositories.AtividadeRepository;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.repositories.CatalogoRepository;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoDoisBootstrapper;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoQuatroBootstrapper;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoTresBootstrapper;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoUmBootstrapper;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicomanagement.application.RegistarServicoController;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.formulario.ScriptValidacao;
import eapli.base.servicomanagement.domain.servico.*;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Rui Marinho
 */
public class ServicoBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {

        final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
        final ListAtividadeService svc = new ListAtividadeService();
        final AtividadeRepository atividadeRepository = PersistenceContext.repositories().atividade();
        final CriarTarefasManuaisDefaultController atividadesController = new CriarTarefasManuaisDefaultController();


        // Catalogo criticidade baixa
        Catalogo catalogoUm = catalogoRepository.findByTitulo("Alteracao Dados Pessoais").get();
        // Catalogo criticidade medio baixo
        Catalogo catalogoDois = catalogoRepository.findByTitulo("Marcacao de Ausencias").get();
        // Catalogo criticidade medio
        Catalogo catalogoTres = catalogoRepository.findByTitulo("Descontos e Vendas").get();
        // Catalogo criticidade medio alto
        Catalogo catalogoQuatro = catalogoRepository.findByTitulo("Servicos tecnicos").get();
        // Catalogo criticidade alto
        Catalogo catalogoCinco = catalogoRepository.findByTitulo("Apoio Psicologico").get();

        Set<PalavraChave> kwServicoIncompletoUm = new HashSet<>();
        Set<PalavraChave> kwServicoIncompletoDois = new HashSet<>();

        kwServicoIncompletoUm.add(PalavraChave.valueOf("Troca de Equipa"));
        kwServicoIncompletoDois.add(PalavraChave.valueOf("Pagamento de despesas"));
        kwServicoIncompletoDois.add(PalavraChave.valueOf("Pagamento"));

        // Servico Incompleto 1 - Pedido de Troca de Equipa
        registerServico(catalogoUm, "SRV05", "Troca de Equipa", "Pedido de troca de equipa", "Pedido para trocar equipa", "BRANCO_FANTASMA", kwServicoIncompletoUm);
        // Servico Incompleto 2 - Pagamento de despesas
        registerServico(catalogoUm,"SRV06", "Pagamento de despesas", "Despesas correntes", "Submissao de despesas correntes", "BRANCO_FANTASMA", kwServicoIncompletoDois);

        //Atributos para o formSRVO4
        Atributo codProduto = new Atributo("Codigo do Produto", "Codigo do Produto", "Codigo do produto", "String", "[a-zA-Z0-9]*");
        Atributo quantidade = new Atributo("Quantidade", "Quantiddade", "Quantidade pretendida", "String", "[a-zA-Z0-9]*");
        Atributo tipoCliente = new Atributo("Tipo Cliente", "Tipo Cliente", "Definir tipo cliente", "String", "[a-zA-Z0-9]*");

        Set<Atributo> atributosFormSRV04 = new HashSet<>();
        atributosFormSRV04.add(codProduto);
        atributosFormSRV04.add(quantidade);
        atributosFormSRV04.add(tipoCliente);

        ScriptValidacao script = new ScriptValidacao("[a-zA-Z0-9]*");

        Formulario formSRV04 = new Formulario("FormularioPedido Pedido do Servico SRV04", atributosFormSRV04, script);

        // Servico de teste WorkFlow
        Iterable<Atividade> it = svc.atividades();
        Atividade atividadeAprovacaoAuto = atividadeRepository.atividadeByDescricao("Aprovacao Automatica").get();
        Atividade atividadeResolucaoAuto = atividadeRepository.atividadeByDescricao("Envio de Email").get();

        Iterator ir = it.iterator();
        Atividade atividade1 = (Atividade) ir.next();
        Atividade atividade2 = (Atividade) ir.next();
        Atividade atividade3 = (Atividade) ir.next();

        ColaboradorDTO colaborador1 = new ColaboradorDTO("quimBairro", 12345679L, "firstnameUm", "01/01/1990",  "localum", "911111111");
        ColaboradorDTO colaborador2 = new ColaboradorDTO("antonioZambujeira", 12345699L, "firstnameDois", "01/01/1990",  "localdois", "911111122");

        HashSet<ColaboradorDTO> aprovadores  = new HashSet<>();
        aprovadores.add(colaborador1);
        aprovadores.add(colaborador2);

        // Servico Completo- SRV17
        registerServico(catalogoUm,"SRV17", "Pagamento de despesas", "Despesas correntes",
                "Submissao de despesas correntes", "BRANCO_FANTASMA",
                kwServicoIncompletoDois, formSRV04, aprovadores, atividade1, atividade1);

        // Servico Completo- SRV20
        registerServico(catalogoDois,"SRV20", "Automatico um", "Automatico um",
                "Automatico um", "BRANCO_FANTASMA",
                kwServicoIncompletoDois, formSRV04, aprovadores, atividade3, atividade3);

        // Servico Completo- SRV21
        registerServico(catalogoTres,"SRV21", "Automatico dois", "Automatico dois",
                "Automatico dois", "BRANCO_FANTASMA",
                kwServicoIncompletoDois, formSRV04, aprovadores, atividade3, atividade3);


        // Para efeitos de teste entre o Motor de Fluxos e Executor. Testar a ordenacao da Lista de prioridades

        // Servico Completo- SRV22
        registerServico(catalogoQuatro,"SRV22", "Automatico tres", "Automatico tres",
                "Automatico tres", "BRANCO_FANTASMA",
                kwServicoIncompletoDois, formSRV04, aprovadores, atividade3, atividade3);
        // Servico Completo- SRV23
        registerServico(catalogoCinco,"SRV23", "Automatico quatro", "Automatico quatro",
                "Automatico quatro", "BRANCO_FANTASMA",
                kwServicoIncompletoDois, formSRV04, aprovadores, atividadeAprovacaoAuto, atividadeResolucaoAuto);

        // Pedidos criados para testes da US 3031 - Servico Completo- SRV24

        AtividadeManual atividadeAprovacaoManual = atividadesController.criarTarefaManualDefault("Atividade de Aprovacao do Servico SRV24",
                "Formulario de Aprovacao do Servico SRV01", atributosFormSRV04, "println(\"script\");");

        AtividadeManual atividadeExecucaoManual = atividadesController.criarTarefaManualDefault("Atividade de Execucao do Servico SRV01",
                "Formulario de Execucao do Servico SRV01", atributosFormSRV04, "println(\"script\");");

        registerServico(catalogoTres,"SRV24", "Automatico cinco", "Automatico cinco",
                "Automatico cinco", "BRANCO_FANTASMA",
                kwServicoIncompletoDois, formSRV04, aprovadores, atividadeAprovacaoManual, atividadeExecucaoManual);

        return false;
    }


    private void registerServico(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve, String descricaoCompleta, String icone, Set<PalavraChave> kws) {

        final RegistarServicoController controller = new RegistarServicoController();
        try {
            Servico servico = controller.registerServicoIncompleto(catalogo, codigoUnico,  titulo,  descricaoBreve,  descricaoCompleta,  icone, kws);
            System.out.println("Servico criado: " + servico.descricaoBreve().toString());
            LOGGER.debug("»»» %s", servico);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            // TODO: corrigir ou tratar o erro
            System.out.println("Falhou a criacao do " + codigoUnico);
            System.out.println(e.getMessage());
        }
    }


    private void registerServico(Catalogo catalogo, String codigoUnico, String titulo, String descricaoBreve, String descricaoCompleta, String icone, Set<PalavraChave> kws, Formulario formulario,Set<ColaboradorDTO> aprovadores, Atividade atividadeAprovacao, Atividade atividadeResolucao ) {

        final RegistarServicoController controller = new RegistarServicoController();
        try {
            Servico servico = controller.registerServicoCompleto(catalogo, codigoUnico,  titulo,  descricaoBreve,  descricaoCompleta,  icone, kws,  formulario, "JOHN",atividadeAprovacao, atividadeResolucao);
            System.out.println("Servico criado: " + servico.descricaoBreve().toString());
            LOGGER.debug("»»» %s", servico);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            // TODO: corrigir ou tratar o erro
            System.out.println("Falhou a criacao do " + codigoUnico);
            System.out.println(e.getMessage());
        }
    }
}
