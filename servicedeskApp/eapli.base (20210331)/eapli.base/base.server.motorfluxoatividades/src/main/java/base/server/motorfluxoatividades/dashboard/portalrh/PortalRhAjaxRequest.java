package base.server.motorfluxoatividades.dashboard.portalrh;

import base.server.motorfluxoatividades.dashboard.HTTPmessage;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PortalRhAjaxRequest extends Thread {

    String baseFolder;
    SSLSocket sock;
    DataInputStream inS;
    DataOutputStream outS;
    boolean keepAline;

    public PortalRhAjaxRequest(SSLSocket s, String f) {
        this.baseFolder = f;
        this.sock = s;
        this.keepAline = true;
    }

    public synchronized boolean stillRunning() {
        return this.keepAline;
    }

    public void run() {

        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }

        try {
            HTTPmessage request = new HTTPmessage(inS);
            HTTPmessage response = new HTTPmessage();
            // System.out.println(request.getURI());


            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/votes")) {
                    response.setContentFromString(
                            PortalRhAjaxServer.createHTML(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) fullname = fullname + "index.html";
                    else fullname = fullname + request.getURI();
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString(
                                "<html><body><h1>404 File not found</h1></body></html>",
                                "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            } else { // NOT GET
                if (request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/sair")) {
                    PortalRhAjaxServer.castVote();
                    this.keepAline = false;
                    response.setResponseStatus("200 Ok");
                } else {
                    response.setContentFromString(
                            "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>",
                            "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
                response.send(outS);
            }
        } catch (IOException ex) {
            //System.out.println("Thread error when reading request");
        }
        try {
            sock.close();
        } catch (IOException ex) {
            System.out.println("CLOSE IOException");
        }
        synchronized (this) {
            notify();
        }
    }
}
