package dictionary.mainMenuScene;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.util.AbstractController;
import dictionary.util.User;
import dictionary.chooseLanguageStage.ChooseLanguageController;
import dictionary.logInScene.LogInController;
import dictionary.settings.SettingsController;
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
        setH(600);
        setW(400);
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
    private Button buttonChangePassword;

    @FXML
    private Button buttonChangeEmail;

    @FXML
    private Button buttonChangeUsername;

    @FXML
    void initialize() {

        buttonChangeEmail.setOnAction(actionEvent -> {
            SettingsController.showStage(Operation.CHANGE_EMAIL, "Change email");
        });

        buttonChangePassword.setOnAction(actionEvent -> {
            SettingsController.showStage(Operation.CHANGE_PASSWORD, "Change password");
        });

        buttonChangeUsername.setOnAction(actionEvent -> {
            SettingsController.showStage(Operation.CHANGE_USERNAME, "Change username");
        });

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

    public enum Operation{
        CHANGE_EMAIL,
        CHANGE_PASSWORD,
        CHANGE_USERNAME

    }
}
