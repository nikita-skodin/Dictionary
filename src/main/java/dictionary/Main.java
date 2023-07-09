package dictionary;

import dictionary.addWordStage.AddWorldController;
import dictionary.chooseLanguageStage.ChooseLanguageController;
import dictionary.exceptionMessageStage.ExceptionMessageController;
import dictionary.logInScene.LogInController;
import dictionary.notificationMessage.NotificationMessageController;
import dictionary.restorePassword.RestorePasswordController;
import dictionary.settings.SettingsController;
import dictionary.util.AbstractController;
import dictionary.util.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.*;

public class Main extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        setStageOptions(stage, "Dictionary");
        loadControllers(Paths.get("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary"));

        LogInController.showScene();
    }

    public static void main(String[] args) throws IOException {
        LOGGER.info("main method was starting");
        launch();
        if (User.getCurrentUser() != null) {
            User.addUser(User.getCurrentUser());
        }
        LOGGER.info("main method was done");

    }

    public static void setStageOptions(Stage stage, String message) {
        stage.setResizable(false);
        stage.setTitle(message);
        loadImage(stage);
    }



    private void loadControllers(Path pathStart) {

        try {
            Files.walkFileTree(pathStart, new SimpleFileVisitor<>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    if (file.toString().endsWith(".fxml")) {
                        createController(file.toString());
                    }

                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            LOGGER.error("fxml is not found");
            throw new RuntimeException(e);

        }

    }

    private  <T extends AbstractController> void createController(String s) {

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

         Scene scene = new Scene(root, controller.getH(), controller.getW());

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