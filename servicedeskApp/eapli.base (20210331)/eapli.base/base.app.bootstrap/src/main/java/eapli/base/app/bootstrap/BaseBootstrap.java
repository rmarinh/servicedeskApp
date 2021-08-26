package eapli.base.app.bootstrap;

import eapli.base.app.common.console.BaseApplication;
import eapli.base.colaboradormanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.base.colaboradormanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.base.colaboradormanagement.domain.events.SignupAcceptedEvent;
import eapli.base.infrastructure.bootstrapers.*;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoDoisBootstrapper;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoQuatroBootstrapper;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoTresBootstrapper;
import eapli.base.infrastructure.bootstrapers.cenarios.ServicoUmBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.BaseDemoBootstrapper;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.eventpubsub.EventDispatcher;
import eapli.framework.io.util.Console;
import eapli.framework.util.ArrayPredicates;

/**
 * Base Bootstrapping data app
 */
@SuppressWarnings("squid:S106")
public final class BaseBootstrap extends BaseApplication {
    /**
     * avoid instantiation of this class.
     */
    private BaseBootstrap() {
    }

    private boolean isToBootstrapDemoData;
    private boolean isToRunSampleE2E;

    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
                new PlainTextEncoder());

        new BaseBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        handleArgs(args);

        System.out.println("\n\n------- MASTER DATA -------");
        new BaseBootstrapper().execute();

        System.out.println("\n\n------- RECURSOS HUMANOS BOOTSTRAP -------");
        new BaseDemoBootstrapper().execute();

        System.out.println("\n\n------- TIPOS DE EQUIPA BOOTSTRAP -------");
        new EquipaTypeBootstrapper().execute();

        System.out.println("\n\n------- COLABORADOR BOOTSTRAP -------");
        new ColaboradorBootstrapper().execute();

        System.out.println("\n\n------- NIVEIS DE CRITICIDADE BOOTSTRAP -------");
        new NiveisCriticidadeBootstrapper().execute();

        System.out.println("\n\n------- EQUIPA BOOTSTRAP -------");
        new EquipaBootstrapper().execute();

        System.out.println("\n\n------- ATIVIDADES MANUAIS BOOTSTRAP -------");
        new AtividadesManuaisBootstrapper().execute();

        System.out.println("\n\n------- ATIVIDADES AUTOMATICAS BOOTSTRAP -------");
        new AtividadesAutomaticasBootstrapper().execute();

        System.out.println("\n\n------- EXECUÇÃO DOS CENÁRIOS -------");

        System.out.println("1 - Cenário um: [Serviço 1 - Pedido de Ausência Futura]");
        System.out.println("2 - Cenário dois: [Serviço 2 - Autorização para Aplicação de Desconto]");
        System.out.println("3 - Cenário três: [Serviço 3 - Alteração de Residência]");
        System.out.println("4 - Cenário quatro: [Serviço 4 - Requerer Cotação para Venda por Grosso]");
        System.out.println("0 - Para sair e continuar a executar os restantes bootstrappers");

        int opcao = -1;
        do {
            opcao = Console.readInteger("\nSelecione um cenário para executar");
            switch (opcao){
                case 1: new ServicoUmBootstrapper().execute(); break;
                case 2: new ServicoDoisBootstrapper().execute(); break;
                case 3: new ServicoTresBootstrapper().execute(); break;
                case 4: new ServicoQuatroBootstrapper().execute(); break;
                default:
                    System.out.println("Opção errada!");
                    break;
            }
        } while (opcao != 0);

        System.out.println("\n\n------- CATALOG BOOTSTRAP -------");
        new CatalogosBootstrapper().execute();

        System.out.println("\n\n------- SERVICO BOOTSTRAP -------");
        new ServicoBootstrapper().execute();

        System.out.println("\n\n------- PEDIDO RASCUNHO BOOTSTRAP -------");
        new PedidoRascunhoBootstrapper().execute();

        System.out.println("\n\n------- PEDIDO BOOTSTRAP -------");
        new PedidoBootstrapper().execute();



        /*if (isToBootstrapDemoData) {
        }

        if (isToRunSampleE2E) {
            System.out.println("\n\n------- BASIC SCENARIO -------");
            new BaseDemoSmokeTester().execute();
        }*/


    }

    private void handleArgs(final String[] args) {
        isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:basic");
        if (isToRunSampleE2E) {
            isToBootstrapDemoData = true;
        } else {
            isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
        }
    }

    @Override
    protected String appTitle() {
        return "Bootstrapping Base data ";
    }

    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }
}
