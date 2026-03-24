package de.wiedel.mario.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
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
    private Animation<TextureRegion> marioRun;
    /** Springanimation*/
    private Animation<TextureRegion> marioJump;

    /** Timer für die Animationen */
    private float stateTimer;
    /** In welche Richtung läuft Mario? */
    private boolean runningRight;

    public Mario(PlayScreen playScreen) {
        super(playScreen.getAtlas().findRegion(RegionNames.LITTLE_MARIO));
        this.world = playScreen.getWorld();

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0f;
        runningRight = true;

        // run Animation
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i < 4; i++){
            frames.add(new TextureRegion(getTexture(), i * 16, 10, 16, 16));
        }
        marioRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // jumop Animation
        for (int i = 4; i < 6; i++){
            frames.add(new TextureRegion(getTexture(), i * 16, 10, 16, 16));
        }
        marioJump = new Animation<TextureRegion>(0.1f, frames);

        defineMario();
        marioStand = new TextureRegion(getTexture(), 0, 10, 16 , 16);
        setBounds(0, 0, 16 /GameConfig.PPM, 16 / GameConfig.PPM);
        setRegion(marioStand);
    }

    /** Update - Funktion von Mario */
    public void update(float delta){
        setPosition(body.getPosition().x -getWidth() / 2,
            body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
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
        fdef.filter.categoryBits = GameConfig.MARIO_BIT;
        fdef.filter.maskBits = GameConfig.DEFAULT_BIT | GameConfig.COIN_BIT |GameConfig.BRICK_BIT;

        fdef.shape = shape;
        body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / GameConfig.PPM, 8 / GameConfig.PPM),
            new Vector2(2 / GameConfig.PPM, 8 / GameConfig.PPM));
        fdef.shape = head;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");
    }

    /** die Methode ermittelt den aktuellen Frame, der gezeichnet werden muss */
    private TextureRegion getFrame(float delta){
        currentState = getState();

        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }
        if((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }

    /** gibt den aktuellen Zustand Marios zurück */
    public State getState(){
        if (body.getLinearVelocity().y > 0
            || (body.getLinearVelocity().y < 0
            && previousState == State.JUMPING)){
            return State.JUMPING;
        }
        else if (body.getLinearVelocity().y < 0){
            return State.FALLING;
        }
        else if (body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }
    }

    /** Getter für den Box2D Body von Mario */
    public Body getBody() {
        return body;
    }
}
