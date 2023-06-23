package dictionary;

import dictionary.logInStage.LogInController;
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

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        LogInController logInController = LogInController.methodInit(stage);
        SignInController signInController = SignInController.methodInit(stage);
        LogInController.methodShow();


        System.out.println(logInController);
        System.out.println(signInController);
    }

    public static void main(String[] args) {
        launch();
    }

    public void loadImage(Stage stage) throws IOException {
        InputStream iconStream = Main.class.getResourceAsStream("/images/img.png");
        if (iconStream != null){
            Image image = new Image(iconStream);
            stage.getIcons().add(image);
        }
        iconStream.close();
    }
}