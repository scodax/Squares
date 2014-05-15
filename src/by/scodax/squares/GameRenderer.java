package by.scodax.squares;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import by.scodax.squares.controller.SquareController;
import by.scodax.squares.helpers.*;
import by.scodax.squares.model.Cell;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import static by.scodax.squares.controller.SquareController.CELL_SIZE;

/**
 * User: Administrator
 * Date: 24.04.14
 * Time: 17:50
 */
public class GameRenderer {



    private static final float CORNER_RADIUS = 4.0f;

    private final Color transitionColor;
    private GameWorld myWorld;
    private final SquareController squareController;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    // Game Assets
    private Texture field;

    private Value alpha = new Value();

    // Tween stuff
    private TweenManager manager;


    public GameRenderer(GameWorld world, int gameHeight, InputHandler inputHandler, SquareController squareController) {
        myWorld = world;
        this.squareController = squareController;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 480, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();

        transitionColor = new Color();
        prepareTransition(255, 255, 255, .5f);

    }

    private void initGameObjects() {

    }

    private void initAssets() {
        field = AssetLoader.field;
        //        noStar = AssetLoader.noStar;
    }

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        Draw Background color
        shapeRenderer.setColor(ColorUtils.COLOR_BACKGROUND);
        shapeRenderer.rect(0, 0, 480, 800);

        shapeRenderer.end();

        batcher.begin();
        batcher.enableBlending();

        batcher.draw(field, squareController.getX(), squareController.getY(), squareController.getWidth(), squareController.getHeight());

        batcher.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Cell[][] cells = squareController.getCells();
        for (int i = 0; i < cells.length; i++) {
            Cell[] row = cells[i];
            for (int j = 0; j < row.length; j++) {
                Cell cell = row[j];

                float zoomDelta = (cell.getZoom() * CELL_SIZE - CELL_SIZE) / 2;
                float x = squareController.getX() + 2 + i * (CELL_SIZE + 2) - zoomDelta;
                float y = squareController.getY() + 2 + j * (CELL_SIZE + 2) - zoomDelta;
                float size = CELL_SIZE * cell.getZoom();

//                float x = squareController.getX() + 2 + i * (CELL_SIZE + 2);
//                float y = squareController.getY() + 2 + j * (CELL_SIZE + 2);
//                float size = CELL_SIZE;
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setProjectionMatrix(cam.combined);
                shapeRenderer.setColor(cell.getColor().getColor());
                shapeRenderer.getColor().a = 0.7f;
                shapeRenderer.rect(x + CORNER_RADIUS, y, size - CORNER_RADIUS * 2, size);
                shapeRenderer.rect(x, y + CORNER_RADIUS, CORNER_RADIUS, size - CORNER_RADIUS * 2);
                shapeRenderer.rect(x + size - CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS, size - CORNER_RADIUS * 2);

                shapeRenderer.arc(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS, 180, 90);
                shapeRenderer.arc(x + CORNER_RADIUS, y + size - CORNER_RADIUS, CORNER_RADIUS, 90, 90);
                shapeRenderer.arc(x + size - CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS, 270, 90);
                shapeRenderer.arc(x + size - CORNER_RADIUS, y + size - CORNER_RADIUS, CORNER_RADIUS, 0, 90);

                shapeRenderer.end();
            }
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }


    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0)
                .ease(TweenEquations.easeOutQuad).start(manager);
    }

}
