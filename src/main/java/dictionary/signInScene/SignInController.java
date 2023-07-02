package dictionary.signInScene;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.logInScene.LogInController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignInController.class);
    private static SignInController signInController;
    public SignInController() {
        if (signInController != null) {
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        signInController = this;
    }

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField mailField;
    @FXML
    private Button buttonLogIn;
    @FXML
    private Button buttonSignIn;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField usernameField;

    @FXML
    void initialize() {

        buttonLogIn.setOnAction(actionEvent -> {
            LogInController.showScene();
            cleanTextFields();
        });

        buttonSignIn.setOnAction(actionEvent -> {
            signInUser();
        });

    }

    public static void showScene() {
        currentStage.setScene(signInController.currentScene);
    }

    private void signInUser() {
        User user;
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String mail = mailField.getText().trim();

        if (!User.usernameValidator(username)) {
            ExceptionMessageController.setText("Incorrect username");
            ExceptionMessageController.showStage();
            return;
        }

        if (!User.passwordValidator(password)) {
            ExceptionMessageController.setText("Incorrect password");
            ExceptionMessageController.showStage();
            return;
        }

        if (!User.mailValidator(mail)) {
            ExceptionMessageController.setText("Incorrect mail address");
            ExceptionMessageController.showStage();
            return;
        }

        user = new User(username, password, mail);

        if (User.isUserExist(user)) {
            ExceptionMessageController.setText("User is exist");
            ExceptionMessageController.showStage();
            return;
        }

        User.addUser(user);
        LogInController.showScene();

        cleanTextFields();
    }

    private void cleanTextFields(){
        passwordField.setText("");
        mailField.setText("");
        usernameField.setText("");
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        // Обработка события нажатия клавиши
        switch (event.getCode()) {
            case ENTER -> buttonSignIn.fire();
        }
    }

}
