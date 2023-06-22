package firstApplication;

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
        Parent root = FXMLLoader.load(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\resources\\first.fxml").toURI().toURL());
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Dictionary");
        InputStream iconStream = Main.class.getResourceAsStream("/images/img.png");
        if (iconStream != null){
            Image image = new Image(iconStream);
            stage.getIcons().add(image);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}