package org.example.mytunes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyTunes extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyTunes.class.getResource("MyTunes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1535, 890);
        stage.setTitle("MyTunes");
        stage.setScene(scene);
        stage.show();
    }
}
