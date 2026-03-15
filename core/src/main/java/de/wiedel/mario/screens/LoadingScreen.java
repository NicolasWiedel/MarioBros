package de.wiedel.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import de.wiedel.mario.MarioGame;
import de.wiedel.mario.assets.AssetDescriptors;
import de.wiedel.mario.config.GameConfig;

public class LoadingScreen implements Screen {

    private MarioGame game;
    private final SpriteBatch batch;
    private final AssetManager assetManager;
    private final BitmapFont defaultFont;

    private final Texture loadingbarFill;
    private final Texture loadingbarEmpty;

    private float timer;

    public LoadingScreen(MarioGame game){
        this.game = game;
        batch = game.getBatch();
        assetManager = game.getAssetManager();

        assetManager.load(AssetDescriptors.LOADING_BAR_EMPTY);
        assetManager.load(AssetDescriptors.LOADING_BAR_FILL);
        assetManager.load(AssetDescriptors.DEFAULT_FONT);
        assetManager.finishLoading();

        loadingbarFill = assetManager.get(AssetDescriptors.LOADING_BAR_FILL);
        loadingbarEmpty = assetManager.get(AssetDescriptors.LOADING_BAR_EMPTY);
        defaultFont = assetManager.get(AssetDescriptors.DEFAULT_FONT);

        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        assetManager.load(AssetDescriptors.LEVEL_1);
    }

    @Override
    public void show() {
        timer = 0f;
    }

    @Override
    public void render(float delta) {
        boolean done = assetManager.update();

        if (done){
            game.setScreen(new PlayScreen(game));
        }

        ScreenUtils.clear(GameConfig.CORNFLOWER_BLUE);

        batch.begin();

        float x = Gdx.graphics.getWidth() / 2f - loadingbarEmpty. getWidth() / 2f;
        float y = Gdx.graphics.getHeight() / 2f - loadingbarEmpty.getHeight() / 2f;
        float width = loadingbarEmpty.getWidth() * assetManager.getProgress();
        batch.draw(loadingbarEmpty, x, y);
        batch.draw(loadingbarFill, x, y, width, loadingbarFill.getHeight());
        defaultFont.draw(batch,
            String.format("Loading %d%%",
                (int)(assetManager.getProgress() * 100)),
            x + 140, y + loadingbarEmpty.getHeight() + 50);

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
        defaultFont.dispose();
    }
}
