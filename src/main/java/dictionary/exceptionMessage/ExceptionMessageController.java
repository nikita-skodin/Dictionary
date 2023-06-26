package dictionary.exceptionMessage;

import dictionary.AbstractController;
import dictionary.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class ExceptionMessageController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageController.class);

    private static ExceptionMessageController exceptionMessageController;

    public ExceptionMessageController() {

        if (exceptionMessageController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        exceptionMessageController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    public static void methodShow(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Error");
        stage.setScene(exceptionMessageController.currentScene);
        stage.show();
    }
}
