package dictionary.exceptionMessageStage;

import dictionary.AbstractController;
import dictionary.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private Button buttonOk;
    @FXML
    private Text text;

    @FXML
    void initialize() {

        buttonOk.setOnAction(actionEvent -> {
            Stage stage = (Stage) buttonOk.getScene().getWindow();
            stage.close();
        });

    }

    public static void setText(String s){
        exceptionMessageController.text.setText(s);
    }

    public static void showStage(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Error");
        stage.setScene(exceptionMessageController.currentScene);
        stage.show();
    }
}
