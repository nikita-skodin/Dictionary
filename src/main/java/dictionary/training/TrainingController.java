package dictionary.training;

import java.net.URL;
import java.util.*;

import dictionary.AbstractController;
import dictionary.User;
import dictionary.checkWord.CheckWordController;
import dictionary.chooseLanguageStage.ChooseLanguageController;
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

    private int counter = 0;

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
            checkWord(translateField.getText(), language);
        });

        buttonNoIdeas.setOnAction(actionEvent -> {
            checkWord("", language);
        });

        buttonExit.setOnAction(actionEvent -> {
            CheckWordController.setRightAnswerText("Your score is: " + counter);
            CheckWordController.setStateText("Game over");
            CheckWordController.showScene(true, 2);
            destruct();
        });



    }

    public static boolean setParameters(String numberOfWordsInString, Language language){

        int maxNumb = User.getCurrentUser().getVocabulary().size();
        if (maxNumb > 0){
            trainingController.maxNumberOfWords = maxNumb;
        }else {
            ExceptionMessageController.setText("Your dictionary is empty");
            ExceptionMessageController.showStage();
            ChooseLanguageController.cleanField();
            return false;
        }

        int numberOfWords;

        try {
            numberOfWords = Integer.parseInt(numberOfWordsInString);
        } catch (NumberFormatException e){
            ExceptionMessageController.setText("Your word count is incorrect");
            ExceptionMessageController.showStage();
            ChooseLanguageController.cleanField();
            return false;
        }

        if (numberOfWords <= maxNumb){
            trainingController.numberOfWords = numberOfWords;
        }else {
            ExceptionMessageController.setText("Not enough words in dictionary");
            ExceptionMessageController.showStage();
            ChooseLanguageController.cleanField();
            return false;
        }

        if (language == null){
            ExceptionMessageController.setText("Language was not chosen");
            ExceptionMessageController.showStage();
            ChooseLanguageController.cleanField();
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
        counter = 0;
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
            int rand = random.nextInt(sourseMap.size());
            String randomKey = list.get(rand);
            map.put(randomKey, sourseMap.get(randomKey));
        }
            return map;
    }

    public static void showScene() {
        trainingController.game();
    }

    private void game(){

            if (iterator.hasNext()) {
                if (language.equals(Language.RUSSIAN)) {
                    currentElement = iterator.next();
                }else {
                    currentElement = randomWordsMap.get(iterator.next());
                }
                currentWord.setText(currentElement);
                currentStage.setScene(trainingController.currentScene);

            } else {

                CheckWordController.setStateText("Game over!");
                CheckWordController.setRightAnswerText("Your score is: " + counter);
                CheckWordController.showScene(true, 2);
                destruct();
            }
    }

    private void checkWord(String string, Language language){

        String rightAnswer;

        if (language.equals(Language.RUSSIAN)) {
            rightAnswer = randomWordsMap.get(currentElement);
        }else {
            rightAnswer = getKeyByValue(randomWordsMap, currentElement);
        }

            if (string.equals(rightAnswer)){
                CheckWordController.setStateText("Right!");
                CheckWordController.setRightAnswerText("");
                CheckWordController.showScene(false, 1);
                counter++;
            } else {
                CheckWordController.setStateText("Wrong!");
                CheckWordController.setRightAnswerText("Right answer is: " + rightAnswer);
                CheckWordController.showScene(false, 2);
            }

            translateField.setText("");

    }


    private static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;  // Возвращаем null, если значение не найдено
    }


    public enum Language{
        ENGLISH,
        RUSSIAN
    }
}
