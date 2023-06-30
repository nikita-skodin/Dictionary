package dictionary.chooseLanguageStage;

import dictionary.AbstractController;
import dictionary.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChooseLanguageController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseLanguageController.class);
    private static ChooseLanguageController chooseLanguageController;
    public ChooseLanguageController() {

        if (chooseLanguageController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        chooseLanguageController = this;
    }


    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonOk;

    @FXML
    private RadioButton radioEnToRus;

    @FXML
    private RadioButton radioRusToEn;

    @FXML
    private TextField textFieldWordsNumber;

    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    void initialize() {

        buttonCancel.setOnAction(actionEvent -> {
            closeStage();
        });



    }

    public static void showStage(){
        Stage stage = new Stage();
        Main.setStageOptions(stage, "Settings");
        stage.setScene(chooseLanguageController.currentScene);
        stage.show();
    }

    private static void closeStage(){
        Stage stage = (Stage) chooseLanguageController.buttonCancel.getScene().getWindow();
        chooseLanguageController.toggleGroup1.getSelectedToggle().setSelected(false);
        chooseLanguageController.textFieldWordsNumber.setText("");
        stage.close();
    }

}
