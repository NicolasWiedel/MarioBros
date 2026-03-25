package de.wiedel.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.wiedel.mario.MarioGame;
import de.wiedel.mario.assets.AssetDescriptors;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.entities.Goomba;
import de.wiedel.mario.entities.Mario;
import de.wiedel.mario.scenes.Hud;
import de.wiedel.mario.tools.B2WorldCreator;
import de.wiedel.mario.tools.WorldContactListener;

public class PlayScreen implements Screen {

    /**
     * Verweis auf die Game-Klasse für den Zugriff auf
     * Spritebatch und AssetManager
     */
    private MarioGame game;
    private SpriteBatch batch;
    private AssetManager assetManager;

    /** die Hauptkamera und der entsprechende Viewport des Spiels **/
    private OrthographicCamera gameCam;
    private Viewport gameViewport;

    /** Userinterface für Spielstand */
    private Hud hud;

    /** die TiledMap des aktuellen Levels */
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    /** Verweis auf unseren Spieler */
    private Mario mario;
    // TODO
    private Goomba goomba;

    /** Box2D Welt */
    private World world;
    /** DebugRenderer zur Visualisierung von Box2D Bodies */
    private Box2DDebugRenderer debugRenderer;

    private Music music;

    private float accumulator;


    public PlayScreen(MarioGame game){
        this.game = game;
        batch = game.getBatch();
        assetManager = game.getAssetManager();
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(GameConfig.V_WIDTH / GameConfig.PPM,
            GameConfig.V_HEIGHT / GameConfig.PPM, gameCam);

        hud = new Hud(batch);

        map = assetManager.get(AssetDescriptors.LEVEL_1);
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / GameConfig.PPM);
        gameCam.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);

        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();

        new B2WorldCreator(this, assetManager);

        mario = new Mario(this);

        world.setContactListener(new WorldContactListener());

        music = assetManager.get(AssetDescriptors.MARIO_MUSIC);
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();

        // TODO
        goomba = new Goomba(this, .64f, .32f);
    }

    public TextureAtlas getAtlas(){
        return assetManager.get(AssetDescriptors.MARIO_AND_ENEMIES);
    }

    @Override
    public void show() {
        accumulator = 0f;
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

        accumulator += delta;
        while (accumulator >= GameConfig.TIME_STEP){
            world.step(GameConfig.TIME_STEP, 6, 2);
            accumulator -= GameConfig.TIME_STEP;
        }

        handleInput(delta);

        mario.update(delta);
        // TODO
        goomba.update(delta);
        hud.update(delta);

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

        game.getBatch().setProjectionMatrix(gameCam.combined);
        game.getBatch().begin();
        mario.draw(game.getBatch());
        // TODO
        goomba.draw(game.getBatch());
        game.getBatch().end();

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

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
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
