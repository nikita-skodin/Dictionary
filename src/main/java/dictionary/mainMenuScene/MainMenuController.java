package dictionary.mainMenuScene;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.chooseLanguageStage.ChooseLanguageController;
import dictionary.logInScene.LogInController;
import dictionary.vocabularyScene.VocabularyController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);
    private static MainMenuController mainMenuController;
    public MainMenuController() {

        if (mainMenuController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        mainMenuController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonVocabulary;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonExit;

    @FXML
    void initialize() {

        buttonExit.setOnAction(actionEvent -> {

            LogInController.showScene();
            User.addUser(User.getCurrentUser());
            User.setCurrentUser(null);
            VocabularyController.clearTable();

        });

        buttonStart.setOnAction(actionEvent -> {
            ChooseLanguageController.showStage();
        });

        buttonVocabulary.setOnAction(actionEvent -> {
            User.addToObservableList();
            VocabularyController.showScene();

        });

    }

    public static void showScene(){
        currentStage.setScene(mainMenuController.currentScene);
    }
}
