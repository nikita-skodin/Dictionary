package dictionary.logInStage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    private static Stage currentStage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button firstButtonLogIn;

    @FXML
    private Button firstButtonSignIn;

    @FXML
    private TextField firstPasswordField;

    @FXML
    private TextField firstUsernameField;

    @FXML
    void initialize() {

        firstButtonSignIn.setOnAction( actionEvent -> {

            FXMLLoader fxmlLoader = new FXMLLoader();

            try {
                fxmlLoader.setLocation(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\signInStage\\signIn.fxml").toURI().toURL());
                fxmlLoader.load();
                Parent root = fxmlLoader.getRoot();

                currentStage.setScene(new Scene(root, 600, 400));
                currentStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void setCurrentStage(Stage currentStage) {
        LogInController.currentStage = currentStage;
    }
}
