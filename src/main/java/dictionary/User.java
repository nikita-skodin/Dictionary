package dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.Map;

public class User {
    private String userName;
    private String password;
    private String userMailAddress;

    Map<String, String> vocabulary;

    //можно добавить шифровку при хранении файла n+p
    public User getUser(String userName, String password) throws IOException {

        UtilProperties utilProperties = new UtilProperties(String.format("src/main/resources/usersData/%s.properties", userName+password));
        for (var el : utilProperties.getEntrySet()) {
            System.out.println(el.getKey() + "=" + el.getValue());
        }

        return null;
    }
}
