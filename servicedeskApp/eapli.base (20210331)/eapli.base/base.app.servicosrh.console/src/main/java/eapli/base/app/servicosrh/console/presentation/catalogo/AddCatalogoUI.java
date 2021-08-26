package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.base.app.backoffice.console.presentation.authz.ColaboradorPrinterDTO;
import eapli.base.app.servicosrh.console.presentation.equipa.EquipaPrinter;
import eapli.base.app.servicosrh.console.presentation.nivelcriticidade.NiveisCriticidadePrinter;
import eapli.base.catalogoservicemanagement.application.RegisterCatalogController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.slaservicemanagement.application.ListNiveisCriticidadeController;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.base.teammanagement.application.ListEquipaController;
import eapli.base.teammanagement.dto.EquipaDTO;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class AddCatalogoUI extends AbstractUI {

    private final RegisterCatalogController controller = new RegisterCatalogController();
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCatalogoUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("\nPor favor, preencha os seguintes dados:\n");

        // Definir Titulo + Descricao Breve + Descricao Completa + Icone
        final String titulo = Console.readLine("Titulo: (Ex: Alteracao Dados Pessoais Nota: Max. 25 caracteres!)");
        final String descricaoBreve = Console.readLine("Descricao breve: (Ex: Alteracao dados intimos colaborador Nota: Max. 30 caracteres!)");
        final String descricaoCompleta = Console.readLine("Descricao completa: (Ex: Alteracao de dados pessaoos dos colaboradores Nota: Max. 100 caracteres!)");
        final String icone = Console.readLine("Icone: (Dica: insira o caminho do ficheiro!)");

        // Definir Colaborador Responsavel
        final ColaboradorDTO responsavelEscolhido = selectResponsavel();

        // Definir o Nivel de criticidade
        final NiveisCriticidadeDTO nivelCritEscolhido = selectNivelCriticidade();

        // Definir Criterios
        final Set<EquipaDTO> setCriteros = new HashSet<>(selectCriterios());

        // Definir Equipas Executoras
        final Set<EquipaDTO> setEquipasExecutoras = new HashSet<>(selectEquipasExecucao());

        try {
            Catalogo catalogo = controller.registerCatalog(titulo, descricaoBreve, descricaoCompleta, icone, responsavelEscolhido, nivelCritEscolhido, setCriteros, setEquipasExecutoras);
            System.out.println("Catalogo criado: " + catalogo.titulo());
            LOGGER.debug("»»» %s", catalogo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Catalogo NAO criado: " + titulo + " " + e);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Adicionar um novo Catalogo";
    }

    public ColaboradorDTO selectResponsavel() {
        final ListColaboradoresController listaColaboradoresController = new ListColaboradoresController();

        final SelectWidget<ColaboradorDTO> selectorColaboradores =
                new SelectWidget<>("Colaboradores:", listaColaboradoresController.listColaboradores(),
                new ColaboradorPrinterDTO());

        selectorColaboradores.show();
        ColaboradorDTO responsavelEscolhido = selectorColaboradores.selectedElement();
        return responsavelEscolhido;
    }

    public NiveisCriticidadeDTO selectNivelCriticidade() {
        final ListNiveisCriticidadeController listaNiveisCriticidadeController = new ListNiveisCriticidadeController();

        final SelectWidget<NiveisCriticidade> selectorNC = new SelectWidget<NiveisCriticidade>("NiveisCriticidade:", listaNiveisCriticidadeController.AllNiveisCriticidade(),
                new NiveisCriticidadePrinter());

        selectorNC.show();
        NiveisCriticidadeDTO nivelCriticidadeDTO = (selectorNC.selectedElement()).toDTO();
        return nivelCriticidadeDTO;
    }

    public Set<EquipaDTO> selectCriterios() {
        final ListEquipaController listaEquipasController = new ListEquipaController();

        final SelectWidget<EquipaDTO> selectorEquipa = new SelectWidget<EquipaDTO>("Lista equipas:", listaEquipasController.listEquipas(),
                new EquipaPrinter());

        Set<EquipaDTO> setCriteros = new HashSet<>();
        selectorEquipa.show();
        while (selectorEquipa.selectedOption() != 0) {
            if (setCriteros.contains(selectorEquipa.selectedElement())) {
                System.out.println("O Catalogo ja comtem essa equipa como criterio de acesso!");
            } else {
                setCriteros.add(selectorEquipa.selectedElement());
            }
            selectorEquipa.show();
        }

        System.out.println(setCriteros.size());

        return new HashSet<>(setCriteros);
    }

    public Set<EquipaDTO> selectEquipasExecucao() {
        final ListEquipaController listaEquipasController = new ListEquipaController();

        final SelectWidget<EquipaDTO> selectorEquipa = new SelectWidget<EquipaDTO>("Lista equipas:", listaEquipasController.listEquipas(),
                new EquipaPrinter());

        Set<EquipaDTO> setCriteros = new HashSet<>();
        selectorEquipa.show();
        while (selectorEquipa.selectedOption() != 0) {
            if (setCriteros.contains(selectorEquipa.selectedElement())) {
                System.out.println("O Catalogo ja contem essa equipa como executora de pedidos!");
            } else {
                setCriteros.add(selectorEquipa.selectedElement());
            }
            selectorEquipa.show();
        }

        System.out.println(setCriteros.size());

        return new HashSet<>(setCriteros);
    }
}
