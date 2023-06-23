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
    public void start(Stage stage) throws IOException {
        LogInController.setCurrentStage(stage);
        SignInController.setCurrentStage(stage);

        Parent root = FXMLLoader.load(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\logInStage\\logIn.fxml").toURI().toURL());
        Scene scene = new Scene(root, 600, 400);
        loadImage(stage);
        stage.setTitle("Dictionary");

        stage.setScene(scene);
        stage.show();
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