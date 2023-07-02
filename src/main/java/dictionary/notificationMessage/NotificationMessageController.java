package dictionary.notificationMessage;

import dictionary.AbstractController;
import dictionary.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationMessageController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMessageController.class);
    private static NotificationMessageController notificationMessageController;
    public NotificationMessageController() {

        if (notificationMessageController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        notificationMessageController = this;
    }

    @FXML
    private Button buttonOk;


    @FXML
    void initialize() {
        buttonOk.setOnAction(actionEvent -> {
            closeStage();
        });

    }

    private void closeStage(){
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }

    public void onKeyPressed(KeyEvent event) {
        // Обработка события нажатия клавиши
        switch (event.getCode()) {
            case ENTER -> buttonOk.fire();
        }
    }

    public static void showStage(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Password restore");
        stage.setScene(notificationMessageController.currentScene);
        stage.show();
    }
}
