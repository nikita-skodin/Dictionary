package dictionary.restorePassword;

import dictionary.util.AbstractController;
import dictionary.util.Mailer;
import dictionary.Main;
import dictionary.util.User;
import dictionary.notificationMessage.NotificationMessageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RestorePasswordController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestorePasswordController.class);
    private static RestorePasswordController restorePasswordController;
    public RestorePasswordController() {

        if (restorePasswordController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        setH(423);
        setW(228);
        restorePasswordController = this;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonOk;

    @FXML
    private TextField textFieldUsername;


    @FXML
    void initialize() {

        buttonCancel.setOnAction(actionEvent -> {
            closeStage();
        });

        buttonOk.setOnAction(actionEvent -> {
            if (Mailer.send(User.getUserPasswordByUsername(textFieldUsername.getText().trim()))) {
                closeStage();
                NotificationMessageController.showStage();
            }
        });

    }

    private void closeStage(){
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
        cleanTextFields();
    }

    private void cleanTextFields(){
        textFieldUsername.setText("");
    }

    public static void showStage(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Password restore");
        stage.setScene(restorePasswordController.currentScene);
        stage.show();
    }

    public void onKeyPressed(KeyEvent event) {
        // Обработка события нажатия клавиши
        switch (event.getCode()) {
            case ESCAPE -> buttonCancel.fire();
            case ENTER -> buttonOk.fire();
        }
    }

}