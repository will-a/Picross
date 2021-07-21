package picross.model;

import javafx.scene.text.Text;

public class PicrossHint extends Text {
    private int value;

    public PicrossHint(int value) {
        super("" + value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
