package de.wiedel.mario.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.wiedel.mario.Mario;
import de.wiedel.mario.assets.AssetDescriptors;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.scenes.Hud;

public class PlayScreen implements Screen {

    private Mario game;

    private SpriteBatch batch;
    private AssetManager assetManager;

    private OrthographicCamera gameCam;
    private Viewport gameViewport;

    private Hud hud;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public PlayScreen(Mario game){
        this.game = game;
        batch = game.getBatch();
        assetManager = game.getAssetManager();
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(GameConfig.V_WIDTH, GameConfig.V_HEIGHT, gameCam);

        hud = new Hud(batch);

        map = assetManager.get(AssetDescriptors.LEVEL_1);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gameViewport.getScreenWidth() / 2f, gameViewport.getScreenHeight() / 2f, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(GameConfig.CORNFLOWER_BLUE);

        batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
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
