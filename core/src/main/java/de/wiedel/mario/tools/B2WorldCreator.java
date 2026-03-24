package de.wiedel.mario.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import de.wiedel.mario.config.GameConfig;
import de.wiedel.mario.entities.Brick;
import de.wiedel.mario.entities.Coin;
import de.wiedel.mario.screens.PlayScreen;

public class B2WorldCreator {

    private AssetManager assetManager;

    public B2WorldCreator(PlayScreen playScreen, AssetManager assetManager){

        this.assetManager = assetManager;
        World world = playScreen.getWorld();
        TiledMap map = playScreen.getMap();

        // vorübergehender Code
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // create ground bodies and fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameConfig.PPM,
                (rect.getY() + rect.getHeight() / 2) / GameConfig.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameConfig.PPM,
                rect.getHeight() / 2 / GameConfig.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // create pipe bodys and fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameConfig.PPM,
                (rect.getY() + rect.getHeight() / 2) / GameConfig.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameConfig.PPM,
                rect.getHeight() / 2 / GameConfig.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // create brick bodys and fixtures
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(playScreen, rect, assetManager);
        }

        // create coin bodys and fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(playScreen, rect, assetManager);
        }
    }
}
