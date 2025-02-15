package boblet.ui;

import java.io.IOException;

import boblet.Boblet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Boblet duke = new Boblet("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Boblet - Your Personal Task Assistant"); // Added app name
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBoblet(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
