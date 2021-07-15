package picross.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class PicrossView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane scenePane = new BorderPane();

        TilePane picrossBoard = new TilePane();
        picrossBoard.setPrefRows(10);
        picrossBoard.setPrefColumns(10);

        Button[][] buttons = new Button[10][10];
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[0].length; col++) {
                Button b = new Button();
                b.setPrefHeight(50);
                b.setPrefWidth(50);
                b.setOnAction(e -> {
                    System.out.println(picrossBoard.getChildren().indexOf(b));
                });
                picrossBoard.getChildren().add(b);
            }
        }
        scenePane.setCenter(picrossBoard);

        Scene scene = new Scene(scenePane);
        stage.setScene(scene);
        stage.setTitle("Picross");
        stage.show();
    }
}
