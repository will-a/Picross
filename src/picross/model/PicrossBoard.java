package picross.model;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PicrossBoard extends GridPane {
    private static double selectChance = 0.5;
    private int numRows;
    private int numCols;
    private ArrayList<ArrayList<PicrossHint>> rowHintVals;
    private ArrayList<ArrayList<PicrossHint>> colHintVals;

    private PicrossTile[][] board;
    private VBox[] rowHints;
    private HBox[] colHints;
    private boolean[][] solution;

    public PicrossBoard(int rows, int cols) {
        numRows = rows;
        numCols = cols;

        board = new PicrossTile[numRows][numCols];
        rowHints = new VBox[numCols];
        colHints = new HBox[numRows];
        solution = new boolean[numRows][numCols];

        add(new HBox(), 0, 0);

        for (int i = 0; i < numCols; i++) {
            VBox v = new VBox();
            add(v, i + 1, 0);
            rowHints[i] = v;
        }
        for (int i = 0; i < numRows; i++) {
            HBox h = new HBox();
            add(h, 0, i + 1);
            colHints[i] = h;
        }

        for (int col = 1; col <= numRows; col++) {
            for (int row = 1; row <= numCols; row++) {
                PicrossTile b = new PicrossTile(row-1, col-1);
                b.setPrefHeight(50);
                b.setPrefWidth(50);
                add(b, row, col);
                board[row-1][col-1] = b;
            }
        }
    }

    public void newGame() {
        rowHintVals = new ArrayList<>();
        for (int i = 0; i < numRows; i++) rowHintVals.add(new ArrayList<>());
        colHintVals = new ArrayList<>();
        for (int i = 0; i < numCols; i++) colHintVals.add(new ArrayList<>());
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

        for (int row = 0; row < numRows; row++) {
            if (rowCounts[row] > 0) rowHintVals.get(row).add(new PicrossHint(rowCounts[row]));
            rowHints[row].getChildren().clear();
            for (PicrossHint ph : rowHintVals.get(row)) {
                rowHints[row].getChildren().add(ph);
            }
        }

        for (int col = 0; col < numCols; col++) {
            colHints[col].getChildren().clear();
            for (PicrossHint ph : colHintVals.get(col)) {
                colHints[col].getChildren().add(ph);
            }
        }

        print();
    }

    public boolean checkSolution() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (board[row][col].isSelected() != solution[col][row]) {
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
