package picross.model;

import javafx.scene.control.ToggleButton;

public class PicrossTile extends ToggleButton {
    private int row;
    private int col;

    public PicrossTile() {
        super();
    }

    public PicrossTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public void select() {
        setSelected(true);
    }

    public void deselect() {
        setSelected(false);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
