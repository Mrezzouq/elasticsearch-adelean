package org.adelean.elasticsearch.utils;

import org.adelean.elasticsearch.exception.ConfigurationLoadException;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtils implements ConfigurationProvider {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (var inputStream = ConfigurationUtils.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null) {
                throw new ConfigurationLoadException("Configuration file 'application.properties' not found");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new ConfigurationLoadException("Failed to load configuration file");
        }
    }

    @Override
    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
