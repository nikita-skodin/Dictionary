package dictionary.signInStage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.exceptionMessage.ExceptionMessageController;
import dictionary.logInStage.LogInController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
            LogInController.methodShow();
        });

        buttonSignIn.setOnAction(actionEvent -> {
            User user;
            do {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                String mail = mailField.getText().trim();

                // TODO: 025 реализовать проверку на корректность всех введенных данных

                if (!usernameValidator(username)) {
                    System.out.println("Uncorrected username");
                    ExceptionMessageController.methodShow();
                    break;
                }

                if (!passwordValidator(password)) {
                    System.out.println("Uncorrected password");
                    break;
                }

                if (!mailValidator(mail)) {
                    System.out.println("Uncorrected mail address");
                    break;
                }

                user = new User(username, password, mail);

                if (isUserExist(user)) {
                    System.out.println("User is exist");
                    break;
                }

                try {
                    User.addUser(user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                LogInController.methodShow();
                usernameField.setText(null);
                passwordField.setText(null);
                mailField.setText(null);
                break;
            } while (true);
        });

    }

    public static void methodShow() {
        signInController.currentStage.setScene(signInController.currentScene);
        signInController.currentStage.show();
    }

    private boolean usernameValidator(String s){
        return !s.equals("");
    }

    private boolean passwordValidator(String s){
        return s.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}");
    }

    private boolean mailValidator(String s){
        return s.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    private boolean isUserExist(User user){
        return Files.exists(Paths.get(String.format("src/main/resources/usersData/%s", user.getUserName()+user.getPassword()+".json")));
    }
}
