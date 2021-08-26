package base.server.motorfluxoatividades.fluxoatividades.automaticas;

import base.server.executortarefasautomaticas.presentation.Message;
import eapli.base.pedidomanagement.domain.Pedido;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server socket for booking daemon.
 */
public class MotorFluxoAtividadesClient {

    //TODO: LER DIRETAMENTE DO FICHEIRO DE CONFIG AS CONFIG DO SERVER

    static InetAddress serverIP;
    static SSLSocket sock;

    static final String KEYSTORE_PASS = "forgotten";
    static final int SERVER_PORT = 32507;

    public Pedido pedido;
    public int type;

    public MotorFluxoAtividadesClient(Pedido pedido, int type) {
        this.pedido = pedido;
        this.type = type;
    }

    public void run() {
        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", "base.app.user.console/src/main/java/eapli/base/app/user/console/HttpClient/server_J.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", "base.app.user.console/src/main/java/eapli/base/app/user/console/HttpClient/client1_J.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server: 127.0.0.1");
            System.exit(1);
        }

        System.out.println("Estabelecendo conexão...");
        try {
            sock = (SSLSocket) sf.createSocket(serverIP, SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Failed to connect. ");
            ex.printStackTrace();
            System.exit(1);
        }

        try {
            sock.startHandshake();
            SSLSession ssl = sock.getSession();

            System.out.println("------------------------------------------------------");
            System.out.println("Conectado ao servidor");
            System.out.println("------------------------------------------------------");
            System.out.println("Versao SSL/TLS: " + ssl.getProtocol() + "\nCypher suite: " + ssl.getCipherSuite());
            System.out.println("------------------------------------------------------");


            ObjectOutputStream sOut = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream sIn = new ObjectInputStream(sock.getInputStream());

            Message msgPedido = new Message(1, type, pedido.identity().toString().length(), pedido.identity().toString());
            sOut.writeObject(msgPedido);
            sOut.flush(); //libertar buffer para envio

            System.out.println("\n------------------------------------------------------");
            System.out.println("Mensagem enviada:");
            System.out.println(msgPedido.toString());
            System.out.println("------------------------------------------------------");

            Message respostaPedido = (Message) sIn.readObject();
            System.out.println("\n------------------------------------------------------");
            System.out.println("Mensagem recebida:");
            System.out.println(respostaPedido.toString());
            System.out.println("------------------------------------------------------");

            Message msgTerminar = new Message(1, 1, 0, "");
            sOut.writeObject(msgTerminar);
            sOut.flush(); //libertar buffer para envio

            System.out.println("\n------------------------------------------------------");
            System.out.println("Mensagem para terminar enviada:");
            System.out.println(msgTerminar.toString());
            System.out.println("------------------------------------------------------");

            Message respostaTerminar = (Message) sIn.readObject();
            System.out.println("\n------------------------------------------------------");
            System.out.println("Resposta ao comando terminar recebida:");
            System.out.println(respostaTerminar.toString());
            System.out.println("------------------------------------------------------");

            System.out.println("\n------------------------------------------------------");
            System.out.println("Conexão do servidor terminada.");
            System.out.println("------------------------------------------------------");

            sIn.close();
            sOut.close();
            sock.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
