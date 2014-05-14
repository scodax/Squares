package by.scodax.squares.controller;

import by.scodax.squares.model.Cell;
import by.scodax.squares.model.CellColor;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * User: Administrator
 * Date: 14.05.14
 * Time: 15:37
 */
public class SquareController {
    private final Cell[][] cells = new Cell[10][10];
    private final Random random = new Random();
    private float x = 44;
    private float y = 300;
    private float width = 392;
    private float height = 392;
    private Rectangle bounds;
    private ZoomTask zoomTask;

    public SquareController() {
        bounds = new Rectangle(x, y, width, height);
        for (int i = 0; i < cells.length; i++) {
            Cell[] row = cells[i];
            for (int j = 0; j < row.length; j++) {
                Cell cell = new Cell();
                cell.setColor(CellColor.values()[random.nextInt(CellColor.values().length)]);
                cells[i][j] = cell;
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void handleTouch(float x, float y) {
        int i = (int) ((width - (this.x - x)) / 39) - 10;
        int j = (int) ((height - (this.y - y)) / 39) - 10;
        setZoomTask(new ZoomTask(cells[i][j]));
        System.out.println("----------------------------------" + i + " : " + j + "---------------------");
    }

    public void update(float delta) {
        if (zoomTask != null) {
            zoomTask.execute(delta);
        }
    }

    public void setZoomTask(ZoomTask zoomTask) {
        if (this.zoomTask != null)
            this.zoomTask.stop();
        this.zoomTask = zoomTask;
    }
}
