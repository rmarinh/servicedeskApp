package eapli.base.app.servicosrh.console.presentation.servicos;

import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.application.RegistarFormularioController;
import eapli.base.servicomanagement.application.RegistarServicoController;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompleteServicoUI extends AbstractUI {

    private final RegistarServicoController theController = new RegistarServicoController();
    private final RegistarFormularioController theFormularioController = new RegistarFormularioController();
    private final ListServicoService theControllerListService = new ListServicoService();

    @Override
    protected boolean doShow() {

        final Iterable<ServicoDTO> servicos = this.theControllerListService.servicosIncompletos();

        System.out.println("Selecione um serviço para completar.");

        final SelectWidget<ServicoDTO> selector = new SelectWidget<>("Servicos:", servicos,
                new ServicoPrinter());

        selector.show();

        final Servico servico = theControllerListService.findServicoById(selector.selectedElement().codUnico).get();

        System.out.println("-> Adicionar Formulário");

            final String nomeFormulario = Console.readLine("Nome do Formulário");

            final Set<Atributo> atributos = new HashSet<>();

            System.out.println("-> Adicionar Atributo");

            String opcao = "0";

            while(!opcao.equalsIgnoreCase("1")){

                final String nome = Console.readLine("Nome do Atributo");
                final String tituloAtributo = Console.readLine("Titulo do Atributo");
                final String descricaoAtributo = Console.readLine("Descrição");
                final String tipoDados = Console.readLine("Tipo de Dados");
                final String validador = Console.readLine("Validador");

                final Atributo atributo = new Atributo(nome, tituloAtributo, descricaoAtributo, tipoDados, validador);

                System.out.println("-> Adicionar novo atributo?");
                System.out.println("0- Sim");
                System.out.println("1- Gravar e sair");

                opcao = Console.readLine("Selecione uma opcao:");
            }

            Formulario formulario = theFormularioController.registerFormulario(nomeFormulario, atributos);

        try {
            this.theController.registerServicoIncompleto(servico, formulario);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Codigo Unico já existente.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Completar Servico";
        }
    }
