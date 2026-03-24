package de.wiedel.mario.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import de.wiedel.mario.assets.AssetDescriptors;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.scenes.Hud;
import de.wiedel.mario.screens.PlayScreen;

/** Klasse, die die einzelnen Bricks beschreibt */
public class Brick extends InteractiveTileObject {

    private Sound breakBlock;

    public Brick(PlayScreen playScreen, Rectangle bounds, AssetManager assetManager) {
        super(playScreen, bounds, assetManager);
        fixture.setUserData(this);
        setCategoryFilter(GameConfig.BRICK_BIT);
        breakBlock = assetManager.get(AssetDescriptors.BREAK_BLOCK);
    }

    /** Was passiert, wenn Mario mit seinem Kopf einen Brick trifft*/
    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Head hits brick!");
        setCategoryFilter(GameConfig.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
        breakBlock.play();
    }
}
