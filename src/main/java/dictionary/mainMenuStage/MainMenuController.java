package dictionary.mainMenuStage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dictionary.logInStage.LogInController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    private static Stage currentStage;
    private static Scene currentScene;

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

    public static MainMenuController methodInit(Stage stage) throws Exception{
        currentStage = stage;
        MainMenuController mainMenuController;
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\mainMenuStage\\mainMenu.fxml").toURI().toURL());
        fxmlLoader.load();
        mainMenuController = fxmlLoader.getController();
        Parent root = fxmlLoader.getRoot();

        Scene scene = new Scene(root, 600, 400);

        currentScene = scene;
        return mainMenuController;
    }

    public static void methodShow(){
        currentStage.setScene(currentScene);
        currentStage.show();
    }

}
