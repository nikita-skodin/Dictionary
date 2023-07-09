package dictionary.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.*;
import dictionary.vocabularyScene.VocabularyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonAutoDetect
public class User {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    private static User currentUser = null;
    private String userName;
    private String password;
    private String userMailAddress;
    private Map<String, String> vocabulary;

    private static final String regex = "@@##@@";

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
        Path path = Paths.get(String.format("src/main/resources/usersData/%s", userName+regex+password+".json"));
        try {
            return objectMapper.readValue(Files.newInputStream(path), User.class);
        } catch (IOException e) {
            LOGGER.info("Attempt to get user was fail");
            return null;
        }
    }

    public static void addUser(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = Paths.get(String.format("src/main/resources/usersData/%s", user.userName+regex+user.password+".json"));
        try {
            objectMapper.writeValue(Files.newOutputStream(path), user);
        } catch (IOException e) {
            LOGGER.error("Exception in addUser, ioException: " + e);
            throw new RuntimeException(e);
        }
    }

    public boolean addWord(String word, String translation){
        if (vocabulary.containsKey(word)){
            return false;
        }
        vocabulary.put(word, translation);
        return true;
    }

    public static void addToObservableList(){
        for (Map.Entry<String, String> el : currentUser.vocabulary.entrySet()) {
            VocabularyController.addToTable(new Node(el.getKey(), el.getValue()));
        }
    }

    public static void removeNode(Node node){
        currentUser.vocabulary.remove(node.original, node.translate);
    }


    public static class Node{

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

    public static void changeMail(String string){
        currentUser.userMailAddress = string;
    }

    public static void changePassword(String string){
        currentUser.password = string;
    }

    public static void username(String string){
        currentUser.userName = string;
    }

    public static boolean usernameValidator(String s) {
        return !s.equals("");
    }

    public static boolean passwordValidator(String s) {
        return s.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}");
    }

    public static boolean mailValidator(String s) {
        return s.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }
    public static boolean isUserExist(User user) {
        return Files.exists(Paths.get(String.format("src/main/resources/usersData/%s", user.getUserName() + user.getPassword() + ".json")));
    }

    public static String getUserPasswordByUsername(String userName){

        if (userName.equals("")){
            return null;
        }

        Path directory = Paths.get("src/main/resources/usersData");
        List<String> list = new ArrayList<>();
        try {
            Files.list(directory)
                    .filter(Files::isRegularFile)
                    .forEach(file -> list.add(file.getFileName().toString().replaceAll(".json", "")));

            for (String el : list) {
                if (el.contains(userName)){
                    return el.split(regex)[1];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
