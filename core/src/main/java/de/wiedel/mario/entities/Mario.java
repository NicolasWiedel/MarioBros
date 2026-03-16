package de.wiedel.mario.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.wiedel.mario.assets.RegionNames;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.screens.PlayScreen;

public class Mario extends Sprite {

    private World world;
    private Body body;
    private TextureRegion marioStand;

    public Mario(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion(RegionNames.LITTLE_MARIO));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 0, 10, 16 , 16);
        setBounds(0, 0, 16 /GameConfig.PPM, 16 / GameConfig.PPM);
        setRegion(marioStand);
    }

    public void update(float delta){
        setPosition(body.getPosition().x -getWidth() / 2,
            body.getPosition().y - getHeight() / 2);
    }

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
