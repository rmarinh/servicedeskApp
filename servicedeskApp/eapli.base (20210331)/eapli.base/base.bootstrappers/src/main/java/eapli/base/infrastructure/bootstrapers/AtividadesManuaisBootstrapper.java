package eapli.base.infrastructure.bootstrapers;

import eapli.base.atividademanagement.application.CriarTarefasManuaisDefaultController;
import eapli.base.atividademanagement.domain.AtividadeManual;

import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.formulario.ScriptValidacao;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class AtividadesManuaisBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {

        Atributo atr1 = new Atributo("nome 1", "titulo 1", "descricao 1", "textfield","validador N/A");
        Atributo atr2 = new Atributo("nome 2", "titulo 2", "descricao 2", "boolean","validador N/A");

        Set<Atributo> listAtributo = new HashSet<>();
        listAtributo.add(atr1);
        listAtributo.add(atr2);

        // atividade manual que pode ser utilizada
        criarTarefaManualDefault("Descricao breve", "Nome do formulario", listAtributo);
        criarTarefaManualDefault("Atividade Manual 2 ", "Fromulario Manual 2 ", listAtributo);
        return false;
    }

    private void criarTarefaManualDefault(String descricaoDreve, String nomeFormulario, Set<Atributo> atributos) {
        final CriarTarefasManuaisDefaultController controller = new CriarTarefasManuaisDefaultController();
        try {
            AtividadeManual atividadeManual = controller.criarTarefaManualDefault(descricaoDreve, nomeFormulario, atributos, "println(\"script\");");
            System.out.println("Atividade manual criado: " + atividadeManual.identity());
            LOGGER.debug("»»» %s", atividadeManual);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Atividade manual NAO criado: " + descricaoDreve + " " + e);
        }
    }

    private Set<Atributo> createFormularioAprovacaoSRV01() {

        Atributo decisao = new Atributo("Decisao", "Decisao", "Aprovado ou Rejeitado", "String", "[a-zA-Z0-9]*");
        Atributo fundamentacao = new Atributo("Fundamentacao", "Fundamentacao da Decisao", "Fundamentar de forma suscinta a decisao de aprovacao", "String", "[a-zA-Z0-9]*");

        Set<Atributo> atributosFormAprovacao = new HashSet<>();
        atributosFormAprovacao.add(decisao);
        atributosFormAprovacao.add(fundamentacao);

        return atributosFormAprovacao;
    }

    private Set<Atributo> createFormularioAprovacaoSRV02() {

        Atributo codInternoCliente = new Atributo("Codigo Interno", "Codigo Interno Cliente", "Codigo Interno Cliente", "String", "[a-zA-Z0-9]*");
        Atributo nomeCliente = new Atributo("Nome", "Nome do Cliente", "Nome completo cliente", "String", "[a-zA-Z0-9]*");
        Atributo tipoDesconto = new Atributo("Tipo de Desconto", "Tipo de Desconto", "Definir o tipo de desconto", "String", "[a-zA-Z0-9]*");
        Atributo recorrencia = new Atributo("Recorrencia", "Recorrencia", "Recorrencia da Aplicacao do desconto", "String", "[a-zA-Z0-9]*");
        Atributo percentagemDesconto = new Atributo("Percentagem Desconto", "Percentagem de Desconto", "Percentagem de desconto", "String", "[a-zA-Z0-9]*");
        Atributo valorDesconto = new Atributo("Valor Desconto", "Valor do Desconto", "Valor do desconto aplicar", "String", "[a-zA-Z0-9]*");
        Atributo faturaId = new Atributo("Identificacao da Fatura", "Identificacao da Fatura", "Identificacao da fatura", "String", "[a-zA-Z0-9]*");
        Atributo fundamentacaoPedido = new Atributo("Fundamentacao", "Fundamentacao do Desconto", "Fundamentar o desconto aplicar", "String", "[a-zA-Z0-9]*");

        Set<Atributo> atributosFormSRV02 = new HashSet<>();
        atributosFormSRV02.add(codInternoCliente);
        atributosFormSRV02.add(nomeCliente);
        atributosFormSRV02.add(tipoDesconto);
        atributosFormSRV02.add(recorrencia);
        atributosFormSRV02.add(percentagemDesconto);
        atributosFormSRV02.add(valorDesconto);
        atributosFormSRV02.add(faturaId);
        atributosFormSRV02.add(fundamentacaoPedido);


        return atributosFormSRV02;
    }
}
