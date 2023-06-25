package dictionary.mainMenuStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.Main;
import dictionary.User;
import dictionary.signInStage.SignInController;
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
