package picross.model;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PicrossBoard extends GridPane {
    private static double selectChance = 0.3;
    private int numRows;
    private int numCols;
    private ArrayList<ArrayList<PicrossHint>> rowHints;
    private ArrayList<ArrayList<PicrossHint>> colHints;

    private PicrossTile[][] board;
    private boolean[][] solution;

    public PicrossBoard(int rows, int cols) {
        numRows = rows;
        numCols = cols;

        board = new PicrossTile[numRows][numCols];
        solution = new boolean[numRows][numCols];

        for (int col = 1; col <= numRows; col++) {
            for (int row = 1; row <= numCols; row++) {
                PicrossTile b = new PicrossTile(row-1, col-1);
                b.setPrefHeight(50);
                b.setPrefWidth(50);
                /*b.setOnAction(e -> {
                    System.out.println(b.getRow() + " " + b.getCol() + " " + board[b.getRow()][b.getCol()].isSelected());
                });*/
                add(b, row, col);
                board[row-1][col-1] = b;
            }
        }
    }

    public void newGame() {
        rowHints = new ArrayList<>();
        for (int i = 0; i < numRows; i++) rowHints.add(new ArrayList<>());
        colHints = new ArrayList<>();
        for (int i = 0; i < numCols; i++) colHints.add(new ArrayList<>());
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
                        colHints.get(row).add(new PicrossHint(colCounter));
                        colCounter = 0;
                    }
                    if (rowCounts[col] > 0) {
                        rowHints.get(col).add(new PicrossHint(rowCounts[col]));
                        rowCounts[col] = 0;
                    }
                }
            }
            if (colCounter > 0) {
                colHints.get(row).add(new PicrossHint(colCounter));
                colCounter = 0;
            }
        }

        for (int row = 0; row < numRows; row++) {
            if (rowCounts[row] > 0) rowHints.get(row).add(new PicrossHint(rowCounts[row]));
            System.out.println(rowHints.get(row));
            VBox v = new VBox();
            for (PicrossHint ph : rowHints.get(row)) {
                v.getChildren().add(ph);
            }
            add(v, row + 1, 0);
        }

        for (int col = 0; col < numCols; col++) {
            HBox h = new HBox();
            for (PicrossHint ph : colHints.get(col)) {
                h.getChildren().add(ph);
            }
            add(h, 0, col + 1);
        }

        print();
    }

    /*public void flipTile(int row, int col) {
        if (board[row][col].isSelected()) {
            board[row][col].deselect();
        } else {
            board[row][col].select();
        }
        System.out.println(row + " " + col + " " + board[row][col]);
    }*/

    public boolean checkSolution() {
        return false;
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
