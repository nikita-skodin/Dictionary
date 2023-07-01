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
    private TableView<User.Node> table;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<User.Node, String> columnTranslates;

    @FXML
    private TableColumn<User.Node, String> columnWords;

    @FXML
    public Button buttonExit;

    @FXML
    private Button buttonAddWord;

    @FXML
    private Button buttonRemove;

    private static final ObservableList<User.Node> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        buttonRemove.setOnAction(actionEvent -> {
            TableSelectionModel<User.Node> selectionModel = table.getSelectionModel();
            if (!selectionModel.isEmpty()) {
                User.Node selectedNode = selectionModel.getSelectedItem();
                User.removeNode(selectedNode);
                table.getItems().remove(selectedNode);
            }

        });

        buttonExit.setOnAction(actionEvent -> {
            MainMenuController.showScene();
            VocabularyController.clearTable();
        });

        buttonAddWord.setOnAction(actionEvent -> {
            AddWorldController.showStage();
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnWords.setCellValueFactory(new PropertyValueFactory<>("original"));
        columnTranslates.setCellValueFactory(new PropertyValueFactory<>("translate"));

        table.setItems(list);

    }

    public static void showScene() {
        currentStage.setScene(vocabularyController.currentScene);
    }

    public static void addToTable(User.Node node){
        list.add(node);
    }

    public static void clearTable(){
        list.clear();
    }
}
