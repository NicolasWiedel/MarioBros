package de.wiedel.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.wiedel.mario.screens.PlayScreen;

public class Mario extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
