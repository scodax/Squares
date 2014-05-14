package by.scodax.squares;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class GameActivity extends AndroidApplication {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new
                AndroidApplicationConfiguration();
        config.useGLSurfaceView20API18 = true;

        initialize(new SquaresGame(), config);
    }
}
