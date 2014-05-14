package by.scodax.squares.controller;

import by.scodax.squares.model.Cell;

/**
 * User: Administrator
 * Date: 14.05.14
 * Time: 17:18
 */
public class ZoomTask {

    private static final float MAX_ZOOM = 1.1f;
    private final Cell cell;
    private boolean grow = true;
    private float zoom = 1;

    public ZoomTask(Cell cell) {
        this.cell = cell;
    }

    public void execute(float delta) {
        if (grow) {
            zoom += delta * 0.7f;
            if (zoom >= MAX_ZOOM) {
                zoom = MAX_ZOOM;
                grow = false;
            }
        } else {
            zoom -= delta * 0.7f;
            if (zoom <= 1) {
                zoom = 1;
                grow = true;
            }
        }
        cell.setZoom(zoom);
    }

    public void stop() {
        cell.setZoom(1);
    }
}
