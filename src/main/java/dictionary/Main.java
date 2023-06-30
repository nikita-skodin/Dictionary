package dictionary;

import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.logInScene.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.*;

public class Main extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage = stage;

        setStageOptions(stage, "Dictionary");
        loadControllers();

        LogInController.showScene();
    }

    public static void main(String[] args) {
        LOGGER.info("main method was starting");
        launch();
        LOGGER.info("main method was done");
    }

    public static void setStageOptions(Stage stage, String message) {
        stage.setResizable(false);
        stage.setTitle(message);
        loadImage(stage);
    }
    public void loadControllers() {
        createController("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\logInScene\\logIn.fxml");
        createController("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\signInScene\\signIn.fxml");
        createController("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\mainMenuScene\\mainMenu.fxml");
        createController("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\exceptionMessageStage\\exceptionMessage.fxml");
        createController("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\vocabularyScene\\vocabulary.fxml");
    }
    public <T extends AbstractController> void createController(String s) {

        T controller;
        FXMLLoader fxmlLoader = new FXMLLoader();

        try {
            fxmlLoader.setLocation(new File(s).toURI().toURL());
            fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.error("URL or path to FXML is not found, application was close, message is: " + e.getMessage());
            throw new RuntimeException(e);
        }

        controller = fxmlLoader.getController();
        controller.setCurrentStage(stage);
        Parent root = fxmlLoader.getRoot();

        Scene scene;
        if (controller instanceof ExceptionMessageController) {
            scene = new Scene(root, 274, 183);
        } else {
            scene = new Scene(root, 600, 400);
        }

        controller.setCurrentScene(scene);
    }
    private static void loadImage(Stage stage) {
        try (InputStream iconStream = Main.class.getResourceAsStream("/images/img.png")) {
            if (iconStream != null) {
                Image image = new Image(iconStream);
                stage.getIcons().add(image);
            } else {
                LOGGER.info("stream in loadImage is null, image did not upload");
            }
        } catch (IOException e) {
            LOGGER.error("error in loadImage, message is: " + e.getMessage());
        }
    }
}