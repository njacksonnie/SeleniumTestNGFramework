package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getResourceAsStream("/config/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }
}