package dictionary;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {
    protected static Stage currentStage;
    protected Scene currentScene;
    public void setCurrentStage(Stage currentStage) {
        AbstractController.currentStage = currentStage;
    }
    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }
    public Stage getCurrentStage() {
        return currentStage;
    }
    public Scene getCurrentScene() {
        return currentScene;
    }
}
