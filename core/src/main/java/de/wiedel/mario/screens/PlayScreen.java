package de.wiedel.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.wiedel.mario.Mario;
import de.wiedel.mario.config.GameConfig;

public class PlayScreen implements Screen {

    private Mario game;

    private SpriteBatch batch;

    private OrthographicCamera gameCam;
    private Viewport gameViewport;

    // vorübergehend
    private Texture texture;

    public PlayScreen(Mario game){
        this.game = game;
        batch = game.getBatch();
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(800, 480, gameCam);

        // vorübergehend
        texture = new Texture("libgdx.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(GameConfig.CORNFLOWER_BLUE);
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
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
