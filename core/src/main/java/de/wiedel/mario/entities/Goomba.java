package de.wiedel.mario.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.screens.PlayScreen;

public class Goomba extends Enemy {

    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;

    public Goomba(PlayScreen playScreen, float x, float y) {
        super(playScreen, x, y);
        frames = new Array<>();
        for (int i = 0; i < 2; i++){
            frames. add(new TextureRegion(playScreen.getAtlas().findRegion("goomba"),
                i * 16, 0, 16, 16));
        }
        walkAnimation = new Animation<>(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 16 / GameConfig.PPM, 16 / GameConfig.PPM);
    }

    public void update(float delta){
        stateTime += delta;
        setPosition(body.getPosition().x - getWidth() / 2,
            body.getPosition().y - getHeight() / 2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(GameConfig.MARIO_START_X / GameConfig.PPM,
            GameConfig.MARIO_START_Y / GameConfig.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(GameConfig.MARIO_RADIUS / GameConfig.PPM);
        fdef.filter.categoryBits = GameConfig.ENEMY_BIT;
        fdef.filter.maskBits = GameConfig.GROUND_BIT |
            GameConfig.COIN_BIT |
            GameConfig.BRICK_BIT |
            GameConfig.ENEMY_BIT |
            GameConfig.OBJECT_BIT |
            GameConfig.MARIO_BIT;

        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
