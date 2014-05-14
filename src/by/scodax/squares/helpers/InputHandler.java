package by.scodax.squares.helpers;

import by.scodax.squares.GameWorld;
import by.scodax.squares.controller.SquareController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 24.04.14.
 */
public class InputHandler implements GestureDetector.GestureListener, InputProcessor {
    private GameWorld myWorld;


    private float scaleFactorX;
    private float scaleFactorY;
    private final SquareController squareController;


    public InputHandler(GameWorld myWorld, float scaleFactorX,
                        float scaleFactorY, SquareController squareController) {
        this.myWorld = myWorld;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
        this.squareController = squareController;

    }


    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    @Override
    public boolean touchDown(float v, float v2, int i, int i2) {
        float x = v / scaleFactorX;
        float y = v2 / scaleFactorY;
        if (squareController.getBounds().contains(x, y)) {
            squareController.handleTouch(x, y);
        }
        return false;
    }

    @Override
    public boolean tap(float v, float v2, int i, int i2) {
        float x = v / scaleFactorX;
        float y = v2 / scaleFactorY;
        return false;
    }

    @Override
    public boolean longPress(float v, float v2) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (!myWorld.isRunning()) {
            return true;
        }
        Boolean gameOver = null;
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
            } else if (velocityX < 0) {
            } else {
                // Do nothing.
            }
        } else {
            if (velocityY > 0) {
            } else if (velocityY < 0) {
            } else {
                // Do nothing.
            }
        }
        return true;
    }

    @Override
    public boolean pan(float v, float v2, float v3, float v4) {
        return false;
    }

    @Override
    public boolean panStop(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean zoom(float v, float v2) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            if (myWorld.isRestart()) {
                myWorld.setState(GameWorld.GameState.RUNNING);
            } else if (myWorld.isRunning()) {
                myWorld.setState(GameWorld.GameState.EXIT);
            } else if (myWorld.isExit()) {
                myWorld.setState(GameWorld.GameState.RUNNING);
            } else if (myWorld.isGameOver()) {
                myWorld.setState(GameWorld.GameState.RUNNING);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        System.out.println("" + i + " : " + i2 + " : " + i3 + " : " + i4);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}