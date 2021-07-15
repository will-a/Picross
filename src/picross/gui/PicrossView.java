package picross.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import picross.model.PicrossBoard;

import java.util.List;

public class PicrossView extends Application {
    private PicrossBoard board;

    @Override
    public void init() throws Exception {
        List<String> args = getParameters().getRaw();
        board = new PicrossBoard(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane scenePane = new BorderPane();

        TilePane picrossGrid = new TilePane();
        picrossGrid.setPrefRows(board.getNumRows());
        picrossGrid.setPrefColumns(board.getNumCols());

        for (int row = 0; row < board.getNumRows(); row++) {
            for (int col = 0; col < board.getNumCols(); col++) {
                Button b = new Button();
                b.setPrefHeight(50);
                b.setPrefWidth(50);
                b.setOnAction(e -> {
                    System.out.println(picrossGrid.getChildren().indexOf(b));
                });
                picrossGrid.getChildren().add(b);
            }
        }
        scenePane.setCenter(picrossGrid);

        Scene scene = new Scene(scenePane);
        stage.setScene(scene);
        stage.setTitle("Picross");
        stage.show();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Program requires <rowNum> and <colNum> arguments");
        } else {
            Application.launch(args);
        }
    }
}
