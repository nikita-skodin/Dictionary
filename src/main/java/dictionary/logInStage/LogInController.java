package dictionary.logInStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.mainMenuStage.MainMenuController;
import dictionary.signInStage.SignInController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController extends AbstractController {

    private static LogInController logInController;

    public LogInController() throws Exception {

        if (logInController != null){
            throw new Exception();
        }
        logInController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        buttonSignIn.setOnAction(actionEvent -> {SignInController.methodShow();});

        buttonLogIn.setOnAction(actionEvent -> {MainMenuController.methodShow();});
    }

    public static void methodShow(){
        logInController.currentStage.setScene(logInController.currentScene);
        logInController.currentStage.show();
    }
}