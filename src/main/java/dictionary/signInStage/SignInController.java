package dictionary.signInStage;

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

public class SignInController {

    private static Stage currentStage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField SecondFieldMail;

    @FXML
    private Button secondButtonLogIn;

    @FXML
    private Button secondButtonSignIn;

    @FXML
    private TextField secondPasswordField;

    @FXML
    private TextField secondUsernameField;

    @FXML
    void initialize() {

        secondButtonSignIn.setOnAction( actionEvent -> {

            FXMLLoader fxmlLoader = new FXMLLoader();

            try {
                fxmlLoader.setLocation(new File("C:\\Users\\dmitr\\Desktop\\mainjavaprojects\\FXtest5\\src\\main\\java\\dictionary\\logInStage\\logIn.fxml").toURI().toURL());
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
        SignInController.currentStage = currentStage;
    }
}
