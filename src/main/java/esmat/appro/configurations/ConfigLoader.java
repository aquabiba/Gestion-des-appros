package esmat.appro.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Désolé, impossible de trouver le fichier config.properties");
            }
            // Charger les propriétés
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement des propriétés", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}

