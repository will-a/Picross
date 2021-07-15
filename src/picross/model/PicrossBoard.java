package picross.model;

import java.util.Arrays;

public class PicrossBoard {
    private int numRows;
    private int numCols;
    private enum TileState {
        SELECTED, NOT_SELECTED
    }

    private TileState[] board;

    public PicrossBoard(int rows, int cols) {
        numRows = rows;
        numCols = cols;

        board = new TileState[numRows * numCols];
        Arrays.fill(board, TileState.NOT_SELECTED);
    }

    public void flipTile(int tileNum) {
        if (board[tileNum] == TileState.NOT_SELECTED) {
            board[tileNum] = TileState.SELECTED;
        } else {
            board[tileNum] = TileState.NOT_SELECTED;
        }
        System.out.println(tileNum + " " + board[tileNum]);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
