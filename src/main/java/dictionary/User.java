package dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userMailAddress='" + userMailAddress + '\'' +
                ", vocabulary=" + vocabulary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(userMailAddress, user.userMailAddress) && Objects.equals(vocabulary, user.vocabulary);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userName, password, userMailAddress, vocabulary);
    }
}
