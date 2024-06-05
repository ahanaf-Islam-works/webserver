package org.webserver;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.webserver.Config.Configuration;
import org.webserver.Config.ConfigurationManager;
import org.webserver.util.Json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("Starting server ...");

        ConfigurationManager.getInstance().loadConfigurationFiles("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getConfiguration();
        System.out.println(conf.getPort());
        System.out.println(conf.getWebroot());

        try (ServerSocket serverSocket = new ServerSocket(conf.getPort())) {
            System.out.println("Server started on port " + conf.getPort());

            // Continuous loop to accept multiple connections
            while (true) {
                try (Socket acceptedSocket = serverSocket.accept();
                     InputStream inputStream = acceptedSocket.getInputStream();
                     OutputStream outputStream = acceptedSocket.getOutputStream()) {


                    // Response as JSON response
                    String html = "<html><head><title>Test</title></head><body><p>Testing</p></body></html>";

                    final String CRLF = "\r\n";
                    String response = "HTTP/1.1 200 OK" + CRLF +
                            "Content-Length: " + html.length() + CRLF +
                            CRLF +
                            html;

                    outputStream.write(response.getBytes());

                    inputStream.close();
                    outputStream.close();
                    acceptedSocket.close();
                    acceptedSocket.close();
                    serverSocket.close();


                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
