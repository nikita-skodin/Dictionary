package dictionary;

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
import java.net.MalformedURLException;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        setStageOptions(stage);
        loadControllers();

        LogInController.methodShow();

    }

    public static void main(String[] args) {
        launch();
    }

    public void setStageOptions(Stage stage){
        stage.setResizable(false);
        stage.setTitle("Dictionary");
        try {
            loadImage(stage);
        } catch (IOException e) {
            System.out.println("error in loadImage");
        }
    }

    public void loadControllers() throws Exception {
        createControllers(LogInController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\logInStage\\logIn.fxml");
        createControllers(SignInController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\signInStage\\signIn.fxml");
        createControllers(MainMenuController.class, "C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\mainMenuStage\\mainMenu.fxml");
    }

    public <T extends AbstractController> T  createControllers(Class<T> type, String s) throws IOException {

        T controller;
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(new File(s).toURI().toURL());
        fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setCurrentStage(stage);
        Parent root = fxmlLoader.getRoot();

        Scene scene = new Scene(root, 600, 400);

        controller.setCurrentScene(scene);
        return controller;

    }

    public void loadImage(Stage stage) throws IOException {
        try (InputStream iconStream = Main.class.getResourceAsStream("/images/img.png")) {
            if (iconStream != null) {
                Image image = new Image(iconStream);
                stage.getIcons().add(image);
            }else {
                System.out.println("image is absent");
            }
        }
    }
}