module maindir.fxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j.slf4j;
    requires slf4j.api;
    requires com.fasterxml.jackson.databind;
    requires java.mail;


    opens dictionary to javafx.fxml;
    exports dictionary;
    exports dictionary.logInScene;
    opens dictionary.logInScene to javafx.fxml;
    exports dictionary.mainMenuScene;
    opens dictionary.mainMenuScene to javafx.fxml;
    exports dictionary.signInScene;
    opens dictionary.signInScene to javafx.fxml;
    exports dictionary.exceptionMessageStage;
    opens dictionary.exceptionMessageStage to javafx.fxml;
    exports dictionary.vocabularyScene;
    opens dictionary.vocabularyScene to javafx.fxml;
    exports dictionary.addWordStage;
    opens dictionary.addWordStage to javafx.fxml;
    exports dictionary.chooseLanguageStage;
    opens dictionary.chooseLanguageStage to javafx.fxml;
    exports dictionary.training;
    opens dictionary.training to javafx.fxml;
    exports dictionary.checkWord;
    opens dictionary.checkWord to javafx.fxml;
    exports dictionary.restorePassword;
    opens dictionary.restorePassword to javafx.fxml;
    exports dictionary.notificationMessage;
    opens dictionary.notificationMessage to javafx.fxml;
    exports dictionary.settings;
    opens dictionary.settings to javafx.fxml;
    opens dictionary.util to javafx.fxml;
    exports dictionary.util;

}