package org.server.core;
import org.server.config.HttpConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

// ServerListenerThread class to handle incoming connections on a specified port
public class ServerListenerThread extends Thread {
    private final ServerSocket serverSocket;

    // Constructor to initialize the server with a port
    public ServerListenerThread(int port, String webroot) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (SocketException e) {
            throw new HttpConfigurationException("Failed to create server socket", e);
        }
    }

    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()) {

                    String html = "<html><head><title>Document</title></head><body><h1>Hello</h1></body></html>";
                    final String CRLF = "\r\n";
                    String response = "HTTP/1.1 200 OK" + CRLF +
                            "Content-Length: " + html.length() + CRLF +
                            CRLF +
                            html;

                    outputStream.write(response.getBytes());
                } catch (IOException e) {
                    System.err.println("Failed to process request: " + e.getMessage());
                }
            }
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.err.println("Failed to close server socket: " + e.getMessage());
                }
            }
        }
    }
}
