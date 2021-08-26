package base.server.executortarefasautomaticas.presentation;

import javax.annotation.security.RunAs;
import java.net.Socket;

public class TcpServerClientHandler implements Runnable {

    private Socket mySocket;
    private final String BASE_FOLDER = "www";

    public TcpServerClientHandler(Socket s) {
        mySocket = s;
    }

    public void run() {
        try {
            Server.trataConexao(mySocket);
            // the client wants to exit
            Server.removeClient(mySocket);
            System.out.print("A conexão está fechada " + mySocket.toString());

        } catch (Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }
}
