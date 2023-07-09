package dictionary.util;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {
    protected int w;
    protected int h;
    protected static Stage currentStage;
    protected Scene currentScene;

    protected static AbstractController init(){

        return null;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    protected void setW(int w) {
        this.w = w;
    }

    protected void setH(int h) {
        this.h = h;
    }
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
