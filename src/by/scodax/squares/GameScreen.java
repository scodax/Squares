package by.scodax.squares;

import by.scodax.squares.helpers.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    // This is the constructor, not the class declaration
    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 480;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        world = new GameWorld();
        InputHandler inputHandler = new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight);
        Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(20, 0.5f, 2, 0.15f, inputHandler), inputHandler));
        Gdx.input.setCatchBackKey(true);

        renderer = new GameRenderer(world, (int) gameHeight, inputHandler);
        world.setRenderer(renderer);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}