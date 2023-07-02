package dictionary.settings;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.Main;
import dictionary.User;
import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.mainMenuScene.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingsController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsController.class);
    private static SettingsController settingsController;

    private static MainMenuController.Operation currentOperation = null;
    public SettingsController() {

        if (settingsController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        settingsController = this;
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
    private TextField textFieldNewData;

    @FXML
    private TextField textFieldPassword;

    @FXML
    void initialize() {

        buttonCancel.setOnAction(actionEvent -> {
            closeStage();
        });

        buttonOk.setOnAction(actionEvent -> {
            String textPassword = textFieldPassword.getText().toLowerCase().trim();
            String textNewData = textFieldNewData.getText().toLowerCase().trim();

            if (User.getCurrentUser().getPassword().equals(textPassword)){
                changeData(textNewData);
            }else {
                ExceptionMessageController.setText("Password is incorrect");
                ExceptionMessageController.showStage();
            }
        });

    }

    private void closeStage(){
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }

    private void cleanTextFields(){
        textFieldNewData.setText("");
        textFieldPassword.setText("");
    }

    public static void showStage(MainMenuController.Operation operation, String s){
        currentOperation = operation;
        Stage stage = new Stage();
        Main.setStageOptions(stage, s);
        settingsController.cleanTextFields();
        switch (currentOperation){
            case CHANGE_EMAIL -> settingsController.textFieldNewData.setPromptText("New Email");
            case CHANGE_PASSWORD -> settingsController.textFieldNewData.setPromptText("New password");
            case CHANGE_USERNAME -> settingsController.textFieldNewData.setPromptText("New username");
        }
        stage.setScene(settingsController.currentScene);
        stage.show();
    }

    private void changeData(String s){
        switch (currentOperation){
            case CHANGE_EMAIL -> {
                if (User.mailValidator(s)){
                    User.changeMail(s);
                    closeStage();
                }else {
                    ExceptionMessageController.setText("Incorrect mail address");
                    ExceptionMessageController.showStage();
                }
            }
            case CHANGE_PASSWORD -> {
                if (User.passwordValidator(s)){
                    User.changeMail(s);
                    closeStage();
                }else {
                    ExceptionMessageController.setText("Incorrect password");
                    ExceptionMessageController.showStage();
                }
            }
            case CHANGE_USERNAME -> {
                if (User.usernameValidator(s)){
                    User.changeMail(s);
                    closeStage();
                }else {
                    ExceptionMessageController.setText("Incorrect username");
                    ExceptionMessageController.showStage();
                }
            }
        }
    }

    public void onKeyPressed(KeyEvent event) {
        // Обработка события нажатия клавиши
        switch (event.getCode()) {
            case ESCAPE -> buttonCancel.fire();
            case ENTER -> buttonOk.fire();
        }
    }

}
