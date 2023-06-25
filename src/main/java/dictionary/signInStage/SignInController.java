package dictionary.signInStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.User;
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
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String mail = mailField.getText().trim();

            // TODO: 025 реализовать проверку на корректность всех введенных данных

            User user = new User(username, password, mail);

            System.out.println(user);

            LogInController.methodShow();}
        );

    }

    public static void methodShow() {
        signInController.currentStage.setScene(signInController.currentScene);
        signInController.currentStage.show();
    }
}
