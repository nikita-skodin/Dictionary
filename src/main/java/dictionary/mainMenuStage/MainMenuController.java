package dictionary.mainMenuStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.logInStage.LogInController;
import dictionary.vocabulary.VocabularyController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuController extends AbstractController {

    private User currentUser;
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
    private Button vocabularyButton;

    @FXML
    private Button startButton;

    @FXML
    private Button buttonExit;

    @FXML
    void initialize() {

        buttonExit.setOnAction(actionEvent -> {

            currentUser = null;
            LogInController.showScene();

        });

        vocabularyButton.setOnAction(actionEvent -> {

            VocabularyController.showScene();

        });

    }

    public static void showScene(){
        currentStage.setScene(mainMenuController.currentScene);
    }
    public static void setCurrentUser(User currentUser) {
        mainMenuController.currentUser = currentUser;
    }
    public static User getCurrentUser() {
        return mainMenuController.currentUser;
    }
}
