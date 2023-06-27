package dictionary.vocabulary;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.mainMenuStage.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class VocabularyController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VocabularyController.class);
    private static VocabularyController vocabularyController;
    public VocabularyController() {

        if (vocabularyController != null){
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        vocabularyController = this;
    }

    @FXML
    public TableView<User> table;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<User, String> columnTranslates;

    @FXML
    private TableColumn<User, String> columnWords;

    @FXML
    public Button buttonExit;

    @FXML
    void initialize() {

        buttonExit.setOnAction(actionEvent -> {
            MainMenuController.showScene();
        });

//        columnWords.setCellValueFactory(new PropertyValueFactory<>("userName"));
//        columnTranslates.setCellValueFactory(new PropertyValueFactory<>("password"));
//
//        table.getColumns().add(columnWords);
//        table.getColumns().add(columnTranslates);



    }

    public static void showScene() {
        currentStage.setScene(vocabularyController.currentScene);
    }
}
