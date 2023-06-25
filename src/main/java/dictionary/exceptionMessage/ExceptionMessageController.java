package dictionary.exceptionMessage;

import dictionary.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
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
    private Text testMessage;

    @FXML
    private Text testTitle;

    @FXML
    void initialize() {



    }

    public static void methodShow(){
        exceptionMessageController.currentStage.setScene(exceptionMessageController.currentScene);
        exceptionMessageController.currentStage.show();
    }
}
