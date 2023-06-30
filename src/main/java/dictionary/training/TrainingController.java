package dictionary.training;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainingController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingController.class);
    private static TrainingController trainingController;
    public TrainingController() {
        if (trainingController != null) {
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        trainingController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label CurrentWord;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonNoIdeas;

    @FXML
    private Button buttonOk;

    @FXML
    private TextField translateField;

    @FXML
    void initialize() {



    }

    public static void showScene() {
        currentStage.setScene(trainingController.currentScene);
    }


}
