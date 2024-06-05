package org.webserver.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.webserver.util.Json;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ConfigurationManager {
    private static ConfigurationManager newConfigurationManager;
    private static Configuration newConfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (newConfigurationManager == null) {
            newConfigurationManager = new ConfigurationManager();
        }
        return newConfigurationManager;
    }

    /*
     * To load a configuration file by the path provided
     */
    public void loadConfigurationFiles(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            StringBuffer sb = new StringBuffer();
            int i;
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
            JsonNode conf = Json.parse(sb.toString());
            newConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (FileNotFoundException e) {
            throw new HttpConfigException(e);
        } catch (IOException e) {
            throw new HttpConfigException(e);
        }
    }

    /*
     * To get the configuration instance
     */
    public Configuration getConfiguration() {
        if (newConfiguration == null) {
            throw new HttpConfigException("Configuration has not been loaded.");
        }
        return newConfiguration;
    }
}
