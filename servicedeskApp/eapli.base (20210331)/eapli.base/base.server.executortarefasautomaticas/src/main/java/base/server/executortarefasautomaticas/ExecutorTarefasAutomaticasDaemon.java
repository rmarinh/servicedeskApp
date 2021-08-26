package base.server.executortarefasautomaticas;

import base.server.executortarefasautomaticas.presentation.Server;
import base.server.executortarefasautomaticas.presentation.TcpServerClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("squid:S106")
public final class ExecutorTarefasAutomaticasDaemon {

    // TODO: read port number from property file
    // à aplicação servidora aceitar pedidos de ligação TCP no porto número 32507.

    private static final int BOOKING_PORT = 32507;
    private static final Logger LOGGER = LogManager.getLogger(ExecutorTarefasAutomaticasDaemon.class);

    /**
     * Avoid instantiation of this class.
     */
    private ExecutorTarefasAutomaticasDaemon() {
    }

    public static void main(final String[] args) throws Exception {
        LOGGER.info("Configuring the daemon");

        Server server = new Server();

        // Creating Threadpool with the thread size given above
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try {
            server.criarServerSocket(BOOKING_PORT);
        } catch (IOException ex) {
            System.out.println("Local port number not available.");
            System.exit(1);
        }

        while (true) {
            System.out.println("À espera de um novo pedido de conexão de um novo cliente.");

            // À espera de um novo pedido de conexão de um novo cliente
            Socket s = server.esperaConexao();
            System.out.println("Cliente " + s.toString() + " conectado.");
            server.addClient(s);
            TcpServerClientHandler client = new TcpServerClientHandler(s);
            executor.execute(client);
        }

        /*AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new CafeteriaPasswordPolicy(),
                new PlainTextEncoder());*/

      /*  LOGGER.info("Starting the server socket");
        final ExecutorTarefasAutomaticasServer server = new ExecutorTarefasAutomaticasServer();
        server.start(BOOKING_PORT, true);

        LOGGER.info("Exiting the daemon");*/
        //System.exit(0);
    }
}