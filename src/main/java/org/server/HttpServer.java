package org.server;

import org.server.config.Configuration;
import org.server.config.ConfigurationManager;
import org.server.config.HttpConfigurationException;
import org.server.core.ServerListenerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server started...");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        int port = conf.getPort();
        String webroot = conf.getWebroot();

        System.out.println("Using webroot: " + port);
        System.out.println("Using port: " + webroot);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                String html = "<html><head><title>Document</title></head><body><h1>Hello</h1></body></html>";
                final String CRLF = "\r\n";
                String response = "HTTP/1.1 200 OK" + CRLF +
                        "Content-Length: " + html.length() + CRLF +
                        CRLF +
                        html;

                outputStream.write(response.getBytes());
                inputStream.close();
                outputStream.close();
                socket.close();

            }

        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
    }
}