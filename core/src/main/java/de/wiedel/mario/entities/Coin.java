package de.wiedel.mario.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.scenes.Hud;
import de.wiedel.mario.screens.PlayScreen;

/** Klasse, die die einzelnen Coins beschreibt */
public class Coin extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(PlayScreen playScreen, Rectangle bounds, AssetManager assetManager) {
        super(playScreen, bounds, assetManager);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(GameConfig.COIN_BIT);
    }

    /** Was passiert, wenn Mario mit seinem Kopf eine Coin trifft*/
    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Head hits coin!");
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);
    }
}
