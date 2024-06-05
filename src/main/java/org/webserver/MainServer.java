package org.webserver;

import org.webserver.Config.Configuration;
import org.webserver.Config.ConfigurationManager;

public class MainServer {
    public static void main(String[] args) {

        System.out.println("Starting server ...");
        ConfigurationManager.getInstance().loadConfigurationFiles("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getConfiguration();

        System.out.println(conf.getPort());
        System.out.println(conf.getWebroot());
    }
}