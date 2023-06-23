package dictionary.mainMenuStage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.logInStage.LogInController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController extends AbstractController {

    private static MainMenuController mainMenuController;

    public MainMenuController() throws Exception {

        if (mainMenuController != null){
            throw new Exception();
        }
        mainMenuController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button dictionaryButton;

    @FXML
    private Button startButton;

    @FXML
    void initialize() {

    }

    public static void methodShow(){
        mainMenuController.currentStage.setScene(mainMenuController.currentScene);
        mainMenuController.currentStage.show();
    }

}
