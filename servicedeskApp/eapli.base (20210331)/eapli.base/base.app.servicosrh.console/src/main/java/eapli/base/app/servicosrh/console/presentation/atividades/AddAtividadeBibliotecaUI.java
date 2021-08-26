package eapli.base.app.servicosrh.console.presentation.atividades;

import eapli.base.atividademanagement.application.CriarTarefasAutomaticasDefaultController;
import eapli.base.atividademanagement.application.CriarTarefasManuaisDefaultController;
import eapli.base.atividademanagement.domain.Script;
import eapli.base.formulario.Atributo;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.*;

public class AddAtividadeBibliotecaUI extends AbstractUI {

    private final CriarTarefasAutomaticasDefaultController taController = new CriarTarefasAutomaticasDefaultController();
    private final CriarTarefasManuaisDefaultController tmController = new CriarTarefasManuaisDefaultController();

    @Override
    protected boolean doShow() {
        System.out.println("\nPor favor, preencha os seguintes dados:\n");

        System.out.println("Escolha uma equipa.");

        HashSet<String> opcoes = new HashSet<>();
        opcoes.add("Tarefa Automática");
        opcoes.add("Tarefa Manual");

        final SelectWidget<String> selectorAtividade = new SelectWidget<>("Tipo Atividade:", opcoes);
        selectorAtividade.show();

        final String opcao = selectorAtividade.selectedElement();

        final String descricaoBreve = Console.readLine("Descrição breve:");
        final String formulario = Console.readLine("Formulário: (Nota: Indique a descrição do formulario");
        Set<Atributo> listAtributos = new HashSet<>();

        String opcaoAtributo = Console.readLine("Pretende adicionar atributo? (y/n)");

        while (opcaoAtributo.toLowerCase(Locale.ROOT).equals("n")) {
            if (opcaoAtributo.toLowerCase(Locale.ROOT).equals("y")) {

                String nome_atributo = Console.readLine("Nome:");
                String titulo_atributo = Console.readLine("Titulo:");
                String descricao_atributo = Console.readLine("Descrição:");
                String tipoDados_atributo = Console.readLine("Tipo de dados:");
                String validador_atributo = Console.readLine("Validador:");

                Atributo atributo = new Atributo(nome_atributo, titulo_atributo,
                        descricao_atributo, tipoDados_atributo, validador_atributo);

                listAtributos.add(atributo);
            }
        }

        if (opcao.equals("Tarefa Automática")) {

            final String scrt = Console.readLine("Script: (Nota: Indique o caminho do script)");
            Script script = new Script(scrt);

            try {
                taController.criarTarefaAutomaticaDefault(descricaoBreve, script);
            } catch (final Exception e) {
                System.out.println("Erro ao inserir atividade automática.");
            }
        } else if (opcao.equals("Tarefa Manual")) {

            try {
                tmController.criarTarefaManualDefault(descricaoBreve, formulario, listAtributos, "println(\"script\");");
            } catch (final Exception e) {
                System.out.println("Erro ao inserir atividade manual.");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Registar Atividade";
    }

}
