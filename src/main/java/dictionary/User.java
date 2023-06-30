package dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonAutoDetect
public class User {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    private static User currentUser = null;
    private static ArrayList<User> currentUserNotes = null;

    private String userName;
    private String password;
    private String userMailAddress;
    private Map<String, String> vocabulary;

    public User() {
    }

    public User(String userName, String password, String userMailAddress) {
        this.userName = userName;
        this.password = password;
        this.userMailAddress = userMailAddress;
        this.vocabulary = new HashMap<>();
    }

    //можно добавить шифровку при хранении файла n+p
    public static User getUser(String userName, String password){
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(String.format("src/main/resources/usersData/%s", userName+password+".json"));
        try {
            return objectMapper.readValue(Files.newInputStream(path), User.class);
        } catch (IOException e) {
            LOGGER.info("Attempt to get user failed");
            return null;
        }
    }

    public static void addUser(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(String.format("src/main/resources/usersData/%s", user.userName+user.password+".json"));
        try {
            objectMapper.writeValue(Files.newOutputStream(path), user);
        } catch (IOException e) {
            LOGGER.error("Exception in addUser, ioException: " + e);
            throw new RuntimeException(e);
        }
    }

    public void addWord(String word, String translation){
        vocabulary.put(word, translation);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public Map<String, String> getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Map<String, String> vocabulary) {
        this.vocabulary = vocabulary;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }


    static class Node{

        String original;
        String translate;

        public Node(String original, String translate) {
            this.original = original;
            this.translate = translate;
        }

        public String getOriginal() {
            return original;
        }

        public String getTranslate() {
            return translate;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "original='" + original + '\'' +
                    ", translate='" + translate + '\'' +
                    '}';
        }
    }

}
