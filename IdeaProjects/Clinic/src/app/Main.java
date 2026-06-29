package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.SceneManager;
import view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        SceneManager.setStage(stage);

        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getView(), 900, 500);

        stage.setTitle("Clinic Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
