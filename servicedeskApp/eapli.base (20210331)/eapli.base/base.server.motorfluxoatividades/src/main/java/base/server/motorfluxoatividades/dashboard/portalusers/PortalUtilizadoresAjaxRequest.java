package base.server.motorfluxoatividades.dashboard.portalusers;

import base.server.motorfluxoatividades.dashboard.HTTPmessage;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PortalUtilizadoresAjaxRequest extends Thread {

    String baseFolder;
    SSLSocket sock;
    DataInputStream inS;
    DataOutputStream outS;
    boolean keepAline;

    public PortalUtilizadoresAjaxRequest(SSLSocket s, String f) {
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
                            PortalUtilizadoresAjaxServer.createHTML(), "text/html");
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
                    PortalUtilizadoresAjaxServer.sair();
                    this.keepAline = false;
                    response.setResponseStatus("200 Ok");
                } else if (request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/assignarPedidoParaAprovar/")) {
                    PortalUtilizadoresAjaxServer.assignarPedidoParaAprovar(request.getURI().substring(27));
                    System.out.println(request.getURI().substring(27));
                    response.setResponseStatus("200 Ok");
                }  else if (request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/assignarPedidoAprovado/")) {
                    PortalUtilizadoresAjaxServer.assignarPedidoAprovado(request.getURI().substring(24));
                    System.out.println(request.getURI().substring(24));
                    response.setResponseStatus("200 Ok");
                } else if (request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/aprovarPedido/")) {
                    PortalUtilizadoresAjaxServer.aprovarPedido(request.getURI().substring(15));
                    System.out.println(request.getURI().substring(15));
                    response.setResponseStatus("200 Ok");
                } else if (request.getMethod().equals("PUT")
                        && request.getURI().startsWith("/rejeitarPedido/")) {
                    PortalUtilizadoresAjaxServer.rejeitarPedido(request.getURI().substring(16));
                    System.out.println(request.getURI().substring(16));
                    response.setResponseStatus("200 Ok");
                } else {
                    response.setContentFromString(
                            "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>",
                            "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
                response.send(outS);
            }

           /* if (request.getMethod().equals("PUT")) {
                System.out.println("estou aqui");
                 else {
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
            }

            /*if (request.getMethod().equals("PUT")) {
                if (request.getURI().startsWith("/assignarPedidoAprovado/")) {
                    PortalUtilizadoresAjaxServer.assignarPedidoAprovado(request.getURI().substring(24));
                    System.out.println(request.getURI().substring(24));
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
            }*/
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
