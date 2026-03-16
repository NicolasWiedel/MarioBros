package de.wiedel.mario.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> DEFAULT_FONT =
        new AssetDescriptor<>(Assets.DEFAULT_FONT, BitmapFont.class);

    public static final AssetDescriptor<Texture> LOADING_BAR_EMPTY =
        new AssetDescriptor<>(Assets.LOADING_BAR_EMPTY, Texture.class);
    public static final AssetDescriptor<Texture> LOADING_BAR_FILL =
        new AssetDescriptor<>(Assets.LOADING_BAR_FILL, Texture.class);

    public static final AssetDescriptor<TextureAtlas> MARIO_AND_ENEMIES =
        new AssetDescriptor<>(Assets.MARIO_ENEMIES_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<TiledMap> LEVEL_1 =
        new AssetDescriptor<>(Assets.LEVEL_1, TiledMap.class);
}
