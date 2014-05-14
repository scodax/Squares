package by.scodax.squares;

import by.scodax.squares.helpers.AssetLoader;
import com.badlogic.gdx.Game;

/**
 * User: Administrator
 * Date: 23.04.14
 * Time: 11:07
 */
public class SquaresGame extends Game
{
    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
