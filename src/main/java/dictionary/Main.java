package dictionary;

import dictionary.exceptionMessage.ExceptionMessageController;
import dictionary.logInStage.LogInController;
import dictionary.mainMenuStage.MainMenuController;
import dictionary.signInStage.SignInController;
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

        setStageOptions(stage);
        loadControllers();

        LogInController.methodShow();



    }

    public static void main(String[] args) {
        LOGGER.info("main method was starting");
        launch();
        LOGGER.info("main method was done");
    }

    public void setStageOptions(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Dictionary");
        loadImage(stage);
    }

    public void loadControllers() {
        createControllers(LogInController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\logInStage\\logIn.fxml");
        createControllers(SignInController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\signInStage\\signIn.fxml");
        createControllers(MainMenuController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\mainMenuStage\\mainMenu.fxml");
//        createControllers(ExceptionMessageController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\exceptionMessage\\exceptionMessage.fxml");
    }

    public <T extends AbstractController> T createControllers(Class<T> type, String s) {

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

        Scene scene = new Scene(root, 600, 400);

        controller.setCurrentScene(scene);
        return controller;

    }

    public void loadImage(Stage stage) {
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