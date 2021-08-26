package eapli.base.app.servicosrh.console.presentation.nivelcriticidade;

import eapli.base.slaservicemanagement.application.RegistarNiveisCriticidadeController;
import eapli.base.slaservicemanagement.domain.Cor;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class AddNiveisCriticidadeUI {

    private final RegistarNiveisCriticidadeController theController = new RegistarNiveisCriticidadeController();
    final Iterable<Cor> listaCoresDisponiveis =  Arrays.asList(Cor.values());
    private static final Logger LOGGER = LoggerFactory.getLogger(AddNiveisCriticidadeUI.class);

    public String headline() {
        return "Adicionar nível de criticidade";
    }

    public NiveisCriticidadeDTO show() {
        System.out.println("\nPor favor, preencha os seguintes dados:\n");

        String cor = new String();
        final SelectWidget<Cor> selectorCor = new SelectWidget<>("Lista de Cores disponíveis:", listaCoresDisponiveis,
                new CoresPrinter());

        while (selectorCor.selectedOption() < 1) {
            selectorCor.show();
            cor = String.valueOf(selectorCor.selectedElement());
            System.out.println("Cor selecionada: " + cor);
        }

        final String escalaNumerica = Console.readLine("Escala numérica: (Insira valores numéricos numa escala de 1 a 5)");
        final String etiqueta = Console.readLine("Etiqueta:  (Ex: \"Médio\" - Max. de 25 caracteres)");
        final String tempoMaximoAprovacao = Console.readLine("Tempo máximo de aprovação: (Insira valores numéricos - expresso em minutos)") ;
        final String tempoMedioAprovacao = Console.readLine("Tempo médio de aprovação: (Insira valores numéricos - expresso em minutos)");
        final String tempoMaximoResolucao = Console.readLine("Tempo máximo de resolução: (Insira valores numéricos - expresso em minutos)") ;
        final String tempoMedioResolucao = Console.readLine("Tempo médio de resolução: (Insira valores numéricos - expresso em minutos)");

        try {
            NiveisCriticidade nivelCriticidade = theController.registarNiveisCriticidade(cor, escalaNumerica,
                                                    etiqueta, tempoMaximoAprovacao, tempoMedioAprovacao, tempoMaximoResolucao, tempoMedioResolucao);
            System.out.println("Nivel de criticidade criado: " + nivelCriticidade.toString());
            LOGGER.debug("»»» %s", nivelCriticidade);
            return nivelCriticidade.toDTO();
        } catch (Exception e) {
            System.out.println("Nível de criticidade NAO criado.  " + e);
            return null;
        }
    }

}
