package de.wiedel.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.wiedel.mario.MarioGame;
import de.wiedel.mario.assets.AssetDescriptors;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.entities.Mario;
import de.wiedel.mario.scenes.Hud;
import de.wiedel.mario.tools.B2WorldCreator;

public class PlayScreen implements Screen {

    private MarioGame game;

    private SpriteBatch batch;
    private AssetManager assetManager;

    private OrthographicCamera gameCam;
    private Viewport gameViewport;

    private Hud hud;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Mario mario;

    // Box2D stuff
    private World world;
    private Box2DDebugRenderer debugRenderer;

    public PlayScreen(MarioGame game){
        this.game = game;
        batch = game.getBatch();
        assetManager = game.getAssetManager();
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(GameConfig.V_WIDTH ,
            GameConfig.V_HEIGHT, gameCam);

        hud = new Hud(batch);

        map = assetManager.get(AssetDescriptors.LEVEL_1);
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / GameConfig.UNIT_SCALE);
        gameCam.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);

        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        mario = new Mario(world);


    }

    @Override
    public void show() {

    }

    private void handleInput(float delta){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            mario.getBody().applyLinearImpulse(new Vector2(0, GameConfig.MARIO_JUMP_SPEED),
                mario.getBody().getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
            && mario.getBody().getLinearVelocity().x <= GameConfig.MARIO_MAX_SPEED){
            mario.getBody().applyLinearImpulse(new Vector2(GameConfig.MARIO_HORIZONTAL_ACCELERATION, 0f),
                mario.getBody().getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
            && mario.getBody().getLinearVelocity().x >= -GameConfig.MARIO_MAX_SPEED){
            mario.getBody().applyLinearImpulse(new Vector2(-GameConfig.MARIO_HORIZONTAL_ACCELERATION, 0f),
                mario.getBody().getWorldCenter(), true);
        }
    }

    private void update(float delta){
        handleInput(delta);

        world.step(1/60f, 6, 2);

        gameCam.position.x = mario.getBody().getPosition().x;

        gameCam.update();
        mapRenderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        ScreenUtils.clear(GameConfig.CORNFLOWER_BLUE);

        mapRenderer.render();

        debugRenderer.render(world, gameCam.combined);

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
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }
}
