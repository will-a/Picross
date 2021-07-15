package picross.model;

public class PicrossBoard {
    private int numRows;
    private int numCols;

    public PicrossBoard() {
        numRows = 10;
        numCols = 10;
    }

    public PicrossBoard(int rows, int cols) {
        numRows = rows;
        numCols = cols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
