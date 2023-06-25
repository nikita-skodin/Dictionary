package dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * before Platform.exit() you must call STORE method for save data in .properties
 */
public final class UtilProperties {
    private final static Logger LOGGER = LoggerFactory.getLogger(UtilProperties.class);
    private final Path PATH_TO_FILE;
    private final Properties PROPERTIES;

    public UtilProperties(String pathToFile) {
        this.PATH_TO_FILE = Paths.get(pathToFile);
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(Files.newInputStream(PATH_TO_FILE));
        } catch (IOException e) {
            LOGGER.error("file .properties is not exists, in constructor");
        }
    }

    public void store() {
        try {
            PROPERTIES.store(Files.newOutputStream(PATH_TO_FILE), null);
        } catch (IOException e) {
            LOGGER.error("file .properties is not exists in STORE method");
        }
    }

    public void setProp(String key, String value) {
        PROPERTIES.put(key, value);
    }

    public String getProp(String key) {
        return (String) PROPERTIES.get(key);
    }

    public Set<Object> getKeySet() {
        return PROPERTIES.keySet();
    }

    public Set<Map.Entry<Object, Object>> getEntrySet() {
        return PROPERTIES.entrySet();
    }

}
