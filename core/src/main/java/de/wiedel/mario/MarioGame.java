package de.wiedel.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.wiedel.mario.screens.LoadingScreen;

/**
 * Haupt-Game-Klasse, die den SpriteBatch und den AssetManager hält
 */
public class MarioGame extends Game {

    /** globaler SpriteBatch */
    private SpriteBatch batch;
    /** globaler AssetManager */
    private AssetManager assetManager;

    /** Erstellt den SpriteBatch und den AssetManager und deligiert zum nächsten Screen */
    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        setScreen(new LoadingScreen(this));
    }

    /** Ruft die render-Methode der Game-Klasse auf */
    @Override
    public void render() {
        super.render();
    }

    /** Dispose setzt Ressourcen frei */
    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }

    /** Getter für den SpriteBatch */
    public SpriteBatch getBatch() {
        return batch;
    }

    /** Getter für den AssetManager */
    public AssetManager getAssetManager() {
        return assetManager;
    }
}
