package dictionary.addWordStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.Main;
import dictionary.User;
import dictionary.mainMenuScene.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddWorldController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddWorldController.class);
    private static AddWorldController addWorldController;
    public AddWorldController() {

        if (addWorldController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        addWorldController = this;
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
    private TextField textFieldOriginal;

    @FXML
    private TextField textFieldTranslate;

    @FXML
    void initialize() {

        buttonCancel.setOnAction(actionEvent -> {
            closeStage();
        });

        buttonOk.setOnAction(actionEvent -> {
            String textOriginal = textFieldOriginal.getText().toLowerCase();
            String textTranslate = textFieldTranslate.getText().toLowerCase();

            User.getCurrentUser().addWord(textOriginal, textTranslate);

            closeStage();
        });

    }

    private void closeStage(){
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
        cleanTextFields();
    }

    private void cleanTextFields(){
        textFieldOriginal.setText(null);
        textFieldTranslate.setText(null);
    }

    public static void showScene(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Add word");
        stage.setScene(addWorldController.currentScene);
        stage.show();
    }

}
