package dictionary.signInStage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dictionary.logInStage.LogInController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

    private static Stage currentStage;
    private static Scene currentScene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField SecondFieldMail;

    @FXML
    private Button secondButtonLogIn;

    @FXML
    private Button secondButtonSignIn;

    @FXML
    private TextField secondPasswordField;

    @FXML
    private TextField secondUsernameField;

    @FXML
    void initialize() {

        secondButtonSignIn.setOnAction( actionEvent -> {

            LogInController.methodShow();
        });

    }

    public static void setCurrentStage(Stage currentStage) {
        SignInController.currentStage = currentStage;
    }

    public static SignInController methodInit(Stage stage) throws Exception{
        currentStage = stage;
        SignInController signInController;
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\signInStage\\signIn.fxml").toURI().toURL());
        fxmlLoader.load();
        signInController = fxmlLoader.getController();
        Parent root = fxmlLoader.getRoot();

        Scene scene = new Scene(root, 600, 400);

        currentScene = scene;
        return signInController ;
    }

    public static void methodShow(){
        currentStage.setScene(currentScene);
        currentStage.show();
    }
}
