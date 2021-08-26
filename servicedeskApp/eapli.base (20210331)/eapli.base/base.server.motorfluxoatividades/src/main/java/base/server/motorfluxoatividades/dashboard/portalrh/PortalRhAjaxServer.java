package base.server.motorfluxoatividades.dashboard.portalrh;

import base.server.motorfluxoatividades.dashboard.config.HttpServerConfiguration;
import base.server.motorfluxoatividades.dashboard.config.HttpServerConfigurationManager;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;

public class PortalRhAjaxServer implements Runnable {

    private static Username username;
    private static SSLServerSocket sock;

    public PortalRhAjaxServer(Username user) {

        PortalRhAjaxServer.username = user;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        SSLSocket cliSock;

        File file = new File("base.server.motorfluxoatividades/src/main/java/base/server/motorfluxoatividades/dashboard/portalrh/server.jks");
        System.out.println(file.exists());

        System.setProperty("javax.net.ssl.keyStore", "base.server.motorfluxoatividades/src/main/java/base/server/motorfluxoatividades/dashboard/portalrh/server.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");

        try {
            HttpServerConfigurationManager.getInstance().loadConfigurationFile(
                    "base.server.motorfluxoatividades/src/main/resources/configuracao_portal_recursos_humanos.xml");
            HttpServerConfiguration conf = HttpServerConfigurationManager.getInstance().getCurrentConfiguration();

            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(conf.getPort());

            boolean keepAlive = true;
            while (keepAlive) {
                try {
                    cliSock = (SSLSocket) sock.accept();
                    PortalRhAjaxRequest req = new PortalRhAjaxRequest(cliSock, conf.getWebroot());
                    synchronized (req) {
                        req.start();
                        //synchronized wait block
                        req.wait();
                        //synchronized notify block
                        keepAlive = req.keepAline;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {

                }
            }
            closeConnetion();
        } catch (IOException ex) {
            System.out.println("Server failed to open local port.");
            System.exit(1);
        }
    }

    public void closeConnetion() {
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED

    public static synchronized String createHTML() {

        PedidoRepository pedidoRepository = PersistenceContext.repositories().pedidos();

        int totalPedidos = pedidoRepository.totalPedidos();
        int totalPedidosConcluidos = pedidoRepository.totalPedidosConcluídos();
        int totalPedidosSubmetidos = pedidoRepository.totalPedidosSubmetidos();
        int totalPedidosRejeitados = pedidoRepository.totalPedidosRejeitados();
        int totalPedidosEmAprovacao = pedidoRepository.totalPedidosEmAprovacao();
        int totalPedidosAprovados = pedidoRepository.totalPedidosAprovados();
        int totalPedidosEmExecucao = pedidoRepository.totalPedidosEmExecucao();

        String textHtml = "<div class=\"container-sm p-3\">\n" +
                "  <hr><p>Utilizador: " + username.toString() +
                "</p><hr></div>";

        textHtml = textHtml + "<div class=\"row row-cols-1 row-cols-md-3 g-4\">\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Total Pedidos</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidos + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Pedidos Submetidos</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidosSubmetidos + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Pedidos Rejeitados</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidosRejeitados + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Pedidos Conluídos</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidosConcluidos + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Em Aprovação</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidosEmAprovacao + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Em Execução</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidosEmExecucao + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"col\">\n" +
                "            <div class=\"card h-100 bg-info\">\n" +
                "                <div class=\"card-body\">\n" +
                "                    <h3 class=\"card-title text-light\">Pedidos Aprovados</h3>\n" +
                "                    <p class=\"card-text\">" + totalPedidosAprovados + "</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "</div>\n";
        return textHtml;
    }

    public static synchronized void castVote() {
        System.out.println("Leaving server HTTP");
    }
}
