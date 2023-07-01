package dictionary.checkWord;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.mainMenuScene.MainMenuController;
import dictionary.training.TrainingController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckWordController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);
    private static CheckWordController checkWordController;
    public CheckWordController() {

        if (checkWordController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        checkWordController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label rightAnswerText;

    @FXML
    private Label stateText;

    @FXML
    void initialize() {





    }

    public static void setStateText(String s){
        checkWordController.stateText.setText(s);
    }

    public static void setRightAnswerText(String s){
        checkWordController.rightAnswerText.setText(s);
    }

    public static void showScene(boolean isGameEnd, int sec){
        currentStage.setScene(checkWordController.currentScene);

        PauseTransition delay = new PauseTransition(Duration.seconds(sec));
        delay.setOnFinished(event -> {
            if (!isGameEnd) {
                TrainingController.showScene();
            }else {
                MainMenuController.showScene();
            }
        });

        delay.play();

    }

}
