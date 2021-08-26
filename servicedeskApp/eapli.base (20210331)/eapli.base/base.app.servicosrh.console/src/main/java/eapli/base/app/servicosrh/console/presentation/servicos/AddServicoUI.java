package eapli.base.app.servicosrh.console.presentation.servicos;

import eapli.base.app.backoffice.console.presentation.authz.CatalogoPrinter;
import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.servicomanagement.application.RegistarFormularioController;
import eapli.base.servicomanagement.application.RegistarServicoController;
import eapli.base.formulario.Atributo;
import eapli.base.formulario.Formulario;
import eapli.base.servicomanagement.domain.servico.PalavraChave;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddServicoUI extends AbstractUI {

    private final RegistarServicoController theController = new RegistarServicoController();
    private final RegistarFormularioController theFormularioController = new RegistarFormularioController();
    private final ListCatalogoController theControllerListCatalogo = new ListCatalogoController();

    @Override
    protected boolean doShow() {

        final Iterable<Catalogo> catalogos = this.theControllerListCatalogo.activeCatalogos();

        System.out.println("Escolha um Catálogo.");

        final SelectWidget<Catalogo> selector = new SelectWidget<>("Catalogos:", catalogos,
                new CatalogoPrinter());

        selector.show();

        final Catalogo catalogo = selector.selectedElement();

        final String codigoUnico = Console.readLine("Codigo Unico");
        final String titulo= Console.readLine("Titulo");
        final String descricaoBreve= Console.readLine("Descricao Breve");
        final String descricaoCompleta= Console.readLine("Descricao Completa");
        final String icone = Console.readLine("Icone");

        final Set<PalavraChave> keywords = new HashSet<>();

        String opcao = "0";

        while(!opcao.equalsIgnoreCase("1")){

            final String palavraChave = Console.readLine("Palavra Chave:");
            final PalavraChave keyword = PalavraChave.valueOf(palavraChave);

            System.out.println("-> Adicionar nova palavra chave?");
            System.out.println("0- Sim");
            System.out.println("1- Não");

            opcao = Console.readLine("Selecione uma opcao:");
        }

        System.out.println("-> Deseja continuar ou gravar?");
        System.out.println("0- Gravar e continuar mais tarde");
        System.out.println("1- Continuar");

        final String gravarContinuar = Console.readLine("\nSelecione uma opção:");

        if(gravarContinuar.equalsIgnoreCase("0")){
            try {
                this.theController.registerServicoIncompleto(catalogo, codigoUnico, titulo, descricaoBreve, descricaoCompleta, icone, keywords);
            } catch (final IntegrityViolationException | ConcurrencyException e) {
                System.out.println("Serviço já existente já existente.");
            }
        }else{

            final String nomeFormulario = Console.readLine("Nome do Formulário");

            final Set<Atributo> atributos = new HashSet<>();

            System.out.println("-> Adicionar Atributos");
            String opcao_1 = "0";

            while(!opcao_1.equalsIgnoreCase("1")){

                final String nome = Console.readLine("Nome do Atributo");
                final String tituloAtributo = Console.readLine("Titulo do Atributo");
                final String descricaoAtributo = Console.readLine("Descrição");
                final String tipoDados = Console.readLine("Tipo de Dados");
                final String validador = Console.readLine("Validador");

                final Atributo atributo = new Atributo(nome, tituloAtributo, descricaoAtributo, tipoDados, validador);

                System.out.println("-> Adicionar novo atributo?");
                System.out.println("0- Sim");
                System.out.println("1- Gravar e sair");

                opcao_1 = Console.readLine("Selecione uma opcao:");
            }

            Formulario formulario = theFormularioController.registerFormulario(nomeFormulario, atributos);

            try {
                this.theController.registerServicoCompleto(catalogo, codigoUnico, titulo, descricaoBreve, descricaoCompleta, icone, formulario);
            } catch (final IntegrityViolationException | ConcurrencyException e) {
                System.out.println("Codigo Unico já existente.");
            }

        }

        return false;
    }

    @Override
    public String headline() {
        return "Adicionar Servico";
        }
    }
