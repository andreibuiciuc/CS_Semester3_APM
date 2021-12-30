package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramsView.fxml"));

        Parent mainWindow = loader.load();

        ProgramsViewController programsViewController = loader.getController();

        programsViewController.init();

        primaryStage.setTitle("Toy-Language-Interpreter");
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
