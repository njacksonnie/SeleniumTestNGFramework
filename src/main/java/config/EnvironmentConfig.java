package config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class EnvironmentConfig {
    private static final String ENV = System.getProperty("env", "staging");
    private static final Map<String, Object> CONFIG = loadConfig();

    private static Map<String, Object> loadConfig() {
        Yaml yaml = new Yaml();
        // Use the class loader to load the resource
        InputStream input = EnvironmentConfig.class.getClassLoader().getResourceAsStream("config/environments.yaml");
        if (input == null) {
            throw new RuntimeException("environments.yaml NOT FOUND. Ensure it's in src/main/resources/config/");
        }

        try {
            Map<String, Map<String, Object>> environments = yaml.load(input);
            if (!environments.containsKey(ENV)) {
                throw new RuntimeException("Environment '" + ENV + "' not found. Valid options: " + environments.keySet());
            }
            return environments.get(ENV);
        } catch (YAMLException e) {
            throw new RuntimeException("Invalid YAML syntax: " + e.getMessage(), e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // -------------------- Public Methods --------------------
    public static String getBaseUrl() {
        if (!CONFIG.containsKey("base_url")) {
            throw new RuntimeException("Missing 'base_url' in environment '" + ENV + "' configuration");
        }
        return (String) CONFIG.get("base_url");
    }

    public static String getUsername() {
        if (!CONFIG.containsKey("username")) {
            throw new RuntimeException("Missing 'username' in environment '" + ENV + "' configuration");
        }
        return (String) CONFIG.get("username");
    }
}