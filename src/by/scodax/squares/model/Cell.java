package by.scodax.squares.model;

/**
 * User: Administrator
 * Date: 14.05.14
 * Time: 15:35
 */
public class Cell {

    private CellColor color;
    private float zoom = 1;

    public CellColor getColor() {
        return color;
    }

    public void setColor(CellColor color) {
        this.color = color;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void update(float delta) {

    }
}
