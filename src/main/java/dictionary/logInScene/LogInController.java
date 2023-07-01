package dictionary.logInScene;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.restorePassword.RestorePasswordController;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.mainMenuScene.MainMenuController;
import dictionary.signInScene.SignInController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInController.class);
    private static LogInController logInController;
    public LogInController() {

        if (logInController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
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
    private Button buttonForgotPassword;

    @FXML
    void initialize() {

        buttonForgotPassword.setOnAction(actionEvent -> {
            RestorePasswordController.showStage();
        });

        buttonSignIn.setOnAction(actionEvent -> {
            SignInController.showScene();
        });

        buttonLogIn.setOnAction(actionEvent -> {
            logInUser();
        });
    }

    public static void showScene(){
        currentStage.setScene(logInController.currentScene);
        currentStage.show();    // вызываем так как это первое окно в приложении и надо показать Stage
    }

    private void logInUser(){
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        User user = User.getUser(username, password);

        if (user != null){
            MainMenuController.showScene();
            User.setCurrentUser(user);


            passwordField.setText("");
            usernameField.setText("");

        } else {
            ExceptionMessageController.setText("Data is incorrect");
            ExceptionMessageController.showStage();
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        // Обработка события нажатия клавиши
        switch (event.getCode()) {
            case ENTER -> buttonLogIn.fire();
        }
    }

}
