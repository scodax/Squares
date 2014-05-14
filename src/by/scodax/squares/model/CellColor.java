package by.scodax.squares.model;

import com.badlogic.gdx.graphics.Color;

/**
 * User: Administrator
 * Date: 14.05.14
 * Time: 15:35
 */
public enum CellColor {

    White(Color.WHITE), Yellow(Color.YELLOW), Orange(Color.ORANGE), Red(Color.RED),
    Green(Color.GREEN), Blue(Color.BLUE), Purple(Color.MAGENTA), Black(Color.BLACK);

    private final Color color;

    CellColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
