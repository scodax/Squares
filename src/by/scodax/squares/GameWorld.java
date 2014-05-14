package by.scodax.squares;

/**
 * User: Administrator
 * Date: 24.04.14
 * Time: 17:50
 */
public class GameWorld {

    private GameRenderer renderer;

    private GameState currentState;

    public void setState(GameState gameState) {
        currentState = gameState;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.RUNNING;
    }

    public enum GameState {
        RUNNING, IS_RESTART, GAMEOVER, EXIT
    }

    public GameWorld() {
        this.currentState = GameState.RUNNING;
    }

    public void update(float delta) {

        switch (currentState) {
            case IS_RESTART:
            case EXIT:
//                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
//        updateRunning(delta);

    }

    private void updateReady(float delta) {
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }
    }

    public boolean isRestart() {
        return currentState == GameState.IS_RESTART;
    }

    public boolean isExit() {
        return currentState == GameState.EXIT;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }



}