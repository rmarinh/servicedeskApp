package eapli.base.app.servicosrh.console.presentation.atividades;

import eapli.base.atividademanagement.application.ListAtividadeService;
import eapli.base.atividademanagement.domain.Atividade;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * @author
 */
@SuppressWarnings({"squid:S106"})
public class ListAtividadesBibliotecaUI extends AbstractListUI<Atividade> {
    private ListAtividadeService theService = new ListAtividadeService();

    @Override
    public String headline() {
        return "Lista de Atividades existentes em sistema:";
    }

    @Override
    protected String emptyMessage() {
        return "Não existem atividades disponiveis!";
    }

    @Override
    protected Iterable<Atividade> elements() {
        return theService.atividades();
    }

    @Override
    protected Visitor<Atividade> elementPrinter() {
        return new AtividadePrinter();
    }

    @Override
    protected String elementName() {
        return "Atividades";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %s -- %s", "Identificador", "DescricaoBreve");
    }
}