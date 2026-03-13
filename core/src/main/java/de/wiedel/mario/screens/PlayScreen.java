package de.wiedel.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import de.wiedel.mario.Mario;
import de.wiedel.mario.config.GameConfig;

public class PlayScreen implements Screen {

    private Mario game;

    private SpriteBatch batch;

    // vorübergehend
    private Texture texture;

    public PlayScreen(Mario game){
        this.game = game;
        batch = game.getBatch();

        // vorübergehend
        texture = new Texture("libgdx.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(GameConfig.CORNFLOWER_BLUE);
        batch.begin();
        batch.draw(texture, Gdx.graphics.getWidth() / 2f - texture.getWidth() / 2f,
            Gdx.graphics.getHeight() /2f - texture.getHeight() / 2f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
