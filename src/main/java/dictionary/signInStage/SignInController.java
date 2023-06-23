package dictionary.signInStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.logInStage.LogInController;
import dictionary.mainMenuStage.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignInController extends AbstractController {

    private static SignInController signInController;

    public SignInController() throws Exception {

        if (signInController != null) {
            throw new Exception();
        }
        signInController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldMail;

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

        buttonSignIn.setOnAction(actionEvent -> {MainMenuController.methodShow();});

    }

    public static void methodShow() {
        signInController.currentStage.setScene(signInController.currentScene);
        signInController.currentStage.show();
    }
}
