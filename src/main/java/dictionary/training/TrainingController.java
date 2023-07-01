package dictionary.training;

import java.net.URL;
import java.util.*;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.mainMenuScene.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainingController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingController.class);
    private static TrainingController trainingController;
    public TrainingController() {
        if (trainingController != null) {
            LOGGER.error("Attempt to create " + this.getClass().getSimpleName() + "a second time");
            throw new RuntimeException();
        }
        trainingController = this;
    }

    private int numberOfWords;
    private int maxNumberOfWords;
    private Language language;
    private Map<String, String> randomWordsMap;
    private ListIterator<String> iterator;

    private ArrayList<String> keyList;
    private String currentElement;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label currentWord;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonNoIdeas;

    @FXML
    private Button buttonOk;

    @FXML
    private TextField translateField;

    @FXML
    void initialize() {

        buttonOk.setOnAction(actionEvent -> {
            checkWord(translateField.getText());

        });



    }

    public static boolean setParameters(String numberOfWordsInString, Language language){

        int maxNumb = User.getCurrentUser().getVocabulary().size();
        if (maxNumb > 0){
            trainingController.maxNumberOfWords = maxNumb;
        }else {
            ExceptionMessageController.setText("Your dictionary is empty");
            ExceptionMessageController.showStage();
            return false;
        }

        int numberOfWords;

        try {
            numberOfWords = Integer.parseInt(numberOfWordsInString);
        } catch (NumberFormatException e){
            ExceptionMessageController.setText("Your word count is incorrect");
            ExceptionMessageController.showStage();
            return false;
        }

        if (numberOfWords <= maxNumb){
            trainingController.numberOfWords = numberOfWords;
        }else {
            ExceptionMessageController.setText("Not enough words in dictionary");
            ExceptionMessageController.showStage();
            return false;
        }

        if (language == null){
            ExceptionMessageController.setText("Language was not chosen");
            ExceptionMessageController.showStage();
            return false;
        }
        trainingController.language = language;
        trainingController.randomWordsMap = trainingController.createRandomMap(numberOfWords, User.getCurrentUser());
        trainingController.keyList = new ArrayList<>(trainingController.randomWordsMap.keySet());
        trainingController.iterator = trainingController.keyList.listIterator();
        return true;
    }

    private void destruct(){
        numberOfWords = 0;
        maxNumberOfWords = 0;
        language = null;
        randomWordsMap = null;
        keyList = null;
        currentElement = null;
    }

    public Map<String, String> createRandomMap(int numberOfWords, User user){
        Map<String, String> sourseMap = user.getVocabulary();

        if (numberOfWords == maxNumberOfWords){
            return new HashMap<>(sourseMap);
        }

        Map<String, String> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>(sourseMap.keySet());
        Random random = new Random();
        while (map.size() < numberOfWords){
            String randomKey = list.get(random.nextInt(numberOfWords) + 1);
            map.put(randomKey, sourseMap.get(randomKey));
        }
            return map;
    }

    public static void showScene() {
        trainingController.game();
        currentStage.setScene(trainingController.currentScene);
    }

    private void game(){
        if (iterator.hasNext()){
            currentElement = iterator.next();
            currentWord.setText(currentElement);
        }else {
            MainMenuController.showScene();
            destruct();
        }


    }

    private void checkWord(String string){
        if (string.equals(randomWordsMap.get(currentElement))){
            ExceptionMessageController.setText("Right!");
        } else {
            ExceptionMessageController.setText("Wrong!");
        }

        ExceptionMessageController.showStage();

        translateField.setText("");
    }


    public enum Language{
        ENGLISH,
        RUSSIAN
    }
}
