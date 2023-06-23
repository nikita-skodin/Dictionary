package dictionary;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {
    protected Stage currentStage;
    protected Scene currentScene;
    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }
}
