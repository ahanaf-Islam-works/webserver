package org.webserver.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.webserver.util.Json;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

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
    public void loadConfigurationFiles(String path){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        }
        catch (FileNotFoundException e){
            throw new HttpConfigException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        }
        catch (IOException e){
            throw new HttpConfigException(e);
        }
        JsonNode conf =null;
        try {
            conf = Json.parse(sb.toString());
        }
        catch (IOException e){
            throw new HttpConfigException(e);
        }
        }

    /*
     * To get the configuration instance
     */
    public Configuration getConfiguration() {
        if (newConfigurationManager == null) {
            throw new HttpConfigException("Error: ConfigurationManager");
        }
        return newConfiguration;
    }

}



