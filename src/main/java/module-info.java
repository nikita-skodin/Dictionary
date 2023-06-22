module maindir.fxtest5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens dictionary to javafx.fxml;
    exports dictionary;
    exports dictionary.logInStage;
    opens dictionary.logInStage to javafx.fxml;
    exports dictionary.mainMenuStage;
    opens dictionary.mainMenuStage to javafx.fxml;
    exports dictionary.signInStage;
    opens dictionary.signInStage to javafx.fxml;
}