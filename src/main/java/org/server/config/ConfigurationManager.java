package org.server.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.server.util.Json;
import java.io.FileReader;
import java.io.IOException;

// ConfigurationManager class to manage configuration settings
public class ConfigurationManager {
    // Singleton instance of ConfigurationManager
    private static ConfigurationManager newConfigurationManager;
    // Current configuration loaded from file
    private static Configuration newCurrentConfiguration;

    // Private constructor to enforce singleton pattern
    private ConfigurationManager() {}

    // Method to get the singleton instance of ConfigurationManager
    public static ConfigurationManager getInstance() {
        if (newConfigurationManager == null) {
            newConfigurationManager = new ConfigurationManager();
        }
        return newConfigurationManager;
    }

    // Method to load configuration from a file
    public void loadConfiguration(String filepath) {
        try (FileReader fileReader = new FileReader(filepath)) {
            StringBuilder stringBuilder = new StringBuilder();
            int i;
            // Read the file character by character and build the string
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
            // Parse the string into a JsonNode
            JsonNode conf = Json.parse(stringBuilder.toString());
            // Convert the JsonNode into a Configuration object
            newCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (IOException e) {
            // Throw custom exception if an IO error occurs
            throw new HttpConfigurationException(e);
        }
    }

    // Method to get the current configuration
    public Configuration getCurrentConfiguration() {
        if (newCurrentConfiguration == null) {
            // Throw custom exception if no configuration is set
            throw new HttpConfigurationException("No current configuration Set.");
        }
        return newCurrentConfiguration;
    }
}