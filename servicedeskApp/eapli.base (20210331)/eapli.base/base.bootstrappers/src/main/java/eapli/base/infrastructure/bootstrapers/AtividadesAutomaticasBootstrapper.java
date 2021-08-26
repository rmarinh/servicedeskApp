package eapli.base.infrastructure.bootstrapers;

import eapli.base.atividademanagement.application.CriarTarefasAutomaticasDefaultController;
import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.atividademanagement.domain.Script;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class AtividadesAutomaticasBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {

        Script script = new Script("C:/caminho script");
        Script scriptSendEmail = new Script("sendemail(\"ruimarinhopt@outlook.com\", \"Atividade send email\", \"Atividade EndEmail\");");
        Script scriptFindXMLFicheiro = new Script("\n" +
                "test = \"<products><product><id>XYJ123</id><name>Product One</name><description>This is the description for product one.</description><price>19.99</price></product><product><id>XYJ234</id><name>Product Two</name><description>This is the description for product two.</description><price>19.99</price></product><product><id>XYJ456</id><name>Product Three</name><description>This is the description for product three.</description><price>19.99</price></product><product><id>XYJ789</id><name>Product Four</name><description>This is the description for product four.</description><price>19.99</price></product><product><id>XYH123</id><name>Product Five</name><description>This is the description for product five.</description><price>19.99</price></product></products>\";\n" +
                "find(test,\"product\", \"id\", \"XYJ123\", \"name\", \"price\" );");


        //Atividade automatica para o Serviço 2
        criarTarefaAutomaticaDefault("Envio de Email", scriptSendEmail);

        //Atividade automatica para o Serviço 4
        criarTarefaAutomaticaDefault("Consultar ficheiro XML", scriptFindXMLFicheiro);

        //Atividade automatica para o Fluxo de Aprovação Automático
        criarTarefaAutomaticaDefault("Aprovacao Automatica", script);

        return false;
    }

    private void criarTarefaAutomaticaDefault(String descricaoDreve, Script script) {
        final CriarTarefasAutomaticasDefaultController controller = new CriarTarefasAutomaticasDefaultController();
        try {
            AtividadeAutomatica atividadeAutomatica = controller.criarTarefaAutomaticaDefault(descricaoDreve, script);
            System.out.println("Atividade automatica criada: " + atividadeAutomatica.identity());
            LOGGER.debug("»»» %s", atividadeAutomatica);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Atividade automatica NAO criada: " + descricaoDreve + " " + e);
        }
    }
}
