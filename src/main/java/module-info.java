module maindir.fxtest5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens firstApplication to javafx.fxml;
    exports firstApplication;
}