package dictionary.vocabularyScene;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.addWordStage.AddWorldController;
import dictionary.mainMenuScene.MainMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public TableView<User> myTable;

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
    private Button buttonAddWord;

    public static ObservableList<User> users = FXCollections.observableArrayList(

            new User("nikita", "111", "maila@")

    );

    @FXML
    public void initialize() {

        buttonExit.setOnAction(actionEvent -> {
            MainMenuController.showScene();
        });

        buttonAddWord.setOnAction(actionEvent -> {
            AddWorldController.showScene();
        });



        myTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnWords.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnTranslates.setCellValueFactory(new PropertyValueFactory<>("password"));

        myTable.setItems(users);


    }

    public static void showScene() {
        currentStage.setScene(vocabularyController.currentScene);
    }
}
