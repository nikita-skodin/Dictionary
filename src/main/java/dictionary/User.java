package dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String userName;
    private final String password;
    private final String userMailAddress;

    private final Map<String, String> vocabulary;

    public User(String userName, String password, String userMailAddress) {
        this.userName = userName;
        this.password = password;
        this.userMailAddress = userMailAddress;
        this.vocabulary = new HashMap<>();
    }

    //можно добавить шифровку при хранении файла n+p
    public User getUser(String userName, String password) throws IOException {
        return null;
    }
}
