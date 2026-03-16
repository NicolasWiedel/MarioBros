package de.wiedel.mario.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import de.wiedel.mario.config.GameConfig;

public class Mario extends Sprite {

    private World world;
    private Body body;

    public Mario(World world){
        this.world = world;
        defineMario();
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
