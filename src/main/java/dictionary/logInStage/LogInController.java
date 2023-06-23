package dictionary.logInStage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dictionary.mainMenuStage.MainMenuController;
import dictionary.signInStage.SignInController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    private static Stage currentStage;
    private static Scene currentScene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button firstButtonLogIn;

    @FXML
    private Button firstButtonSignIn;

    @FXML
    private TextField firstPasswordField;

    @FXML
    private TextField firstUsernameField;

    @FXML
    void initialize() {

        firstButtonSignIn.setOnAction( actionEvent -> {SignInController.methodShow();});

        firstButtonSignIn.setOnAction(actionEvent -> {
            MainMenuController.methodShow();
        });
    }

    public static LogInController methodInit(Stage stage) throws Exception{
        currentStage = stage;
        LogInController logInController;
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\logInStage\\logIn.fxml").toURI().toURL());
        fxmlLoader.load();
        logInController = fxmlLoader.getController();
        Parent root = fxmlLoader.getRoot();

        Scene scene = new Scene(root, 600, 400);

        currentScene = scene;
        return logInController;
    }

    public static void methodShow(){
        currentStage.setScene(currentScene);
        currentStage.show();
    }
}
