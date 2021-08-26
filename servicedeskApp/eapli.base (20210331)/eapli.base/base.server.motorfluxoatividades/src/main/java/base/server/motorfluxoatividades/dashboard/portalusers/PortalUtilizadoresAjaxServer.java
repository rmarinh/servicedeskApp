package base.server.motorfluxoatividades.dashboard.portalusers;

import base.server.motorfluxoatividades.dashboard.config.HttpServerConfiguration;
import base.server.motorfluxoatividades.dashboard.config.HttpServerConfigurationManager;
import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.application.ListColaboradoresController;
import eapli.base.pedidomanagement.application.AssignarPedidoController;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.application.RegistarPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.base.pedidomanagement.repositories.PedidoRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PortalUtilizadoresAjaxServer implements Runnable {

    private static Username username;
    static private SSLServerSocket sock;
    private static ListPedidoController listPedidos;
    private static AssignarPedidoController assignarPedidoController;
    private static ListColaboradorService listColaboradorService;
    private static RegistarPedidoController pedidoController;

    public PortalUtilizadoresAjaxServer(Username user) {
        listPedidos = new ListPedidoController();
        PortalUtilizadoresAjaxServer.username = user;
        assignarPedidoController = new AssignarPedidoController();
        listColaboradorService = new ListColaboradorService();
        pedidoController = new RegistarPedidoController();
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

        System.setProperty("javax.net.ssl.keyStore", "base.server.motorfluxoatividades/src/main/java/base/server/motorfluxoatividades/dashboard/portalusers/server.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");

        try {
            HttpServerConfigurationManager.getInstance().loadConfigurationFile(
                    "base.server.motorfluxoatividades/src/main/resources/configuracao_portal_utilizadores.xml");
            HttpServerConfiguration conf = HttpServerConfigurationManager.getInstance().getCurrentConfiguration();

            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(conf.getPort());

            boolean keepAlive = true;
            while (keepAlive) {
                try {
                    cliSock = (SSLSocket) sock.accept();
                    PortalUtilizadoresAjaxRequest req = new PortalUtilizadoresAjaxRequest(cliSock, conf.getWebroot());
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
            ex.printStackTrace();
            System.out.println("Server failed to open local port ");
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

    public static String createHTML() {

        Iterable<Pedido> todosPedidosSubmetidos = listPedidos.allPedidosSubmetidosManuais();
        Iterable<Pedido> todosPedidosPorAprovar = listPedidos.allPedidosPorAprovarPorColaborador(username);
        Iterable<Pedido> todosPedidosPorExecutar = listPedidos.allPedidosPendentesManuais();
        Iterable<PedidoDTO> iterablePedidios = listPedidos.allPedidosCriadosPorColaborador(username);

        String textHtml = "<div class=\"container-sm p-3\">\n" +
                "  <hr><p>Utilizador: " + username.toString() +
                "</p><hr></div>";

        textHtml = textHtml +
                "<div class=\"container-sm\" >" +
                "<h2>Todos os pedidos por Submetidos</h2>" +
                "<table class=\"table\">\n" +
                "            <thead>\n" +
                "                <td scope=\"col\">#</td>\n" +
                "                <td scope=\"col\">Pedido ID</td>\n" +
                "                <td scope=\"col\">Status</td>\n" +
                "                <td scope=\"col\"></td>" +
                "            </thead>\n" +
                "            <tbody>";

        int i = 1;

        // Listagem Pedidos Submetidos
        for (Pedido p : todosPedidosSubmetidos) {
            textHtml = textHtml
                    + "<tr><th scope=\"row\">" + i
                    + "</th><td>" + p.identity()
                    + "</td><td>" + p.status()
                    + "</td><td>"
                    + "<button id=\""+p.identity()+"\"type=\"button\" class=\"btn btn-outline-info\" onClick=\"assignarPedidoParaAprovar(this.id)\">Assignar a mim</button>"
                    + "</td></tr>";
            i++;
        }

        textHtml = textHtml + "</tbody></table></div>";

        textHtml = textHtml +
                "<div class=\"container-sm\" >" +
                "<h2>Todos os pedidos por aprovar</h2>" +
                "<table class=\"table\">\n" +
                "            <thead>\n" +
                "                <td scope=\"col\">#</td>\n" +
                "                <td scope=\"col\">Pedido ID</td>\n" +
                "                <td scope=\"col\">Status</td>\n" +
                "                <td scope=\"col\"></td>" +
                "            </thead>\n" +
                "            <tbody>";

        int a = 1;

        // Listagem Pedidos Por Aprovar
        for (Pedido p : todosPedidosPorAprovar) {
            textHtml = textHtml
                    + "<tr><th scope=\"row\">" + a
                    + "</th><td>" + p.identity()
                    + "</td><td>" + p.status()
                    + "</td><td>"
                    + "<button id=\""+p.identity()+"\"type=\"button\" class=\"btn btn-outline-success mr-2\" onClick=\"aprovarPedido(this.id)\">Aprovar</button>"
                    + "<button id=\""+p.identity()+"\"type=\"button\" class=\"btn btn-outline-danger ml-2\" onClick=\"rejeitarPedido(this.id)\">Rejeitar</button>"
                    + "</td></tr>";
            a++;
        }

        textHtml = textHtml + "</tbody></table></div>";

        // Listagem Pedidos por Executar e Aprovados
        textHtml = textHtml +
                "<div class=\"container-sm\" >" +
                "<h2>Todos os pedidos aprovados por assignar</h2>" +
                "<table class=\"table\">\n" +
                "            <thead>\n" +
                "                <td scope=\"col\">#</td>\n" +
                "                <td scope=\"col\">Pedido ID</td>\n" +
                "                <td scope=\"col\">Status</td>\n" +
                "            </thead>\n" +
                "            <tbody>";

        int j = 1;
        for (Pedido p : todosPedidosPorExecutar) {
            textHtml = textHtml
                    + "<tr><th scope=\"row\">" + j
                    + "</th><td>" + p.identity()
                    + "</td><td>" + p.status()
                    + "</td><td>"
                    + "<button id=\""+p.identity()+"\"type=\"button\" class=\"btn btn-outline-info\" onClick=\"assignarPedidoAprovados(this.id)\">Assignar a mim</button>"
                    + "</td></tr>";
            j++;
        }

        textHtml = textHtml + "</tbody></table></div>";


        textHtml = textHtml +
                "<div class=\"container-sm\" >" +
                "<h2>Os meus pedidos</h2>" +
                "<table class=\"table\">\n" +
                "            <thead>\n" +
                "                <td scope=\"col\">#</td>\n" +
                "                <td scope=\"col\">Pedido ID</td>\n" +
                "                <td scope=\"col\">Status</td>\n" +
                "            </thead>\n" +
                "            <tbody>";

        int c = 1;
        for (PedidoDTO p : iterablePedidios) {
            textHtml = textHtml
                    + "<tr><th scope=\"row\">" + c
                    + "</th><td>" + p.pk
                    + "</td><td>" + p.status
                    + "</td></tr>";
            c++;
        }

        textHtml = textHtml + "</tbody></table></div>";
        return textHtml;
    }

    public static void sair() {
        System.out.println("Leaving server HTTP");
    }

    public static void assignarPedidoParaAprovar(String idPedido) {
        Pedido p = listPedidos.findPedidoById(Long.parseLong(idPedido)).get();
        assignarPedidoController.assignarPedidoSubmetido(p, listColaboradorService.findColaboradorByUsername(username).get());
    }

    public static void assignarPedidoAprovado(String idPedido) {
        Pedido p = listPedidos.findPedidoById(Long.parseLong(idPedido)).get();
        assignarPedidoController.assignarPedidoAprovado(p, listColaboradorService.findColaboradorByUsername(username).get());
    }

    public static void aprovarPedido(String idPedido) {
        System.out.println("APROVAR!");
        Pedido p = listPedidos.findPedidoById(Long.parseLong(idPedido)).get();
        pedidoController.aprovarPedido(p);

    }

    public static void rejeitarPedido(String idPedido) {
        Pedido p = listPedidos.findPedidoById(Long.parseLong(idPedido)).get();
        pedidoController.recusarPedido(p);
    }
}
