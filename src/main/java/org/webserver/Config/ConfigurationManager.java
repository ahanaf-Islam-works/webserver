package org.webserver.Config;

public class ConfigurationManager {
    public static ConfigurationManager newConfigurationManager;
    private static Configuration newConfiguration;
    private ConfigurationManager() {}
    public ConfigurationManager getInstance() {
        if (newConfigurationManager == null){
            newConfigurationManager = new ConfigurationManager();
        }
        return newConfigurationManager;
    }
    /*
    * To load a configuration file by the path provided
    */
    public void loadConfigurationFiles(String path){}

    /*
    * To save a configuration file by the path provided
    */

    public void getConfigurationFiles(){}
}
