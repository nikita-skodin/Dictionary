package dictionary.chooseLanguageStage;

import dictionary.AbstractController;
import dictionary.Main;
import dictionary.training.TrainingController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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


        buttonOk.setOnAction(actionEvent -> {



            closeStage();
            TrainingController.showScene();
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

        Toggle toggle = chooseLanguageController.toggleGroup1.getSelectedToggle();
        if (toggle != null) {
            toggle.setSelected(false);
        }

        chooseLanguageController.textFieldWordsNumber.setText("");

        stage.close();
    }

}
