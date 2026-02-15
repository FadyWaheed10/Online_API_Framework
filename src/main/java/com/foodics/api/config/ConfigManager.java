package com.foodics.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Manager for managing environment properties
 * Handles loading of configuration from properties file
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        String environment = System.getProperty("env", "qa");
        String configFile = "src/test/resources/config/" + environment + ".properties";

        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + configFile, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getApiKey() {
        return getProperty("api.key");
    }

    public String getAuthToken() {
        return getProperty("auth.token");
    }

    public int getTimeout() {
        String timeout = getProperty("timeout");
        return timeout != null ? Integer.parseInt(timeout) : 30000;
    }
}
