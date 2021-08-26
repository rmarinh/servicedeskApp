package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.base.app.servicosrh.console.presentation.nivelcriticidade.NiveisCriticidadePrinter;
import eapli.base.catalogoservicemanagement.application.ChangeNivelCriticidadeCatalogController;
import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.application.RegisterCatalogController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.catalogoservicemanagement.domain.dto.CatalogoDTO;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.slaservicemanagement.application.ListNiveisCriticidadeController;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeSlaCatalogoUI extends AbstractUI {

    private final ChangeNivelCriticidadeCatalogController controller = new ChangeNivelCriticidadeCatalogController();
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCatalogoUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("\nPor favor, selecione os seguintes dados:\n");

        // Escolher o catalog a modificar
        Long idCatalogo = selectCatalogo();

        // Escolher o novo nivel de criticidade
        Long idNivelCriticidade = selectNivelCriticidade();

        controller.ChangeNivelCriticidade(idCatalogo, idNivelCriticidade);

        return true;
    }

    @Override
    public String headline() {
        return "Editiar o nivel de criticidade de um Catalogo";
    }

    public Long selectCatalogo() {
        final ListCatalogoController listaCatalogosController = new ListCatalogoController();

        final SelectWidget<Catalogo> selectorCatalogos =
                new SelectWidget<Catalogo>("Catalogos:", listaCatalogosController.activeCatalogos(),
                new CatalogoPrinter());

        selectorCatalogos.show();
        return selectorCatalogos.selectedElement().identificador();
    }

    public Long selectNivelCriticidade() {
        final ListNiveisCriticidadeController listaNiveisCriticidadeController = new ListNiveisCriticidadeController();

        final SelectWidget<NiveisCriticidade> selectorNC =
                new SelectWidget<NiveisCriticidade>("NiveisCriticidade:", listaNiveisCriticidadeController.AllNiveisCriticidade(),
                new NiveisCriticidadePrinter());

        selectorNC.show();
        return selectorNC.selectedElement().identity();
    }
}
