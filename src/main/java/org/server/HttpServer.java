package org.server;

import org.server.config.Configuration;
import org.server.config.ConfigurationManager;
import org.server.core.ServerListenerThread;
import java.io.IOException;


public class HttpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started...");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        int port = conf.getPort();
        String webroot = conf.getWebroot();

        System.out.println("Using webroot: " + port);
        System.out.println("Using port: " + webroot);

        ServerListenerThread thread = new ServerListenerThread(port, webroot);
        thread.start();
    }
}