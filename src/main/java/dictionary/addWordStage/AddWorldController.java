package dictionary.addWordStage;

import java.net.URL;
import java.util.ResourceBundle;

import dictionary.AbstractController;
import dictionary.Main;
import dictionary.User;
import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.vocabularyScene.VocabularyController;
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
            String textOriginal = textFieldOriginal.getText().toLowerCase().trim();
            String textTranslate = textFieldTranslate.getText().toLowerCase().trim();

            if (textOriginal.equals("") || textTranslate.equals("")){
                ExceptionMessageController.setText("This word is incorrect");
                ExceptionMessageController.showStage();
            } else if (User.getCurrentUser().getVocabulary().containsValue(textTranslate)) {
                ExceptionMessageController.setText("This translation is already exist");
                ExceptionMessageController.showStage();
            } else if (User.getCurrentUser().addWord(textOriginal, textTranslate)) {
                VocabularyController.addToTable(new User.Node(textOriginal, textTranslate));
            }else {
                ExceptionMessageController.setText("This word is already exist");
                ExceptionMessageController.showStage();
            }
            closeStage();
        });

    }

    private void closeStage(){
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
        cleanTextFields();
    }

    private void cleanTextFields(){
        textFieldOriginal.setText("");
        textFieldTranslate.setText("");
    }

    public static void showStage(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Add word");
        stage.setScene(addWorldController.currentScene);
        stage.show();
    }

}
