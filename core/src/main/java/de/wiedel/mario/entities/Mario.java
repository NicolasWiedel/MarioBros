package de.wiedel.mario.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.wiedel.mario.assets.RegionNames;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.screens.PlayScreen;

public class Mario extends Sprite {

    /** Enum für die Zustände, in denen sich Mario befunden kann. */
    public enum State{ FALLING, JUMPING, STANDING, RUNNING}

    /** aktueller Zustand*/
    private State currentState;
    /** vorheriger Zustand */
    private State previousState;
    /** Verweis auf die Box2D Welt */
    private World world;
    /** Box2D Körper von Mario */
    private Body body;
    /** Bildausschnitt der stehenden Marios */
    private TextureRegion marioStand;
    /** Laufanimation */
    private Animation marioRun;
    /** Springanimation*/
    private Animation marioJump;

    private float stateTimer;
    private boolean runningRight;

    public Mario(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion(RegionNames.LITTLE_MARIO));
        this.world = world;

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0f;
        runningRight = true;

        defineMario();
        marioStand = new TextureRegion(getTexture(), 0, 10, 16 , 16);
        setBounds(0, 0, 16 /GameConfig.PPM, 16 / GameConfig.PPM);
        setRegion(marioStand);
    }

    /** Update - Funktion von Mario */
    public void update(float delta){
        setPosition(body.getPosition().x -getWidth() / 2,
            body.getPosition().y - getHeight() / 2);
    }

    /** Definition der Box2d Komponenten von Mario */
    private void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(GameConfig.MARIO_START_X / GameConfig.PPM,
            GameConfig.MARIO_START_Y / GameConfig.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(GameConfig.MARIO_RADIUS / GameConfig.PPM);

        fdef.shape = shape;
        body.createFixture(fdef);
    }

    public Body getBody() {
        return body;
    }
}
