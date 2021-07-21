package picross.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import picross.model.PicrossBoard;

import java.util.List;

public class PicrossView extends Application {
    private PicrossBoard board;

    @Override
    public void init() throws Exception {
        List<String> args = getParameters().getRaw();
        board = new PicrossBoard(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        board.newGame();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane scenePane = new BorderPane();
        // picrossGrid.setPrefRows(board.getNumRows());
        // picrossGrid.setPrefColumns(board.getNumCols());
        /*for (int i = 1; i <= board.getNumRows(); i++) {
            HBox rowNums = new HBox(5);


            picrossGrid.add();
        }*/

        scenePane.setCenter(board);

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
