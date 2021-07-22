package picross.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PicrossBoard extends GridPane {
    private static double selectChance = 0.5;
    private int numRows;
    private int numCols;

    private PicrossTile[][] board;
    private HBox[] rowHints;
    private VBox[] colHints;
    private boolean[][] solution;

    public PicrossBoard(int rows, int cols) {
        numRows = rows;
        numCols = cols;

        board = new PicrossTile[numRows][numCols];
        rowHints = new HBox[numCols];
        colHints = new VBox[numRows];
        solution = new boolean[numRows][numCols];

        // left hint row
        for (int i = 0; i < numCols; i++) {
            HBox v = new HBox(2);
            v.setAlignment(Pos.CENTER_RIGHT);
            v.setPadding(new Insets(0, 5, 0, 0));
            add(v, 0, i + 1);
            rowHints[i] = v;
        }
        // top hint row
        for (int i = 0; i < numRows; i++) {
            VBox h = new VBox();
            h.setAlignment(Pos.BOTTOM_CENTER);
            h.setPadding(new Insets(0, 0, 3, 0));
            add(h, i + 1, 0);
            colHints[i] = h;
        }

        for (int row = 1; row <= numRows; row++) {
            for (int col = 1; col <= numCols; col++) {
                PicrossTile b = new PicrossTile(row-1, col-1);
                b.setPrefHeight(50);
                b.setPrefWidth(50);
                add(b, row, col);
                board[row-1][col-1] = b;
            }
        }
    }

    public void newGame() {
        ArrayList<ArrayList<PicrossHint>> rowHintVals = new ArrayList<>();
        for (int i = 0; i < numCols; i++) rowHintVals.add(new ArrayList<>());
        ArrayList<ArrayList<PicrossHint>> colHintVals = new ArrayList<>();
        for (int i = 0; i < numRows; i++) colHintVals.add(new ArrayList<>());
        int[] rowCounts = new int[numCols];
        int colCounter = 0;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                solution[row][col] = Math.random() <= selectChance;
                board[row][col].deselect();

                if (solution[row][col]) {
                    colCounter++;
                    rowCounts[col]++;
                }
                else {
                    if (colCounter > 0) {
                        colHintVals.get(row).add(new PicrossHint(colCounter));
                        colCounter = 0;
                    }
                    if (rowCounts[col] > 0) {
                        rowHintVals.get(col).add(new PicrossHint(rowCounts[col]));
                        rowCounts[col] = 0;
                    }
                }
            }
            if (colCounter > 0) {
                colHintVals.get(row).add(new PicrossHint(colCounter));
                colCounter = 0;
            }
        }

        for (int row = 0; row < numCols; row++) {
            if (rowCounts[row] > 0) rowHintVals.get(row).add(new PicrossHint(rowCounts[row]));
            rowHints[row].getChildren().clear();
            for (PicrossHint ph : rowHintVals.get(row)) {
                rowHints[row].getChildren().add(ph);
            }
        }

        for (int col = 0; col < numRows; col++) {
            colHints[col].getChildren().clear();
            for (PicrossHint ph : colHintVals.get(col)) {
                colHints[col].getChildren().add(ph);
            }
        }
    }

    public boolean checkSolution() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (board[row][col].isSelected() != solution[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {
        for (int row = 0; row < solution.length; row++) {
            for (int col = 0; col < solution[0].length; col++) {
                System.out.print(solution[row][col] ? "X" : ".");
            }
            System.out.println();
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
