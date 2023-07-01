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
    private Button buttonOk;

    @FXML
    private Label rightAnswerText;

    @FXML
    private Label stateText;

    @FXML
    void initialize() {



    }

    public static void showScene(){
        currentStage.setScene(checkWordController.currentScene);

        PauseTransition delay = new PauseTransition(Duration.seconds(5));

        // Задаем действие, которое будет выполнено после задержки
        delay.setOnFinished(event -> {
            // Закрываем primaryStage после истечения времени
            TrainingController.showScene();
        });

    }

}
