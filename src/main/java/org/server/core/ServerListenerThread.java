package org.server.core;

import org.server.config.HttpConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// ServerListenerThread class to handle incoming connections on a specified port
public class ServerListenerThread extends Thread {
    private final int port;
    private final String webroot;
    private ServerSocket serverSocket;

    // Constructor to initialize the server with a port and webroot (currently not used)
    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        // Start the server and listen for connections
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Accept a connection from a client
                Socket socket = serverSocket.accept();

                // Get input and output streams from the socket
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // Create a simple HTML response
                String html = "<html>" +
                        "<head><title>Document</title></head>" +
                        "<body><h1>My First Heading</h1></body>" +
                        "</html>";
                final String CRLF = "\r\n"; // Carriage Return Line Feed for HTTP protocol
                String response = "HTTP/1.1 200 OK" + CRLF + // Status Line: HTTP Version and Status Code
                        "Content-Type: text/html; charset=UTF-8" + CRLF + // Content-Type header
                        "Content-Length: " + html.length() + CRLF + // Content-Length header
                        CRLF + // Blank line indicating the end of headers
                        html; // Response body

                // Send the response to the client
                outputStream.write(response.getBytes());

                // Close the streams and socket
                inputStream.close();
                outputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            // Handle server errors and throw a custom exception
            throw new HttpConfigurationException("Server error: " + e.getMessage(), e);
        }
    }
}
