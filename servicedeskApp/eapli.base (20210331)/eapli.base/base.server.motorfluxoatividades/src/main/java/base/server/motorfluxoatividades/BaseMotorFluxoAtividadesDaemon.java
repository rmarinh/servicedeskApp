package base.server.motorfluxoatividades;

import base.server.motorfluxoatividades.dashboard.config.HttpServerConfiguration;
import base.server.motorfluxoatividades.dashboard.config.HttpServerConfigurationManager;
import base.server.motorfluxoatividades.fluxoatividades.automaticas.GestorPedidosAutomaticos;
import base.server.motorfluxoatividades.fluxoatividades.automaticas.MotorFluxoAtividadesClient;
import base.server.motorfluxoatividades.fluxoatividades.config.MotorFluxoConfiguration;
import base.server.motorfluxoatividades.fluxoatividades.config.MotorFluxoConfigurationManager;
import base.server.motorfluxoatividades.fluxoatividades.manuais.GestorPedidosManuais;
import base.server.motorfluxoatividades.fluxoatividades.utils.NiveisPrioridade;
import base.server.motorfluxoatividades.fluxoatividades.utils.SortPedidoByNiveisCriticidade;
import eapli.base.app.common.console.BaseServerApplication;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.eventpubsub.EventDispatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("squid:S106")
public final class BaseMotorFluxoAtividadesDaemon extends BaseServerApplication {

    public static int NUMBER_OF_THREADS = 2;

    /**
     * avoid instantiation of this class.
     */
        private BaseMotorFluxoAtividadesDaemon() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        new BaseMotorFluxoAtividadesDaemon().run(args);
    }

    @Override
    protected void doMain(String[] args) {
        printTitleDrawing();

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        MotorFluxoConfigurationManager.getInstance().loadConfigurationFile(
                "base.server.motorfluxoatividades/src/main/resources/configuracao_motor_fluxo.xml");
        MotorFluxoConfiguration conf = MotorFluxoConfigurationManager.getInstance().getCurrentConfiguration();

        /*System.out.println("Tipo: " + conf.tipo());
        System.out.println("AlgColaborador: " + conf.algoritmoColaborador());
        System.out.println("AlgExecutor: " + conf.algoritmoExecutor());

        NiveisPrioridade niveisPrioridade = new NiveisPrioridade();

        for (int i=0; i<5; i++) {
            for (int ii=0; ii<6; ii++) {
                System.out.print(NiveisPrioridade.TABELA_PRIORIDADES[i][ii] + " ");
            }
            System.out.println("");
        }

        System.out.println("\n\n");

        AuthenticationService authenticationService = AuthzRegistry.authenticationService();
        authenticationService.authenticate("helpdesk", "passA1", BaseRoles.GESTOR_HELPDESK).isPresent();

        ListPedidoController controller = new ListPedidoController();
        List<Pedido> opt = controller.allPedidosPorAprovarAutomaticos();

        for (Pedido p : opt) {

            int tempoDecorrido = p.tempoDecorrido().intValue();
            int tempoMaximo =  Integer.parseInt(p.servico().nivelCrtiticidade().tempoMaximoAprovacao().toString());
            double tempoRestante = 0;
            double diferenca = 0;

            if (tempoDecorrido > tempoMaximo) {
                tempoRestante = (tempoDecorrido*100) / tempoMaximo;
            }else{
                diferenca = tempoMaximo-tempoDecorrido;
                tempoRestante = diferenca / tempoMaximo;
                tempoRestante = tempoRestante * 100;
            }

            tempoRestante= Math.round(tempoRestante);

            int nivelCriticidade = Integer.parseInt(p.servico().nivelCrtiticidade().escalaNumerica().toString());
            System.out.println(p.identity() + "\n" +
                    " Nivel criticidade: " + p.servico().nivelCrtiticidade().etiqueta()   + "\n"+
                    " Cri " + nivelCriticidade   + "\n"+
                    " Tempo Maximo: " + tempoMaximo  + "\n"+
                    " Tempo Decorrido: " + tempoDecorrido + "\n" +
                    " Tempo restante: " + diferenca + "\n" +
                    " Tempo Decorrido (%):" + tempoRestante + "%\n" +
                    " Valor tabela " + niveisPrioridade.getPriority((int)tempoRestante, nivelCriticidade));
        }

        System.out.println("    ");

        Collections.sort(opt, new SortPedidoByNiveisCriticidade());

        for (Pedido pedido : opt) {
            System.out.println(pedido);
        }*/

        // Classe responsável pela assignment das tarefas manuais aos colaboradores
        Thread[] myThreads = new Thread[NUMBER_OF_THREADS];
        myThreads[0] = new GestorPedidosManuais();
        myThreads[0].start();

        // Arrancar com as Threads Pedidos Automaticos - Comunicacao com Executor de Tarefas Automaticas Daemon
        myThreads[1] = new GestorPedidosAutomaticos();
        myThreads[1] .start();

        // Esperar que as threads terminem
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            try {
                myThreads[i].join();
            } catch (InterruptedException ex) {
                //Logger.getLogger(mes.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
            System.out.println("Thread " + i + " joined!");
        }

        // exiting the application, closing all threads
        System.exit(0);
    }

    @Override
    protected String appTitle() {
        return "Motor de Fluxo de Atividades - Server";
    }

    @Override
    protected String appGoodbye() {
        return "See you later!";
    }

    @Override
    protected void doSetupEventHandlers(EventDispatcher dispatcher) {

    }

    public void printTitleDrawing() {
        System.out.println(" ,ggg, ,ggg,_,ggg,                                                    ,gggggggggggggg");
        System.out.println("dP\"\"Y8dP\"\"Y88P\"\"Y8b                 I8                               dP\"\"\"\"\"\"88\"\"\"\"\"\" ,dPYb,");
        System.out.println("Yb, `88'  `88'  `88                 I8                               Yb,_    88       IP'`Yb");
        System.out.println(" `\"  88    88    88              88888888                             `\"\"    88       I8  8I");
        System.out.println("     88    88    88                 I8                                    ggg88gggg   I8  8'");
        System.out.println("     88    88    88    ,ggggg,      I8      ,ggggg,     ,gggggg,             88   8   I8 dP  gg      gg     ,gg,   ,gg   ,ggggg,");
        System.out.println("     88    88    88   dP\"  \"Y8ggg   I8     dP\"  \"Y8ggg  dP\"\"\"\"8I             88       I8dP   I8      8I    d8\"\"8b,dP\"   dP\"  \"Y8ggg");
        System.out.println("     88    88    88  i8'    ,8I    ,I8,   i8'    ,8I   ,8'    8I       gg,   88       I8P    I8,    ,8I   dP   ,88\"    i8'    ,8I");
        System.out.println("     88    88    Y8,,d8,   ,d8'   ,d88b, ,d8,   ,d8'  ,dP     Y8,       \"Yb,,8P      ,d8b,_ ,d8b,  ,d8b,,dP  ,dP\"Y8,  ,d8,   ,d8'");
        System.out.println("     88    88    `Y8P\"Y8888P\"    88P\"\"Y88P\"Y8888P\"    8P      `Y8         \"Y8P'      8P'\"Y888P'\"Y88P\"`Y88\"  dP\"   \"Y88P\"Y8888P\"");
        System.out.println("");
        System.out.println("                                                                 8I");
        System.out.println("                                                                 8I");
        System.out.println("                                                                 8I");
        System.out.println("                                                                 8I");
        System.out.println("                                                           ,gggg,8I   ,ggg,");
        System.out.println("                                                          dP\"  \"Y8I  i8\" \"8i");
        System.out.println("                                                         i8'    ,8I  I8, ,8I");
        System.out.println("                                                        ,d8,   ,d8b, `YbadP'");
        System.out.println("                                                        P\"Y8888P\"`Y8888P\"Y888");
        System.out.println("");
        System.out.println("                     dP\"\"8I     I8                                       8I                      8I");
        System.out.println("                    dP   88     I8                                       8I                      8I");
        System.out.println("                   dP    88  88888888  gg                   gg           8I                      8I");
        System.out.println("                  ,8'    88     I8     \"\"                   \"\"           8I                      8I");
        System.out.println("                  d88888888     I8     gg      ggg    gg    gg     ,gggg,8I    ,gggg,gg    ,gggg,8I   ,ggg,     ,g,");
        System.out.println("            __   ,8\"     88     I8     88     d8\"Yb   88bg  88    dP\"  \"Y8I   dP\"  \"Y8I   dP\"  \"Y8I  i8\" \"8i   ,8'8,");
        System.out.println("           dP\"  ,8P      Y8    ,I8,    88    dP  I8   8I    88   i8'    ,8I  i8'    ,8I  i8'    ,8I  I8, ,8I  ,8'  Yb");
        System.out.println("           Yb,_,dP       `8b, ,d88b, _,88,_,dP   I8, ,8I  _,88,_,d8,   ,d8b,,d8,   ,d8b,,d8,   ,d8b, `YbadP' ,8'_   8)");
        System.out.println("            \"Y8P\"         `Y888P\"\"Y888P\"\"Y88\"     \"Y8P\"   8P\"\"Y8P\"Y8888P\"`Y8P\"Y8888P\"`Y8P\"Y8888P\"`Y8888P\"Y888P' \"YY8P8P");
    }
}
