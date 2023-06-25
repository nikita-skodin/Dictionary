package dictionary;

import java.io.IOException;
import java.util.Map;

public class User {
    private String userName;
    private String password;
    private String userMailAddress;

    Map<String, String> vocabulary;

    //можно добавить шифровку при хранении файла n+p
    public User getUser(String userName, String password) throws IOException {
        return null;
    }
}
