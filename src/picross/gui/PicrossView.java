package picross.gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import picross.model.PicrossBoard;

import java.text.DecimalFormat;
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

        Text picrossText = new Text("Picross");
        picrossText.setFont(new Font("Calibri", 40));

        Text result = new Text();
        result.setFont(new Font("Calibri", 20));
        result.setVisible(false);

        Button solveButton = new Button("Solve");
        solveButton.setFont(new Font("Calibri", 30));
        solveButton.setOnAction(e -> {
            if (board.checkSolution()) {
                result.setText("Correct!");
            } else {
                result.setText("Incorrect");
            }
            result.setVisible(true);
            solveButton.setDisable(true);
        });

        Button newGameButton = new Button("New Game");
        newGameButton.setFont(new Font("Calibri", 20));
        newGameButton.setOnAction(e -> {
            board.newGame();
            solveButton.setDisable(false);
            result.setVisible(false);
        });

        Text percentDone = new Text();
        percentDone.setFont(new Font("Calibri", 20));
        DecimalFormat df = new DecimalFormat("#.#");
        percentDone.textProperty().bind(Bindings.createStringBinding(() ->
                df.format(board.getPercentDone().get()) + "%", board.getPercentDone()));

        VBox sidebar = new VBox(20, picrossText, solveButton, newGameButton, result, percentDone);
        sidebar.setPadding(new Insets(50));
        sidebar.setAlignment(Pos.BASELINE_CENTER);

        scenePane.setCenter(board);
        scenePane.setLeft(sidebar);

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
