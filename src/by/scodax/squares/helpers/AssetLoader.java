package by.scodax.squares.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by patrick on 24.04.14.
 */
public class AssetLoader {

    public static Texture logo, field;
    public static Preferences prefs;

    public static void load() {

        logo = new Texture(Gdx.files.internal("data/logo.png"));
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



        field = new Texture(Gdx.files.internal("data/field.png"));
        field.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);



        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("SquaresScodax");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        logo.dispose();
        field.dispose();
    }

}
