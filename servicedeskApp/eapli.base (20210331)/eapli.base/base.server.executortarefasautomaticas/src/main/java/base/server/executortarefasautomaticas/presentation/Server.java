package base.server.executortarefasautomaticas.presentation;

import base.server.executortarefasautomaticas.protocol.ExecutarPedidoProtocolMessageParser;
import base.server.executortarefasautomaticas.protocol.ExecutarPedidoProtocolRequest;

import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import java.io.*;

import java.util.HashMap;

public class Server {

    private SSLServerSocket serverSocket;

    static final String TRUSTED_STORE = "base.server.executortarefasautomaticas/src/main/java/base/server/executortarefasautomaticas/presentation/server_J.jks";
    static final String KEYSTORE_PASS = "forgotten";

    private static HashMap<Socket, DataOutputStream> clientList = new HashMap<>();

    public static synchronized void addClient(Socket s) throws Exception {
        clientList.put(s, new DataOutputStream(s.getOutputStream()));
    }

    public static synchronized void removeClient(Socket s) throws Exception {
        //clientList.get(s).write(0);
        clientList.remove(s);
    }

    private static void fechaSocket(Socket s) throws IOException {
        s.close();
    }

    public void criarServerSocket(int porta) throws IOException {

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            this.serverSocket = (SSLServerSocket) sslF.createServerSocket(porta);
            this.serverSocket.setNeedClientAuth(true);

        } catch (IOException ex) {
            System.out.println("Porta não disponível.");
            System.exit(1);
        }
    }

    public Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    public static void trataConexao(Socket socket) throws IOException {

        //protocolo da aplicação
        try {
            // Criar streams de entrada e saida
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            System.out.println("A tratar mensagem...");

            Message recebida = null;
            do {
                //Ler objecto Mensagem
                recebida = (Message) input.readObject();
                System.out.println("Mensagem recebida: ");
                System.out.println(recebida.toString());

                ExecutarPedidoProtocolRequest eppr =
                        ExecutarPedidoProtocolMessageParser.parse(recebida.versao() + "," + recebida.codigo() + ","
                                                                    + recebida.numeroBytes() + "," + recebida.dados());
                String response = eppr.execute();

                Message resposta = new Message(
                        Integer.parseInt(response.split(",")[0]),
                        Integer.parseInt(response.split(",")[1]),
                        Integer.parseInt(response.split(",")[2]),
                        response.split(",")[3]);

                output.writeObject(resposta);
                output.flush();
            } while (recebida.codigo() != 1);

            // close comunications
            input.close();
            output.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            fechaSocket(socket);
        }
    }
}