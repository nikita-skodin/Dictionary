package dictionary;

import dictionary.logInStage.LogInController;
import dictionary.mainMenuStage.MainMenuController;
import dictionary.signInStage.SignInController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        setStageOptions(stage);
        loadControllers(stage);

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

    public void loadControllers(Stage stage) throws Exception {
        LogInController logInController = LogInController.methodInit(stage);
        SignInController signInController = SignInController.methodInit(stage);
        MainMenuController mainMenuController = MainMenuController.methodInit(stage);
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