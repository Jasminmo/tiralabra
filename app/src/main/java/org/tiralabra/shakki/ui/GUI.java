package org.tiralabra.shakki.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chess");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
