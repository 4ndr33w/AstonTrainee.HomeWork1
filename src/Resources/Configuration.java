package Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Properties properties;
    public static Properties getProperties() {
        return properties;
    }

    static {
        try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("./Resources/config.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
