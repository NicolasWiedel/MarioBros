package de.wiedel.mario.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import de.wiedel.mario.screens.PlayScreen;

public abstract class Enemy extends Sprite {

    /** Verweis auf die B2D-Welt */
    protected World world;
    /** Verweis auf den PlayScreen */
    protected PlayScreen playScreen;
    /** Box2D Body des Enemies */
    protected Body body;

    public Enemy(PlayScreen playScreen, float x, float y){
        this.world = playScreen.getWorld();
        this.playScreen = playScreen;
        setPosition(x, y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
    public abstract void hitOnHead();
}
